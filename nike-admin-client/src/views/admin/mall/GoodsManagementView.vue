<template>
  <div>
    <!-- 頂部面包屑標識與導航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right" style="font-size: 16px;">
      <el-breadcrumb-item :to="{ path: '/' }"><i class="el-icon-s-promotion"></i> 後台管理</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/admin/mall' }">商城管理</el-breadcrumb-item>
      <el-breadcrumb-item>商品管理</el-breadcrumb-item>
    </el-breadcrumb>

    <!-- 操作區域 -->
    <div style="margin: 0 0 20px 0;">
      <el-divider content-position="left">操作區域</el-divider>
      <el-button type="primary" size="small" @click="gotoAddNew()">發布商品</el-button>
    </div>

    <!-- 數據表格 -->
    <el-divider content-position="left">數據表格</el-divider>
    <el-table :data="tableData" border style="width: 100%">
      <el-table-column prop="id" label="ID" width="80" align="center"
                       :show-overflow-tooltip="true"></el-table-column>
      <el-table-column label="封面圖片" width="100" align="center">
        <template slot-scope="scope">
          <el-image
              style="width: 80px; height: 80px"
              :src="scope.row.coverUrl"
              fit="fit"></el-image>
        </template>
      </el-table-column>
      <el-table-column prop="categoryName" label="類別" width="80" align="center"
                       :show-overflow-tooltip="true"></el-table-column>
      <el-table-column prop="barCode" label="商品條碼" width="150" align="center"
                       :show-overflow-tooltip="true"></el-table-column>
      <el-table-column prop="title" label="商品標題" align="center"
                       :show-overflow-tooltip="true"></el-table-column>
      <el-table-column prop="salePrice" label="售價" width="80" align="center"></el-table-column>
      <el-table-column prop="salesCount" label="銷量" width="80" align="center"></el-table-column>
      <el-table-column label="好評/評論" width="100" align="center">
        <template slot-scope="scope">
          <div>{{ scope.row.positiveCommentCount }} / {{ scope.row.commentCount }}</div>
        </template>
      </el-table-column>
      <el-table-column prop="sort" label="排序序號" width="80" align="center"></el-table-column>
      <el-table-column label="審核狀態" width="80" align="center">
        <template slot-scope="scope">
          <el-tooltip class="item" effect="dark" content="如需發布，請點擊【審核】按鈕進行審核，任何修改都會導致發布狀態改為【未審核】" placement="top">
            <span v-if="scope.row.checkState == 1" style="color: #67c23a">{{ checkStateText[scope.row.checkState] }}</span>
            <span v-else-if="scope.row.checkState == 2" style="color: #F56C6C">{{ checkStateText[scope.row.checkState] }}</span>
            <span v-else style="color: #aaa">{{ checkStateText[scope.row.checkState] }}</span>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column label="推薦" width="60" align="center">
        <template slot-scope="scope">
          <el-switch
              @change="toggleRecommend(scope.row)"
              v-model="scope.row.isRecommend"
              :active-value="1"
              :inactive-value="0"
              active-color="#13ce66"
              inactive-color="#cccccc">
          </el-switch>
        </template>
      </el-table-column>
      <el-table-column label="上架" width="60" align="center">
        <template slot-scope="scope">
          <el-switch
              @change="togglePutOn(scope.row)"
              v-model="scope.row.isPutOn"
              :active-value="1"
              :inactive-value="0"
              active-color="#13ce66"
              inactive-color="#cccccc">
          </el-switch>
        </template>
      </el-table-column>

      <el-table-column label="操作" width="160" align="center">
        <template slot-scope="scope">
          <el-button class="op_button" :disabled="scope.row.checkState != 0" @click="openCheckDialog(scope.row)">審核</el-button>
          <el-button class="op_button" @click="openShowInfoDialog(scope.row)">查看</el-button>
          <el-button class="op_button" @click="openEditDialog(scope.row)">編輯</el-button>
          <el-button class="op_button danger" @click="openDeleteConfirm(scope.row)">刪除</el-button>
        </template>
      </el-table-column>
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

    <!-- 顯示審核商品的對話框 -->
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

    <!-- 顯示商品詳情的對話框 -->
    <el-dialog class="goods-info" :title="goods.title" :visible.sync="infoDialogVisible">
      <el-descriptions :column="1" border label-class-name="spu-info-label">
        <el-descriptions-item label="數據ID">
          {{ goods.id }}
          <el-tooltip class="item" effect="dark" content="此數據不會顯示在普通用戶界面" placement="right">
            <sup><i class="el-icon-info"></i></sup>
          </el-tooltip>
        </el-descriptions-item>
        <el-descriptions-item label="類別">{{ goods.categoryName }}</el-descriptions-item>
        <el-descriptions-item label="商品條碼">{{ goods.barCode }}</el-descriptions-item>
        <el-descriptions-item label="標題">{{ goods.title }}</el-descriptions-item>
        <el-descriptions-item label="簡介">{{ goods.brief }}</el-descriptions-item>
        <el-descriptions-item label="關鍵詞">{{ goods.keywords }}</el-descriptions-item>
        <el-descriptions-item label="售價">{{ goods.salePrice }} 元</el-descriptions-item>
        <el-descriptions-item label="銷量">{{ goods.salesCount }}</el-descriptions-item>
        <el-descriptions-item label="排序序號">{{ goods.sort }}</el-descriptions-item>
        <el-descriptions-item label="是否推薦">{{ goods.isRecommend == 0 ? '否' : '是' }}</el-descriptions-item>
        <el-descriptions-item label="是否上架">{{ goods.isPutOn == 0 ? '否' : '是' }}</el-descriptions-item>
        <el-descriptions-item label="審核狀態">{{ checkStateText[goods.checkState] }}</el-descriptions-item>
        <el-descriptions-item label="好評數量">{{ goods.positiveCommentCount }}</el-descriptions-item>
        <el-descriptions-item label="差評數量">{{ goods.negativeCommentCount }}</el-descriptions-item>
        <el-descriptions-item label="評論總量">{{ goods.commentCount }}</el-descriptions-item>
        <el-descriptions-item label="商品圖片">
          <el-image class="picture-item"
                    :src="goods.coverUrl"
                    fit="fit"></el-image>
        </el-descriptions-item>
        <el-descriptions-item label="商品詳情"><span v-html="goods.detail"></span></el-descriptions-item>
      </el-descriptions>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="infoDialogVisible = false">關 閉</el-button>
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
      // 表格數據
      tableData: [],
      // 分頁相關數據
      currentPage: this.$router.currentRoute.query.page ? parseInt(this.$router.currentRoute.query.page) : 1,
      pageSize: 20,
      total: 0,
      // 商品詳情
      goods: {
      },
      // 審核對話框的可見性
      checkFormDialogVisible: false,
      // 審核表單數據
      checkForm: {
        id: 0,
        title: '',
        checkState: 2,
        remarks: ''
      },
      // 顯示商品詳情的對話框的可見性
      infoDialogVisible: false,
    }
  },
  methods: {
    // 跳轉到新增頁面
    gotoAddNew() {
      this.$router.push('/admin/mall/goods/add-new');
    },
    // 切換【是否上架】狀態
    togglePutOn(goods) {
      let putOnStateText = ['下架', '上架'];
      let url = BaseUrl.ADMIN_MALL + '/goods/' + goods.id;
      if (goods.isPutOn == 1) {
        url += '/put-on';
      } else {
        url += '/pull-off';
      }
      console.log('url = ' + url);

      TokenAxios.post(url).then((response) => {
        let jsonResult = response.data;
        if (jsonResult.state == 20000) {
          this.$message({
            message: putOnStateText[goods.isPutOn] + '成功！',
            type: 'success'
          });
        } else if (jsonResult.state == 40400) {
          this.$alert(jsonResult.message, '錯誤', {
            confirmButtonText: '確定',
            callback: action => {
              this.loadGoodsList();
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
    // 切換【是否推薦】狀態
    toggleRecommend(goods) {
      let recommendStateText = ['不推薦', '推薦'];
      let url = BaseUrl.ADMIN_MALL + '/goods/' + goods.id;
      if (goods.isRecommend == 1) {
        url += '/set-recommend';
      } else {
        url += '/cancle-recommend';
      }
      console.log('url = ' + url);

      TokenAxios.post(url).then((response) => {
        let jsonResult = response.data;
        if (jsonResult.state == 20000) {
          this.$message({
            message: recommendStateText[goods.isRecommend] + '成功！',
            type: 'success'
          });
        } else if (jsonResult.state == 40400) {
          this.$alert(jsonResult.message, '錯誤', {
            confirmButtonText: '確定',
            callback: action => {
              this.loadGoodsList();
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
    // 打開審核對話框
    openCheckDialog(goods) {
      this.checkForm.id = goods.id;
      this.checkForm.title = goods.title;
      this.checkFormDialogVisible = true;
    },
    // 提交審核
    submitCheck() {
      let checkStateText = ['', '通過', '拒絕'];
      let url = BaseUrl.ADMIN_MALL + '/goods/' + this.checkForm.id;
      if (this.checkForm.checkState == 2) {
        url += '/check-state/reject';
      } else {
        url += '/check-state/pass';
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
          this.loadGoodsList();
        } else if (jsonResult.state == 40400) {
          this.$alert(jsonResult.message, '錯誤', {
            confirmButtonText: '確定',
            callback: action => {
              this.checkFormDialogVisible = false;
              this.loadGoodsList();
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
    // 打開詳情對話框
    openShowInfoDialog(goods) {
      let url = BaseUrl.ADMIN_MALL + '/goods/' + goods.id;
      console.log('url = ' + url);

      TokenAxios.get(url).then((response) => {
        let jsonResult = response.data;
        if (jsonResult.state == 20000) {
          this.goods = jsonResult.data;
          this.infoDialogVisible = true;
        } else {
          this.$alert(jsonResult.message, '警告', {
            confirmButtonText: '確定',
            callback: action => {
              this.loadGoodsList();
            }
          });
        }
      });
    },
    // 打開編輯對話框
    openEditDialog(goods) {
      let title = '提示';
      let message = '您正在嘗試編輯【' + goods.id + '-' + goods.title + '】數據，抱歉，此功能尚未實現……';
      this.$alert(message, title, {
        confirmButtonText: '確定',
        callback: action => {
        }
      });
    },
    // 打開刪除確認框
    openDeleteConfirm(goods) {
      let message = '將永久刪除【' + goods.title + '】類別，是否繼續？';
      this.$confirm(message, '提示', {
        confirmButtonText: '確定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.handleDelete(goods);
      }).catch(() => {
      });
    },
    // 執行刪除
    handleDelete(goods) {
      let url = BaseUrl.ADMIN_MALL + '/goods/' + goods.id + '/delete';
      console.log('url = ' + url);

      TokenAxios.post(url).then((response) => {
        let jsonResult = response.data;
        if (jsonResult.state == 20000) {
          this.$message({
            message: '刪除成功！',
            type: 'success'
          });
          this.loadGoodsList();
        } else if (jsonResult.state == 40400) {
          this.$alert(jsonResult.message, '錯誤', {
            confirmButtonText: '確定',
            callback: action => {
              this.loadGoodsList();
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
    // 切換分頁
    changePage(page) {
      this.$router.replace('?page=' + page);
      this.loadGoodsList();
    },
    // 加載商品列表
    loadGoodsList() {
      let page = this.$router.currentRoute.query.page;
      if (!page) {
        page = 1;
      }

      let url = BaseUrl.ADMIN_MALL + '/goods?page=' + page;
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
    this.loadGoodsList();
  }
}
</script>

<style>
.op_button {
  width: 50px;
  margin: 5px 6px;
  padding: 5px 10px;
  font-size: 12px;
  border-radius: 3px;
}

.danger {
  background: #F56C6C;
  color: #fff;
}

.danger:hover {
  background: #f92d2d;
  color: #fff;
}

.goods-info {
}

.goods-info .spu-info-label {
  width: 120px;
}

.goods-info .picture-item {
  width: 80px;
  height: 80px;
  margin-right: 16px;
  margin-bottom: 10px;
  border-radius: 3px;
  float: left;
}
</style>