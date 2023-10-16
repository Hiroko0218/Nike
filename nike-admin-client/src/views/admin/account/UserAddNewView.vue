<template>
  <div>
    <!-- 頂部面包屑標識與導航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right" style="font-size: 16px;">
      <el-breadcrumb-item :to="{ path: '/' }"><i class="el-icon-s-promotion"></i> 後台管理</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/admin/account' }">帳號管理</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: './' }">用戶管理</el-breadcrumb-item>
      <el-breadcrumb-item>添加用戶</el-breadcrumb-item>
    </el-breadcrumb>

    <!-- 添加數據的表單 -->
    <el-divider content-position="left"></el-divider>
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="用戶名" prop="username">
        <el-input v-model="ruleForm.username"></el-input>
      </el-form-item>
      <el-form-item label="密碼" prop="password">
        <el-input v-model="ruleForm.password"></el-input>
      </el-form-item>
      <el-form-item label="頭像" prop="avatar">
        <el-input v-model="ruleForm.avatar"></el-input>
      </el-form-item>
      <el-form-item label="手機號碼" prop="phone">
        <el-input v-model="ruleForm.phone"></el-input>
      </el-form-item>
      <el-form-item label="電子郵箱" prop="email">
        <el-input v-model="ruleForm.email"></el-input>
      </el-form-item>
      <el-form-item label="簡介" prop="description">
        <el-input v-model="ruleForm.description"></el-input>
      </el-form-item>
      <el-form-item label="是否啟用">
        <el-switch
            v-model="ruleForm.enable"
            :active-value="1"
            :inactive-value="0"
            active-color="#13ce66"
            inactive-color="#999">
        </el-switch>
      </el-form-item>
      <el-form-item label="角色" prop="roleIds">
        <el-select v-model="ruleForm.roleIds" multiple placeholder="請選擇">
          <el-option
              v-for="item in roleListOptions"
              :key="item.id"
              :label="item.name"
              :value="item.id">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">添加</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
        <el-button type="warning" @click="gotoList">返回列表</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import BaseUrl from "@/http/BaseUrl";
import TokenAxios from "@/http/TokenAxios";

export default {
  data() {
    return {
      // 角色的下拉菜單的選項數據
      roleListOptions: [],
      // 表單
      ruleForm: {
        username: 'TestUser001',
        password: '123456',
        avatar: 'https://img2.baidu.com/it/u=4244269751,4000533845&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500',
        phone: '13700137001',
        email: '13700137001@163.com',
        description: '測試用戶001的簡介',
        enable: 1,
        roleIds: []
      },
      // 表單驗證規則
      rules: {
        username: [
          {required: true, message: '請輸入用戶名', trigger: 'blur'},
          {min: 4, max: 15, message: '長度在 4 到 15 個字符', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '請輸入密碼', trigger: 'blur'},
          {min: 4, max: 15, message: '長度在 4 到 15 個字符', trigger: 'blur'}
        ],
        avatar: [
          {required: true, message: '請輸入頭像的URL', trigger: 'blur'},
          {min: 10, max: 255, message: '長度在 10 到 255 個字符', trigger: 'blur'}
        ],
        phone: [
          {required: true, message: '請輸入手機號碼', trigger: 'blur'},
          {min: 8, max: 15, message: '長度在 8 到 15 個字符', trigger: 'blur'}
        ],
        email: [
          {required: true, message: '請輸入電子郵箱', trigger: 'blur'},
          {min: 8, max: 35, message: '長度在 8 到 35 個字符', trigger: 'blur'}
        ],
        description: [
          {required: true, message: '請輸入相冊簡介', trigger: 'blur'},
          {min: 2, max: 35, message: '長度在 4 到 35 個字符', trigger: 'blur'}
        ],
        roleIds: [
          {required: true, message: '請至少選擇1種角色', trigger: 'blur'}
        ]
      }
    };
  },
  methods: {
    // 返回列表
    gotoList() {
      this.$router.push('/admin/account/user');
    },
    // 提交表單
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let url = BaseUrl.ADMIN_ACCOUNT + '/users/add-new';
          console.log('url = ' + url);

          TokenAxios.post(url, this.ruleForm).then((response) => {
            let jsonResult = response.data;
            console.log(jsonResult);
            if (jsonResult.state == 20000) {
              this.$message({
                showClose: true,
                message: '添加用戶成功！',
                type: 'success'
              });
              this.resetForm(formName);
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
        } else {
          console.log('請求參數格式錯誤！');
          return false;
        }
      });
    },
    // 重置表單
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    // 加載角色列表
    loadRoleList() {
      let url = BaseUrl.ADMIN_ACCOUNT + '/roles';
      console.log('url = ' + url);

      TokenAxios.get(url).then((response) => {
        let jsonResult = response.data;
        if (jsonResult.state == 20000) {
          this.roleListOptions = jsonResult.data.list;
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
    this.loadRoleList();
  }
}
</script>