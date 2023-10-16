<template>
  <div>
    <!-- 浮動導航 -->
    <div class="float-link-container">
      <FloatMallLinkComponent/>
    </div>

    <!-- 頁面主體 -->
    <div class="main-container">
      <div class="user-about-main">
        <div class="user-about-main-inner">
          <el-menu :default-active="nowUserAboutId" class="el-menu-demo" mode="horizontal" @select="handleSelect">
            <el-menu-item index="1">修改密碼</el-menu-item>
            <el-menu-item index="2">個人資料</el-menu-item>
            <el-menu-item index="3">上傳頭像</el-menu-item>
          </el-menu>
          <el-form v-if="nowUserAboutId==='1'" :model="passwordForm" status-icon :rules="passwordRules"
                   label-width="100px" class="now-from">
            <el-form-item label="舊密碼" prop="oldPassword">
              <el-input prefix-icon="el-icon-key" v-model="passwordForm.oldPassword" autocomplete="off" width="150px">
              </el-input>
            </el-form-item>
            <el-form-item label="新密碼" prop="password">
              <el-input prefix-icon="el-icon-key" type="password" v-model="passwordForm.newPassword" autocomplete="off"
                        width="150px">
              </el-input>
            </el-form-item>
            <el-form-item label="確認密碼" prop="confirmPassword">
              <el-input prefix-icon="el-icon-key" type="password" v-model="passwordForm.confirmPassword"
                        autocomplete="off" width="150px">
              </el-input>
            </el-form-item>
            <el-form-item>
              <el-button class="from-bnt" type="primary" @click="submitForm('passwordForm')">提交</el-button>
            </el-form-item>
          </el-form>

          <el-form v-if="nowUserAboutId==='2'" :model="userInfoForm" status-icon :rules="userInfoForm"
                   label-width="100px" class="now-from">

          </el-form>
          <el-form v-if="nowUserAboutId==='3'" :model="userImgForm" status-icon :rules="userImgForm" label-width="100px"
                   class="now-from">
                <el-form label-width="80px">
                  <el-form-item>
<!--                    <img :src="userImgForm.url"-->
<!--                         style="float:left;width: 145px;height: 145px;border-radius: 10px">-->
                    <!--***************上傳組件開始******************-->
<!--                    <el-upload style="float: left"-->
<!--                               class="avatar-uploader"-->
<!--                               action="https://jsonplaceholder.typicode.com/posts/"-->
<!--                               :show-file-list="false"-->
<!--                               :on-success="handleAvatarSuccess"-->
<!--                               :before-upload="beforeAvatarUpload">-->
<!--                      <img v-if="userImgForm.url" :src="userImgForm.url" class="avatar">-->
<!--                      <i v-else class="el-icon-plus avatar-uploader-icon"></i>-->
<!--                      <i class="el-icon-plus"></i>-->
<!--                    </el-upload>-->
                    <el-upload style="float: left"
                               action="/v1/upload"
                               name="file"
                               limit="1"
                               list-type="picture-card"
                               :on-preview="handlePictureCardPreview"
                               :on-success="handleSuccess"
                               :on-remove="handleRemove">
                      <i class="el-icon-plus"></i>
                    </el-upload>
                    <el-dialog :visible.sync="dialogVisible">
                      <img width="100%" :src="dialogImageUrl" alt="">
                    </el-dialog>
                  </el-form-item>

                  <el-form-item>
                    <el-button type="primary" @click="submitForm('userImgForm')">上傳照片</el-button>
                  </el-form-item>
                </el-form>
          </el-form>
        </div>
      </div>
    </div>
  </div>

</template>

<script>
import FloatMallLinkComponent from '@/components/FloatMallLinkComponentView.vue';
import URLTitle from "@/http/URLTitle";
import SimpleAxios from "@/http/SimpleAxios";

export default {
  components: {
    FloatMallLinkComponent
  },
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('請輸入密碼'));
      } else {
        if (this.passwordForm.confirmPassword !== '') {
          this.$refs.passwordForm.validateField('confirmPassword');
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('請再次輸入密碼'));
      } else if (value !== this.passwordForm.newPassword) {
        callback(new Error('兩次輸入密碼不一致!'));
      } else {
        callback();
      }
    };
    return {
      nowUserAboutId: 1,
      dialogImageUrl: '',
      dialogVisible: false,
      // 注冊表單
      passwordForm: {
        oldPassword: '',
        newPassword: ''
      },
      userInfoForm: {
        url: ''
      },
      userImgForm: {},
      // 表單規則
      passwordRules: {
        oldPassword: [
          {required: true, message: '請輸入舊密碼', trigger: 'blur'},
          {min: 4, max: 15, message: '長度在 4 到 15 個字符', trigger: 'blur'},
        ],
        newPassword: [
          {required: true, message: '請輸入新密碼', trigger: 'blur'},
          {min: 4, max: 15, message: '長度在 4 到 15 個字符', trigger: 'blur'},
          {validator: validatePass, trigger: 'blur'}
        ],
        confirmPassword: [
          {required: true, message: '請輸入確認密碼', trigger: 'blur'},
          {min: 4, max: 15, message: '長度在 4 到 15 個字符', trigger: 'blur'},
          {validator: validatePass2, trigger: 'blur'}
        ]
      }
    }
  },
  methods: {
    // 網頁抬頭
    loadPageTitle() {
      this.pageTitle = 'Nick商城';
      document.title = this.pageTitle;
    },

    changeUserAboutId() {
      console.log(this.$router.currentRoute.query.id)
      this.nowUserAboutId = this.$router.currentRoute.query.id;
    },
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
      this.nowUserAboutId = key;
    },
    //提交數據
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if (formName === 'passwordForm') {
            let url = URLTitle.FRONT_ACCOUNT_USERS_PASSWORD_UPDATE;
            console.log('url = ' + url);

            SimpleAxios.post(url, this.passwordForm).then((response) => {
              let jsonResult = response.data;
              if (jsonResult.state = 20000) {
                this.$message({
                  message: '修改密碼成功！',
                  type: 'success'
                });

              } else {
                this.$alert(jsonResult.message, '錯誤', {
                  confirmButtonText: '確定',
                  callback: action => {
                  }
                });
              }
            });
          }
          if (formName === 'userInfoForm') {

          }
          if (formName === 'userImgForm') {
            let url = URLTitle.ATTACHMENT_UPLOAD_AVATAR;
            console.log('url = ' + url);

            SimpleAxios.post(url, this.userImgForm).then((response) => {
              let jsonResult = response.data;
              if (jsonResult.state = 20000) {
                this.$message({
                  message: '上傳頭像成功！',
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
          }
        } else {
          return false;
        }
      });
    },
    // 重置表單
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    handleAvatarSuccess(res, file) {
      this.imageUrl = URL.createObjectURL(file.raw);
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg';
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!');
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!');
      }
      return isJPG && isLt2M;
    }
  },
  mounted() {
    this.loadPageTitle();
  },
  created() {
    this.changeUserAboutId();
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
  color: #0D0D0D;;
}

body {
  font-family: "微軟雅黑", "黑體";
}

.main-container {
  text-align: left !important;
  width: 1200px;
  margin: 0 auto;
}

.user-about-main {
  margin-top: 24px;
  min-height: 400px;
  background-color: #fff;
  -webkit-border-radius: 24px;
  -moz-border-radius: 24px;
  -ms-border-radius: 24px;
  border-radius: 24px;
  overflow: hidden;
}

.user-about-main-inner {
  padding: 40px;
}

.now-from {
  padding: 20px 50px 20px 0px;
}

/**上傳頭像*/
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 145px;
  height: 145px;
  line-height: 145px;
  border-radius: 10px;
  text-align: center;
}
.avatar {
  width: 145px;
  height: 145px;
  display: block;
  border-radius: 10px;
}
</style>
