<template>
  <div>
    <!-- 頂部的面包屑導航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right" style="font-size: 16px;">
      <el-breadcrumb-item :to="{ path: '/' }"><i class="el-icon-s-promotion"></i> 後台管理</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/admin/mall' }">商城管理</el-breadcrumb-item>
      <el-breadcrumb-item>類別管理</el-breadcrumb-item>
      <el-breadcrumb-item v-for="item in history" :key="item.id">
        <span v-text="item.name"></span>
      </el-breadcrumb-item>
    </el-breadcrumb>

    <!-- 操作區域 -->
    <div>
      <el-divider content-position="left">操作區域</el-divider>
      <el-button type="primary" size="small" @click="gotoAddNew()">新增類別</el-button>
    </div>

    <!-- 數據表格 -->
    <el-divider content-position="left">數據表格</el-divider>
    <el-table :data="tableData" border style="width: 100%">
      <el-table-column prop="id" label="ID" width="80" align="center"></el-table-column>
      <el-table-column label="圖標" align="center" width="80">
        <template slot-scope="scope">
          <el-avatar shape="square" size="small" :src="scope.row.icon"></el-avatar>
        </template>
      </el-table-column>
      <el-table-column prop="name" label="名稱" header-align="center" width="120"></el-table-column>
      <el-table-column prop="keywords" label="關鍵詞列表" header-align="center"
                       show-overflow-tooltip></el-table-column>
      <el-table-column prop="sort" label="排序序號" align="center" width="100"></el-table-column>
      <el-table-column label="是否啟用" align="center" width="100">
        <template slot-scope="scope">
          <el-switch
              @change="toggleEnable(scope.row)"
              v-model="scope.row.enable"
              :active-value="1"
              :inactive-value="0"
              active-color="#13ce66"
              inactive-color="#999">
          </el-switch>
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
      <el-table-column label="查看子級" align="center" width="100">
        <template slot-scope="scope">
          <el-button size="mini" @click="showSubCategoryList(scope.row)"
                     :disabled="scope.row.isParent == 0">查看
          </el-button>
        </template>
      </el-table-column>

      <el-table-column label="操作" align="center" width="120">
        <template slot-scope="scope">
          <el-button type="primary" icon="el-icon-edit" size="mini" circle
                     @click="openEditDialog(scope.row)"></el-button>
          <el-button type="danger" icon="el-icon-delete" size="mini" circle
                     @click="openDeleteConfirm(scope.row)"
                     :disabled="scope.row.isParent == 1"></el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分頁控件 -->
    <div style="text-align: right; margin: 10px 0 0 0; height: 30px;">
      <el-button size="mini" style="float: left;"
                 v-if="history.length > 0"
                 @click="goBack">返回
      </el-button>
      <el-pagination
          @current-change="changePage"
          hide-on-single-page
          :current-page="currentPage"
          :page-size="pageSize"
          layout="total, prev, pager, next"
          :total="total">
      </el-pagination>
    </div>

    <!-- 修改類別信息的彈出窗口 -->
    <el-dialog title="修改類別" :visible.sync="editFormDialogVisible">
      <el-form :model="editForm" label-width="120px">
        <el-form-item label="名稱">
          <el-input v-model="editForm.name"></el-input>
        </el-form-item>
        <el-form-item label="關鍵詞列表">
          <el-input v-model="editForm.keywords"></el-input>
        </el-form-item>
        <el-form-item label="圖標">
          <el-input v-model="editForm.icon"></el-input>
        </el-form-item>
        <el-form-item label="排序序號">
          <el-input v-model="editForm.sort"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="editFormDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitEditForm()">確 定</el-button>
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
      // 查看子級時的歷史記錄，用於實現“返回上層”的功能
      history: [],
      // 分頁顯示的數據
      currentPage: this.$router.currentRoute.query.page ? parseInt(this.$router.currentRoute.query.page) : 1,
      pageSize: 10,
      total: 0,
      // 數據列表
      tableData: [],
      // 修改類別信息的彈出窗口的相關變量
      editFormDialogVisible: false,
      editForm: {
        id: 0,
        name: '',
        icon: '',
        keywords: '',
        sort: ''
      }
    }
  },
  methods: {
    // 跳轉到新增頁面
    gotoAddNew() {
      this.$router.push('/admin/mall/category/add-new');
    },
    // 切換分頁
    changePage(page) {
      let parentId = this.$router.currentRoute.query.parentId;
      if (!parentId) {
        parentId = 0;
      }
      this.$router.replace('?parentId=' + parentId + '&page=' + page);
      this.loadCategoryList();
    },
    // 顯示子級類別列表
    showSubCategoryList(category) {
      this.history[this.history.length] = category;
      console.log('------------------');
      for (let i = 0; i < this.history.length; i++) {
        console.log(this.history[i].name);
      }
      console.log('');

      this.$router.replace('?parentId=' + category.id);
      this.loadCategoryList();
    },
    // 返回：用於顯示子級類別列表後的返回
    goBack() {
      // let parentCategory = this.history[this.history.length - 1];
      // this.history.pop();
      let parentCategory = this.history.pop();
      this.$router.replace('?parentId=' + parentCategory.parentId);
      this.loadCategoryList();
    },
    // 切換啟用狀態
    toggleEnable(category) {
      let enableStateText = ['禁用', '啟用'];
      let url = BaseUrl.ADMIN_MALL + '/categories/' + category.id;
      if (category.enable == 1) {
        url += '/enable';
      } else {
        url += '/disable';
      }
      console.log('url = ' + url);

      TokenAxios.post(url).then((response) => {
        let jsonResult = response.data;
        if (jsonResult.state == 20000) {
          this.$message({
            message: enableStateText[category.enable] + '成功！',
            type: 'success'
          });
        } else if (jsonResult.state == 40400) {
          this.$alert(jsonResult.message, '錯誤', {
            confirmButtonText: '確定',
            callback: action => {
              this.loadCategoryList();
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
    // 切換【顯示】狀態
    toggleIsDisplay(category){
      let isDisplayStateText = ['不顯示', '顯示'];
      let url = BaseUrl.ADMIN_MALL + '/categories/' + category.id;
      if (category.isDisplay == 1) {
        url += '/display';
      } else {
        url += '/hidden';
      }
      console.log('url = ' + url);

      TokenAxios.post(url).then((response) => {
        let jsonResult = response.data;
        if (jsonResult.state == 20000) {
          this.$message({
            message: isDisplayStateText[category.isDisplay] + '成功！',
            type: 'success'
          });
        } else if (jsonResult.state == 40400) {
          this.$alert(jsonResult.message, '錯誤', {
            confirmButtonText: '確定',
            callback: action => {
              this.loadCategoryList();
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
    // 打開編輯對話框
    openEditDialog(category) {
      let url = BaseUrl.ADMIN_MALL + '/categories/' + category.id;
      console.log('url = ' + url);

      TokenAxios.get(url).then((response) => {
        let jsonResult = response.data;
        if (jsonResult.state == 20000) {
          this.editForm = jsonResult.data;
          this.editFormDialogVisible = true;
        } else if (jsonResult.state == 40400) {
          this.$alert(jsonResult.message, '錯誤', {
            confirmButtonText: '確定',
            callback: action => {
              this.loadCategoryList();
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
    // 提交編輯
    submitEditForm() {
      let url = BaseUrl.ADMIN_MALL + '/categories/' + this.editForm.id + '/update';
      console.log('url = ' + url);

      TokenAxios.post(url, this.editForm).then((response) => {
        let jsonResult = response.data;
        if (jsonResult.state == 20000) {
          this.$message({
            message: '修改類別成功！',
            type: 'success'
          });
          this.editFormDialogVisible = false;
          this.loadCategoryList();
        } else if (jsonResult.state = 40400) {
          this.$alert(jsonResult.message, '錯誤', {
            confirmButtonText: '確定',
            callback: action => {
              this.loadCategoryList();
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
    // 打開刪除確認框
    openDeleteConfirm(category) {
      let message = '將永久刪除【' + category.name + '】類別，是否繼續？';
      this.$confirm(message, '提示', {
        confirmButtonText: '確定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.handleDelete(category);
      }).catch(() => {
      });
    },
    // 執行刪除
    handleDelete(category) {
      let url = BaseUrl.ADMIN_MALL + '/categories/' + category.id + '/delete';
      console.log('url = ' + url);

      TokenAxios.post(url).then((response) => {
        let jsonResult = response.data;
        if (jsonResult.state == 20000) {
          this.$message({
            message: '刪除成功！',
            type: 'success'
          });
          this.loadCategoryList();
        } else if (jsonResult.state == 40400) {
          this.$alert(jsonResult.message, '錯誤', {
            confirmButtonText: '確定',
            callback: action => {
              this.loadCategoryList();
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
    // 載入類別數據
    loadCategoryList() {
      let page = this.$router.currentRoute.query.page;
      if (!page) {
        page = 1;
      }

      let parentId = this.$router.currentRoute.query.parentId;
      if (!parentId) {
        parentId = 0;
      }

      let url = BaseUrl.ADMIN_MALL + '/categories/list-by-parent?parentId=' + parentId
          + '&page=' + page;
      console.log('url = ' + url);

      TokenAxios.get(url).then((response) => {
        let jsonResult = response.data;
        if (jsonResult.state == 20000) {
          this.currentPage = jsonResult.data.pageNum;
          this.pageSize = jsonResult.data.pageSize;
          this.total = jsonResult.data.total;
          this.tableData = jsonResult.data.list;
        } else {
          this.$alert(jsonResult.message, '錯誤', {
            confirmButtonText: '确定',
            callback: action => {
            }
          });
        }
      });
    }
  },
  mounted() {
    this.loadCategoryList();
  }
}
</script>

<style scoped>
</style>