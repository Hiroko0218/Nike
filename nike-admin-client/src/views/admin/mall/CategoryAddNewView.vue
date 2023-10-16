<template>
  <div>
    <!-- 頂部的面包屑導航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right" style="font-size: 16px;">
      <el-breadcrumb-item :to="{ path: '/' }"><i class="el-icon-s-promotion"></i> 後台管理</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/admin/mall' }">商城管理</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/admin/mall/category' }">類別管理</el-breadcrumb-item>
      <el-breadcrumb-item>新增類別</el-breadcrumb-item>
    </el-breadcrumb>

    <!-- 表單 -->
    <el-divider content-position="left"></el-divider>
    <div class="rule-form">
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="130px" class="demo-ruleForm">
        <el-form-item label="父級類別" prop="parentId">
          <el-cascader
              style="width: 100%;"
              placeholder="請選擇父級類別，如不選擇，則表示無父級類別"
              :props="{ checkStrictly: true }"
              v-model="ruleForm.parentId"
              :options="parentCategoryOptions"></el-cascader>
          <span>如果父級類別列表不準確，請點擊 <a class="simple-link" href="#" @click="loadCategoryTree">這里</a> 刷新。</span>
        </el-form-item>
        <el-form-item label="名稱" prop="name">
          <el-input v-model="ruleForm.name"></el-input>
        </el-form-item>
        <el-form-item label="圖標" prop="icon">
          <el-input v-model="ruleForm.icon"></el-input>
        </el-form-item>
        <el-form-item label="關鍵詞列表" prop="keywords">
          <el-input v-model="ruleForm.keywords"></el-input>
        </el-form-item>
        <el-form-item label="排序序號" prop="sort">
          <el-input v-model="ruleForm.sort"></el-input>
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
        <el-form-item label="是否顯示在導航欄">
          <el-switch
              v-model="ruleForm.isDisplay"
              :active-value="1"
              :inactive-value="0"
              active-color="#13ce66"
              inactive-color="#999">
          </el-switch>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
          <el-button @click="resetForm('ruleForm')">重置</el-button>
          <el-button type="warning" @click="gotoList()">返回列表</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import BaseUrl from "@/http/BaseUrl";
import TokenAxios from "@/http/TokenAxios";

export default {
  data() {
    return {
      // 父級類別下拉列表選項
      parentCategoryOptions: [],
      // 表單
      ruleForm: {
        parentId: 0,
        name: '家電',
        icon: 'http://www.baidu.com/logo.png',
        keywords: '關鍵詞1,關鍵詞2,關鍵詞3',
        sort: 99,
        enable: 1,
        isDisplay: 1
      },
      // 表單驗證規則
      rules: {
        parentId: [
          {required: true, message: '請輸入父級類別ID', trigger: 'blur'},
          {pattern: /^\d+$/, message: '請選擇有效的父級類別', trigger: 'blur'}
        ],
        name: [
          {required: true, message: '請輸入類別名稱', trigger: 'blur'},
          {min: 1, max: 6, message: '類別名稱的長度必須是 1 到 6 個字符', trigger: 'blur'}
        ],
        keywords: [
          {required: true, message: '請輸入類別關鍵詞列表', trigger: 'blur'},
          {min: 2, max: 35, message: '長度在 2 到 35 個字符', trigger: 'blur'}
        ],
        icon: [
          {required: true, message: '請輸入類別圖標的URL', trigger: 'blur'},
          {min: 20, max: 255, message: '長度在 20 到 255 個字符', trigger: 'blur'}
        ],
        sort: [
          {required: true, message: '請輸入排序序號', trigger: 'blur'},
          {pattern: /^(\d{1}|[1-9]{1}[0-9]?)$/, message: '排序序號必須是 0~99 之間的數字', trigger: 'blur'}
        ]
      }
    };
  },
  methods: {
    // 返回列表
    gotoList() {
      this.$router.push('./');
    },
    // 提交表單
    submitForm(formName) {
      if (Array.isArray(this.ruleForm.parentId)) {
        this.ruleForm.parentId = this.ruleForm.parentId.pop();
      }
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let url = BaseUrl.ADMIN_MALL + '/categories/add-new';
          console.log('url = ' + url);

          TokenAxios.post(url, this.ruleForm).then((response) => {
            let jsonResult = response.data;
            if (jsonResult.state == 20000) {
              this.$message({
                message: '新增類別成功！',
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
    // 重置表單
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    // 加載類別樹的數據
    loadCategoryTree() {
      let url = BaseUrl.ADMIN_MALL + '/categories/tree';
      console.log('url = ' + url);

      TokenAxios.get(url).then((response) => {
        let jsonResult = response.data;
        if (jsonResult.state == 20000) {
          this.parentCategoryOptions = jsonResult.data;
          this.parentCategoryOptions.unshift({
            value: 0,
            label: '無父級類別'
          });
          this.ruleForm.parentId = "0";
        }
      });
    }
  },
  mounted() {
    this.loadCategoryTree();
  }
}
</script>

<style scoped>

</style>