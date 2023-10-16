<template>
  <div>
    <!-- 頂部的面包屑導航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right" style="font-size: 16px;">
      <el-breadcrumb-item :to="{ path: '/' }"><i class="el-icon-s-promotion"></i> 後台管理</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/admin/content' }">內容管理</el-breadcrumb-item>
      <el-breadcrumb-item>評論管理</el-breadcrumb-item>
    </el-breadcrumb>

    <!-- 數據表格 -->
    <el-divider content-position="left">數據表格</el-divider>
    <el-table :data="tableData" border style="width: 100%">
      <el-table-column prop="id" label="ID" width="80" align="center"></el-table-column>
      <el-table-column prop="authorName" label="作者名字" align="center" width="100"
                       :show-overflow-tooltip="true"></el-table-column>
      <el-table-column label="類型" width="80" align="center">
        <template slot-scope="scope">
          {{ TypeText[scope.row.resourceType] }}
        </template>
      </el-table-column>
      <el-table-column prop="floor" label="樓層" align="center" width="80"></el-table-column>
      <el-table-column prop="content" label="評論內容" align="center" width="240"
                       :show-overflow-tooltip="true"></el-table-column>
      <el-table-column label="讚數/倒讚數" width="80" align="center">
        <template slot-scope="scope">
          <div>{{ scope.row.upCount }} / {{ scope.row.downCount }}</div>
        </template>
      </el-table-column>
      <el-table-column label="審核狀態" width="80" align="center">
        <template slot-scope="scope">
          <el-tooltip class="item" effect="dark" content="如需發布，請點擊【審核】按鈕進行審核，任何修改都會導致發布狀態改為【未審核】" placement="top">
            <span v-if="scope.row.checkState == 1" style="color: #67c23a">{{ checkStateText[scope.row.checkState] }}</span>
            <span v-else-if="scope.row.checkState == 2" style="color: #F56C6C">{{ checkStateText[scope.row.checkState] }}</span>
            <span v-else style="color: #aaa">{{ checkStateText[scope.row.checkState] }}</span>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column prop="isDisplay" label="是否顯示" align="center" width="100">
        <template slot-scope="scope">
          <el-switch
              @change="toggleIsDisplay(scope.row)"
              v-model="scope.row.isDisplay"
              :active-value="1"
              :inactive-value="0"
              active-color="#13ce66"
              inactive-color="#999">
          </el-switch>
        </template>
      </el-table-column>
      <el-table-column prop="gmtModified" label="最後修改時間" width="180" align="center"></el-table-column>

      <el-table-column label="操作" width="80" align="center">
        <template slot-scope="scope">
          <el-button class="op_button" :disabled="scope.row.checkState != 0"size="small" @click="openCheckDialog(scope.row)">審核</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分頁控件 -->
    <div style="text-align: right; margin: 10px 0 0 0; height: 30px;">
      <el-pagination
          @current-change="changePage"
          hide-on-single-page
          :current-page="currentPage"
          :page-size="pageSize"
          layout="total, prev, pager, next"
          :total="total">
      </el-pagination>
    </div>

    <!-- 顯示審核文章的對話框 -->
    <el-dialog :title="'審核 ' + checkForm.title" :visible.sync="checkFormDialogVisible">
      <el-form :model="checkForm" label-width="120px">
        <el-form-item label="審核狀態">
          <el-radio-group v-model="checkForm.checkState">
            <el-radio :label="2">拒絕</el-radio>
            <el-radio :label="1">通過</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="備注">
          <el-input v-model="checkForm.remarks"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="checkFormDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitCheck()">確 定</el-button>
      </div>
    </el-dialog>
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
      // 審核狀態文本
      TypeText: ['文章', '評論'],
      // 表格數據
      tableData: [],
      // 分頁相關數據
      currentPage: this.$router.currentRoute.query.page ? parseInt(this.$router.currentRoute.query.page) : 1,
      pageSize: 0,
      total: 0,
      // 商品詳情
      comments: {},
      // 審核對話框的可見性
      checkFormDialogVisible: false,
      // 審核表單數據
      checkForm: {
        id: '',
        title: '',
        checkState: '',
        remarks: ''
      },
    }
  },
  methods:{
    // 切換分頁
    changePage(page) {
      this.$router.replace('?page=' + page);
      this.loadCommentsList();
    },
    // 打開審核對話框
    openCheckDialog(comments) {
      this.checkForm.id = comments.id;
      this.checkForm.title = comments.title;
      this.checkFormDialogVisible = true;
    },
    // 提交審核
    submitCheck() {
      let checkStateText = ['', '通過', '拒絕'];
      let url = BaseUrl.ADMIN_CONTENT + '/comments/' + this.checkForm.id;
      if (this.checkForm.checkState == 2) {
        url += '/cancel-check';
      } else {
        url += '/pass-check';
      }
      console.log('url = ' + url);

      let data = {
        id: this.checkForm.id,
        remarks: this.checkForm.remarks
      };

      TokenAxios.post(url, data).then((response) => {
        let jsonResult = response.data;
        if (jsonResult.state == 20000) {
          this.$message({
            message: checkStateText[this.checkForm.checkState] + '審核成功！',
            type: 'success'
          });
          this.checkFormDialogVisible = false;
          this.checkForm.checkState = 2;
          this.checkForm.remarks = '';
          this.loadCommentsList();
        } else if (jsonResult.state == 40400) {
          this.$alert(jsonResult.message, '錯誤', {
            confirmButtonText: '確定',
            callback: action => {
              this.checkFormDialogVisible = false;
              this.loadCommentsList();
            }
          });
        } else {
          this.$alert(jsonResult.message, '錯誤', {
            confirmButtonText: '確定',
            callback: action => {
              this.checkFormDialogVisible = false;
            }
          });
        }
      });
    },
    // 切換【顯示】狀態
    toggleIsDisplay(comments){
      let isDisplayStateText = ['不顯示', '顯示'];
      let url = BaseUrl.ADMIN_CONTENT + '/comments/' + comments.id;
      if (comments.isDisplay == 1) {
        url += '/display';
      } else {
        url += '/hidden';
      }
      console.log('url = ' + url);

      TokenAxios.post(url).then((response) => {
        let jsonResult = response.data;
        if (jsonResult.state == 20000) {
          this.$message({
            message: isDisplayStateText[comments.isDisplay] + '成功！',
            type: 'success'
          });
        } else if (jsonResult.state == 40400) {
          this.$alert(jsonResult.message, '錯誤', {
            confirmButtonText: '確定',
            callback: action => {
              this.loadCommentsList();
            }
          });
        } else {
          this.$alert(jsonResult.message, '錯誤', {
            confirmButtonText: '確定',
            callback: action => {
            }
          });
        }
      });
    },
    //加載評論列表
    loadCommentsList() {
      let page = this.$router.currentRoute.query.page;
      if (!page) {
        page = 1;
      }

      let url = BaseUrl.ADMIN_CONTENT + '/comments/list-by-article?page=' + page;
      console.log('url = ' + url);

      TokenAxios.get(url).then((response) => {
        let jsonResult = response.data;
        if (jsonResult.state == 20000) {
          this.tableData = jsonResult.data.list;
          this.currentPage = jsonResult.data.currentPage;
          this.pageSize = jsonResult.data.pageSize;
          this.total = jsonResult.data.total;
        }
      });
    }
  },
  mounted() {
    this.loadCommentsList();
  }
}
</script>

<style>

</style>