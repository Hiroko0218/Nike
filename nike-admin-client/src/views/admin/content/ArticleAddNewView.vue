<template>
  <div>
    <!-- 頂部的面包屑導航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right" style="font-size: 16px;">
      <el-breadcrumb-item :to="{ path: '/' }"><i class="el-icon-s-promotion"></i> 後台管理</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: './' }">內容管理</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: './' }">文章管理</el-breadcrumb-item>
      <el-breadcrumb-item>新增類別</el-breadcrumb-item>
    </el-breadcrumb>

    <!-- 表單 -->
    <el-divider content-position="left"></el-divider>
    <div class="rule-form">
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="120px" class="demo-ruleForm">
        <el-form-item label="類別" prop="categoryId">
          <el-select v-model="id" clearable placeholder="請選擇文章類別" @change="updateCategoryId">
            <el-option
                v-for="item in options"
                :key="item.id"
                :label="item.name"
                :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="封面圖" prop="coverUrl">
          <el-input v-model="ruleForm.coverUrl"></el-input>
        </el-form-item>
        <el-form-item label="標題" prop="title">
          <el-input v-model="ruleForm.title"></el-input>
        </el-form-item>
        <el-form-item label="簡介" prop="brief">
          <el-input v-model="ruleForm.brief"></el-input>
        </el-form-item>
        <el-form-item label="關鍵詞列表" prop="keywords">
          <el-input v-model="ruleForm.keywords"></el-input>
        </el-form-item>
        <el-form-item label="排序序號" prop="sort">
          <el-input v-model="ruleForm.sort"></el-input>
        </el-form-item>
        <el-form-item label="詳情" prop="detail">
          <el-input v-model="ruleForm.detail"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
          <el-button @click="resetForm('ruleForm')">重置</el-button>
          <el-button type="warning" @click="gotoList()">返回</el-button>
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
      // 表單
      ruleForm: {
        categoryId: '',
        coverUrl: 'http://localhost:19090/resources/goods-image/2023/06/01/3bcd52f4-1a7c-4f6f-8246-6208c5e03616.jpg',
        title: '2023年明前特級信陽毛尖綠茶 250克（125g*2罐）',
        brief: '細、圓、緊、直 鮮爽持久',
        keywords: '關鍵詞1,關鍵詞2,關鍵詞3',
        sort: 99,
        detail: ''
      },
      // 類別下拉列表選項
      options: [],
      id:'',
      // 表單驗證規則
      rules: {
        categoryId: [
          {required: true, message: '請輸入類別', trigger: 'blur'},
          {pattern: /^\d+$/, message: '請選擇有效的類別', trigger: 'blur'}
        ],
        coverUrl: [
          {required: true, message: '請上傳封面圖', trigger: 'blur'}
        ],
        title: [
          {required: true, message: '請輸入標題', trigger: 'blur'},
          {min: 2, max: 255, message: '標題的長度必須是 2 到 255 個字符', trigger: 'blur'}
        ],
        brief: [
          {required: true, message: '請輸入簡介', trigger: 'blur'},
          {min: 2, max: 255, message: '簡介的長度必須是 2 到 255 個字符', trigger: 'blur'}
        ],
        keywords: [
          {required: true, message: '請輸入類別關鍵詞列表', trigger: 'blur'},
          {min: 2, max: 35, message: '長度在 2 到 35 個字符', trigger: 'blur'}
        ],
        sort: [
          {required: true, message: '請輸入排序序號', trigger: 'blur'},
          {pattern: /^(\d{1}|[1-9]{1}[0-9]?)$/, message: '排序序號必須是 0~99 之間的數字', trigger: 'blur'}
        ],
        detail: [
          {required: true, message: '請輸入商品詳情', trigger: 'blur'}
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
      if (Array.isArray(this.ruleForm.categoryId)) {
        this.ruleForm.categoryId= this.ruleForm.categoryId.pop();
      }
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let url = BaseUrl.ADMIN_CONTENT + '/articles/add-new';
          console.log('url = ' + url);

          TokenAxios.post(url, this.ruleForm).then((response) => {
            let jsonResult = response.data;
            if (jsonResult.state == 20000) {
              this.$message({
                message: '發表文章成功！',
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
    // 加載類別數據
    loadCategory() {
      let url = BaseUrl.ADMIN_CONTENT+ '/categories/';
      console.log('url = ' + url);

      TokenAxios.get(url).then((response) => {
        let jsonResult = response.data;
        if (jsonResult.state == 20000) {
          this.options = jsonResult.data.list;
          this.total = jsonResult.data.total;
        }
      });
    },
    // 更新類別數據
    updateCategoryId(){
      this.ruleForm.categoryId = this.id;
    }
  },
  mounted() {
    this.loadCategory();
  }
}
</script>

<style scoped>

</style>