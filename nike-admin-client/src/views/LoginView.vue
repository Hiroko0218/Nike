<template>
  <div>
    <!-- 整個頁面需要顯示內容的區域 -->
    <div class="container">
      <!-- 頂部區域 -->
      <div class="header">
        <h1>學茶商城運營管理平台</h1>
      </div>
      <!-- 登入表單 -->
      <div class="login-form">
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="0px" class="demo-ruleForm">
          <el-form-item label="" prop="username">
            <el-input v-model="ruleForm.username">
              <template slot="prepend"><i class="el-icon-user-solid"/></template>
            </el-input>
          </el-form-item>
          <el-form-item label="" prop="password">
            <el-input v-model="ruleForm.password">
              <template slot="prepend"><i class="el-icon-s-ticket"/></template>
            </el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" style="width: 100%;"
                       @click="submitForm('ruleForm')">登入</el-button>
          </el-form-item>
        </el-form>
      </div>
      <!-- 版權信息 -->
      <div class="copyright">
        <p>Copyright &copy; 達內時代科技集團 - IT學院 - Java教學研發部</p>
      </div>
    </div>
  </div>
</template>

<script>
import BaseUrl from "@/http/BaseUrl";
import SimpleAxios from "@/http/SimpleAxios";

export default {
  data() {
    return {
      ruleForm: {
        username: 'root',
        password: '123456'
      },
      rules: {
        username: [
          {required: true, message: '請輸入用戶名', trigger: 'blur'},
          {min: 4, max: 15, message: '用戶名的長度必須是 4 到 15 個字符', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '請輸入密碼', trigger: 'blur'},
          {min: 4, max: 15, message: '密碼的長度必須是 4 到 15 個字符', trigger: 'blur'}
        ]
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let url = BaseUrl.PASSPORT + '/passport/login';
          console.log('url = ' + url);

          SimpleAxios.post(url, this.ruleForm).then((response) => {
            let jsonResult = response.data;
            if (jsonResult.state == 20000) {
              this.$message({
                message: '登入成功！',
                type: 'success'
              });
              let loginInfo = jsonResult.data;
              localStorage.setItem('loginInfo', JSON.stringify(loginInfo));
              // 跳轉：this.$router.push('/xxx');
              this.$router.push('/');
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
    }
  }
}
</script>

<style>
body {
  height: 100vh;
  background-image: linear-gradient(to bottom left, #5abae8, #3c4bd2);
  background-repeat: no-repeat;
}

.container {
  width: 460px;
  margin: 0 auto;
}

.header {
  margin-top: 50px;
}

.header h1 {
  text-align: center;
  color: #fff;
}

.login-form {
  background: #fff;
  border-radius: 10px;
  padding: 50px 50px 20px 50px;
}

.copyright {
  margin-top: 20px;
}

.copyright p {
  text-align: center;
  font-size: 11px;
  color: #fff;
}
</style>