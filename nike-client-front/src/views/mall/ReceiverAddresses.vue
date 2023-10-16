<template>
  <div>
    <!-- 浮動導航 -->
    <div class="float-link-container">
      <FloatMallLinkComponent/>
    </div>

    <!-- 頁面主體 -->
    <div class="main-container">
      <div class="mod-main mod-comm">
        <div class="mt">
          <a @click="newAddressDialogVisible = true" class="e-btn add-btn btn-5" href="javascript:;">新增收貨地址</a>
          <span class="ftx-03">您已創建<span class="ftx-02">9 </span>個收貨地址，最多可創建<span
              class="ftx-02">25 </span>個</span>
        </div>
        <div class="mc" v-for="addressItem in addressList">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <span>{{ addressItem.receiverName }}&nbsp;&nbsp;{{ addressItem.city }}</span>
              <span v-if="addressItem.isDefault" class="default-add">默認地址</span>
              <el-button v-if="!addressItem.isDefault" style="float: right; padding: 3px" type="text">設為默認
              </el-button>
              <el-button style="float: right; padding: 3px 0" type="text">編輯</el-button>
              <el-button style="float: right; padding: 3px" type="text" @click="deleteForm">刪除</el-button>
            </div>
            <div class="text item">
              <span class="label">收貨人：</span>
              <span class="fl">
                  {{ addressItem.receiverName }}
                </span>
            </div>
            <div class="text item">
              <span class="label">所在地區：</span>
              <span class="fl">
                 {{ addressItem.area }}
                </span>

            </div>
            <div class="text item">
              <span class="label">地址：</span>
              <span class="fl">
                  {{ addressItem.detail }}
                </span>
              <div class="clr"></div>
            </div>

            <div class="text item">
              <span class="label">手機：</span>
              <span class="fl">
                  {{ addressItem.receiverPhone }}
                </span>
            </div>
          </el-card>
        </div>

        <div class="mt" id="addAddressDiv_bottom">
          <el-dialog title="添加收貨地址" :visible.sync="newAddressDialogVisible" width="500px"
                     :close-on-click-modal="false">
            <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="login-from">
              <el-form-item label="收貨人" prop="receiverName">
                <el-input v-model="ruleForm.receiverName" autocomplete="off" width="150px">
                </el-input>
              </el-form-item>
              <el-form-item label="所在城市" prop="area">
                <el-input v-model="ruleForm.cityCode" autocomplete="off" width="150px">
                </el-input>
              </el-form-item>
              <el-form-item label="詳細地址" prop="detail">
                <el-input v-model="ruleForm.detail" autocomplete="off" width="150px">
                </el-input>
              </el-form-item>
              <el-form-item label="手機號碼" prop="receiverPhone">
                <el-input v-model="ruleForm.receiverPhone" autocomplete="off" width="150px">
                </el-input>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
                <el-button @click="resetForm">重置</el-button>
              </el-form-item>
            </el-form>
          </el-dialog>
        </div>
      </div>
    </div>
  </div>

</template>

<script>
import FloatMallLinkComponent from '@/components/FloatMallLinkComponentView.vue';
import URLTitle from "@/http/URLTitle";
import TokenAxios from "@/http/TokenAxios";
import MallAPI from "@/http/apis/MallAPI";

export default {
  components: {
    FloatMallLinkComponent
  },
  data() {
    return {
      newAddressDialogVisible: false,
      ruleForm: {
        receiverName: '',
        receiverPhone: '',
        cityCode: '',
        detail: '',
      },
      // 表單規則
      rules: {
        receiverName: [
          {required: true, message: '請輸入收貨人', trigger: 'blur'},
        ],
        receiverPhone: [
          {required: true, message: '請輸入手機號碼', trigger: 'blur'},
        ],
        cityCode: [
          {required: true, message: '所請輸入在城市', trigger: 'blur'},
        ],
        detail: [
          {required: true, message: '請輸入詳細地址', trigger: 'blur'},
        ],
      },
      dialogTableVisible: false,
      addressList: [],
    }
  },
  methods: {
    // 網頁抬頭
    loadPageTitle() {
      this.pageTitle = 'Nike商城-收貨地址';
      document.title = this.pageTitle;
    },
    // 地址清單
    getAddressesList() {
      MallAPI.getAddressesList()
          .then((response)=>{
            const jsonResult = response.data;
            this.addressList = jsonResult.data
          })
          .catch((error) => {
            console.error('載入地址時發生錯誤：', error);
          });
    },
    // 新增
    submitForm(formName) {
      if (Array.isArray(this.ruleForm.userId)) {
        this.ruleForm.userId = this.ruleForm.userId.pop();
      }
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let url = URLTitle.FRONT_MALL_ADDRESSES_ADD;
          console.log('url = ' + url);

          TokenAxios.post(url,this.ruleForm).then((response) => {
            let jsonResult = response.data;
            if (jsonResult.state = 20000) {
              this.$message({
                message: '新增地址成功！',
                type: 'success'
              });
              this.resetForm(formName);
            } else {
              this.$alert(jsonResult.message, '錯誤', {
                confirmButtonText: '確定',
                callback: action => {
                }
              });
            }
          });
        } else {
          return false;
        }
      });
    },
    // 重置
    resetForm(formName) {
        this.$refs[formName].resetFields();
      },
    // 刪除
    deleteForm(){
      const params = {
        id: this.$router.currentRoute.query.id
      };
      MallAPI.DeleteAddressesByID(params)
          .then((response)=>{
            const jsonResult = response.data;
            this.addressList = jsonResult.data;
            this.total = jsonResult.data.total;
          })
    }
  },
  mounted() {
    this.loadPageTitle();
    this.getAddressesList();
  },
  created() {

  }
}
</script>

<style>
/*通用的去ul默認格式*/
ul, li {
  margin: 0;
  padding: 0;
  list-style: none;
}

a {
  color: #4caf50;;
}

body {
  font-family: "微軟雅黑", "黑體";
}

.main-container {
  text-align: left !important;
  width: 1200px;
  margin: 0 auto;
}

.mod-main {
  margin-top: 24px;
  padding: 10px;
  background-color: #fff;
  margin-bottom: 20px;
}

.mod-comm {
  padding: 10px 20px 20px;
}

.mod-comm .mt {
  padding: 10px 0;
}

a.add-btn {
  display: inline-block;
  vertical-align: middle;
  font-weight: 700;
  padding: 0 14px;
  height: 28px;
  line-height: 28px;
  font-size: 14px;
  margin: 0 10px 0 0;
}

.btn-5, .btn-6, .btn-7, .btn-8 {
  background-color: rgb(245, 251, 239);
  background-image: linear-gradient(rgb(245, 251, 239) 0px, rgb(234, 246, 226) 100%);
  border-radius: 2px;
  display: inline-block;
  height: 18px;
  line-height: 18px;
  border: 1px solid rgb(191, 214, 175);
  padding: 2px 14px 3px;
  color: rgb(50, 51, 51);
}

.ftx-03, .ftx03 {
  color: #999;
}

.ftx-02, .ftx02 {
  color: #71b247;
}

.mod-comm .mc {
  line-height: 20px;
}

.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.label {
  float: left;
  width: 70px;
  color: rgb(153, 153, 153);
  text-align: right;
}

.fl {

}

.el-button--text {
  border-color: transparent;
  color: rgb(0, 94, 167);
  background: 0 0;
  padding-left: 0;
  padding-right: 0;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both
}

.box-card {
  width: 100%;
  /*margin-bottom: 30px;*/
  border: 2px solid rgb(230, 230, 230);
  margin: 0px 0px 10px;
}

.default-add {
  margin: 0 0 0 10px;
  font-size: 12px;
  background: #ffaa45;
  padding: 2px;
  color: #fff;
  font-weight: 400;
}
</style>
