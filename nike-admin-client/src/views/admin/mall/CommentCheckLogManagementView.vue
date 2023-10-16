<template>
  <div>
    <!-- 頂部面包屑標識與導航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right" style="font-size: 16px;">
      <el-breadcrumb-item :to="{ path: '/' }"><i class="el-icon-s-promotion"></i> 後台管理</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/admin/mall' }">商城管理</el-breadcrumb-item>
      <el-breadcrumb-item>評論審核日志</el-breadcrumb-item>
    </el-breadcrumb>

    <!-- 數據表格 -->
    <el-divider content-position="left"></el-divider>
    <el-table :data="tableData" border style="width: 100%">
      <el-table-column prop="id" label="ID" width="80" align="center"></el-table-column>
      <el-table-column prop="resourceId" label="評論ID" width="100" align="center"></el-table-column>
      <el-table-column prop="resourceBrief" label="評論" header-align="center"
                       :show-overflow-tooltip="true"></el-table-column>
      <el-table-column label="原狀態" width="80" align="center">
        <template slot-scope="scope">
          {{ checkStateText[scope.row.originalState] }}
        </template>
      </el-table-column>
      <el-table-column label="新狀態" width="80" align="center">
        <template slot-scope="scope">
          {{ checkStateText[scope.row.newState] }}
        </template>
      </el-table-column>
      <el-table-column prop="checkUsername" label="審核人" width="100" align="center"
                       :show-overflow-tooltip="true"></el-table-column>
      <el-table-column prop="checkRemarks" label="審核備注" header-align="center"
                       :show-overflow-tooltip="true"></el-table-column>
      <el-table-column prop="gmtCheck" label="審核時間" width="180" align="center"></el-table-column>
    </el-table>

    <!-- 分頁控件 -->
    <div style="margin: 10px 0; text-align: right;">
      <el-pagination
          @current-change="changePage"
          :hide-on-single-page="true"
          :page-size="pageSize"
          :current-page="currentPage"
          layout="total, prev, pager, next"
          :total="total">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import BaseUrl from "@/http/BaseUrl";
import TokenAxios from "@/http/TokenAxios";

export default {
  data() {
    return {
      // 審核狀態文本
      checkStateText: ['未審核', '通過', '拒絕'],
      // 表格數據
      tableData: [],
      // 分頁相關數據
      currentPage: this.$router.currentRoute.query.page ? parseInt(this.$router.currentRoute.query.page) : 1,
      pageSize: 20,
      total: 0,
    }
  },
  methods: {
    // 切換分頁
    changePage(page) {
      this.$router.replace('?page=' + page);
      this.loadCheckLogList();
    },
    // 加載審核日志列表
    loadCheckLogList() {
      let page = this.$router.currentRoute.query.page;
      if (!page) {
        page = 1;
      }

      let url = BaseUrl.ADMIN_MALL + '/check-logs/comment?page=' + page;
      console.log('url = ' + url);

      TokenAxios.get(url).then((response) => {
        let jsonResult = response.data;
        if (jsonResult.state == 20000) {
          this.tableData = jsonResult.data.list;
          this.currentPage = jsonResult.data.currentPage;
          this.pageSize = jsonResult.data.pageSize;
          this.total = jsonResult.data.total;
        } else {
          let title = '操作失敗';
          this.$alert(jsonResult.message, title, {
            confirmButtonText: '確定',
            callback: action => {
            }
          });
        }
      }).catch(error => {
        let title = '錯誤';
        let message = '程序執行過程中出現錯誤，請打開瀏覽器的控制台查看詳細錯誤信息！';
        this.$alert(message, title, {
          confirmButtonText: '確定',
          callback: action => {
          }
        });
        console.log(error);
      });
    }
  },
  mounted() {
    this.loadCheckLogList();
  }
}
</script>

<style>
</style>