<template>
  <div>
    <el-container>
      <!-- 頂部 -->
      <div id="shortcut">
        <div class="w" role="navigation" aria-label="頂部導航" tabindex="0">
          <ul class="fr">
            <li v-if="showLoginOrRegButton" id="ttbar-login" class="shortcut_btn fore1 dropdown">
              <a @click="checkLogin()" class="link-login" style="cursor:pointer">你好，請登入</a>&nbsp;&nbsp;
              <a @click="checkReg()" class="link-regist style-red" style="cursor:pointer">免費注冊</a>
            </li>
            <li v-else-if="!showLoginOrRegButton" id="ttbar-login" class="shortcut_btn fore1 dropdown">
              <a class="link-login" style="cursor:pointer">
                <el-dropdown>
                <span class="el-dropdown-link">
                 {{ nowUserName }} <i class="el-icon-setting el-icon--right"></i>
                </span>
                  <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item>
                      <a target="_blank" :href="'/userAbout?id=1'" aria-label="修改密碼">修改密碼</a></el-dropdown-item>
                    <el-dropdown-item>
                      <a target="_blank" :href="'/userAbout?id=2'" aria-label="個人資料">個人資料</a></el-dropdown-item>
                    <el-dropdown-item>
                      <a target="_blank" :href="'/userAbout?id=3'" aria-label="上傳頭像">上傳頭像</a></el-dropdown-item>
                    <el-dropdown-item>
                      <a target="_blank" :href="'/receiverAddresses'" aria-label="收貨地址">收貨地址
                      </a>
                    </el-dropdown-item>
                  </el-dropdown-menu>
                </el-dropdown>


              </a>&nbsp;&nbsp;
              <a @click="logout()" class="link-regist style-red" style="cursor:pointer">退出登入</a>&nbsp;&nbsp;
            </li>
            <li class="spacer"></li>
            <li v-if="!showLoginOrRegButton" class="shortcut_btn fore2">
              <div class="dt">
                <a target="_blank" :href="'/mall/myOrderList'"
                   aria-label="我的訂單">我的訂單
                </a>
              </div>
            </li>
            <li v-if="!showLoginOrRegButton" class="spacer"></li>
            <li v-if="!showLoginOrRegButton" class="shortcut_btn fore2">
              <div class="dt">
                <a target="_blank" :href="'/mall/CartLayout'"
                   aria-label="購物車">購物車
                </a>
              </div>
            </li>
          </ul>
        </div>
      </div>
      <el-header class="header-layout">

        <div class="header-container">
          <div class="header-wrap">
            <el-image class="logo" src="/logo.png"></el-image>
            <el-menu class="menu"
                     router
                     :default-active="activeMenuItem"
                     mode="horizontal"
                     background-color="#ffffff"
                     text-color="#666"
                     active-text-color="#0D0D0D">
              <el-menu-item :index="item.url"
                            v-for="item in categoryList" :key="item.url">
                <span v-text="item.name"></span>
              </el-menu-item>
              <el-menu-item index="/mall">
                <span>商城</span>
              </el-menu-item>
            </el-menu>
          </div>
        </div>
      </el-header>
      <!-- 主體 -->
      <el-main class="main-layout">
        <router-view/>
      </el-main>
      <!-- 底部 -->
      <el-footer class="footer-layout">
        <div class="footer-container">
          <div class="footer-wrap">
            <div class="copyright" v-html="copyright"></div>
          </div>
        </div>
      </el-footer>
    </el-container>
    <el-dialog :title="dialogTitile" :visible.sync="dialogTableVisible" width="500px" :close-on-click-modal="false">

      <el-form v-if="dialogTitile==='登入'" :model="ruleForm" status-icon :rules="rules" ref="ruleForm"
               label-width="100px" class="login-from">
        <el-form-item label="帳號" prop="username">
          <el-input prefix-icon="el-icon-user" v-model="ruleForm.username" autocomplete="off" width="150px">
          </el-input>
        </el-form-item>
        <el-form-item label="密碼" prop="password">
          <el-input prefix-icon="el-icon-key" type="password" v-model="ruleForm.password" autocomplete="off"
                    width="150px">
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-button class="login-bnt" type="primary" @click="submitForm('ruleForm')">提交</el-button>
          <div class="g-font12 g-c-9 g-center g-m10-bt">登入即代表同意
            <a href="" target="_blank">《隱私政策》</a>
          </div>
        </el-form-item>
      </el-form>

      <el-form v-if="dialogTitile==='注冊'" :model="regForm" status-icon :rules="regRules" ref="regForm"
               label-width="100px" class="login-from">
        <el-form-item label="用戶名" prop="username">
          <el-input prefix-icon="el-icon-user" v-model="regForm.username" autocomplete="off" width="150px">
          </el-input>
        </el-form-item>
        <el-form-item label="手機號碼" prop="phone">
          <el-input prefix-icon="el-icon-user" v-model="regForm.phone" autocomplete="off" width="150px">
          </el-input>
        </el-form-item>
        <el-form-item label="電子郵箱" prop="email">
          <el-input prefix-icon="el-icon-user" v-model="regForm.email" autocomplete="off" width="150px">
          </el-input>
        </el-form-item>
        <el-form-item label="密碼" prop="password">
          <el-input prefix-icon="el-icon-key" type="password" v-model="regForm.password" autocomplete="off"
                    width="150px">
          </el-input>
        </el-form-item>
        <el-form-item label="確認密碼" prop="confirmPassword">
          <el-input prefix-icon="el-icon-key" type="password" v-model="regForm.confirmPassword" autocomplete="off"
                    width="150px">
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-button class="login-bnt" type="primary" @click="submitForm('regForm')">提交</el-button>
          <div class="g-font12 g-c-9 g-center g-m10-bt">注冊即代表同意
            <a href="" target="_blank">《隱私政策》</a>
          </div>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import UserAPI from "@/http/apis/UserAPI";
import URLTitle from "@/http/URLTitle";
import SimpleAxios from "@/http/SimpleAxios";

export default {
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('請輸入密碼'));
      } else {
        if (this.regForm.confirmPassword !== '') {
          this.$refs.regForm.validateField('confirmPassword');
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('請再次輸入密碼'));
      } else if (value !== this.regForm.password) {
        callback(new Error('兩次輸入密碼不一致!'));
      } else {
        callback();
      }
    };
    return {
      dialogTitile: "登入",
      activeMenuItem: this.$router.currentRoute.path,
      categoryList: [],
      showLoginOrRegButton: true,
      copyright: '',
      nowUserName: '',
      dialogTableVisible: false,
      // 登入表單
      ruleForm: {
        username: 'root',
        password: '123456'
      },
      // 表單規則
      rules: {
        username: [
          {required: true, message: '請輸入用戶名', trigger: 'blur'},
          {min: 4, max: 15, message: '長度在 4 到 15 個字符', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '請輸入密碼', trigger: 'blur'},
          {min: 4, max: 15, message: '長度在 4 到 15 個字符', trigger: 'blur'}
        ]
      },
      // 注冊表單
      regForm: {
        avatar: null,
        confirmPassword: '123456',
        email: 'student@tedu.cn',
        password: '123456',
        phone: '13800138008',
        username: 'root',
      },
      // 表單規則
      regRules: {
        username: [
          {required: true, message: '請輸入用戶名', trigger: 'blur'},
          {min: 4, max: 15, message: '長度在 4 到 15 個字符', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '請輸入密碼', trigger: 'blur'},
          {min: 4, max: 15, message: '長度在 4 到 15 個字符', trigger: 'blur'},
          {validator: validatePass, trigger: 'blur'}
        ],
        confirmPassword: [
          {required: true, message: '請輸入確認密碼', trigger: 'blur'},
          {min: 4, max: 15, message: '長度在 4 到 15 個字符', trigger: 'blur'},
          {validator: validatePass2, trigger: 'blur'}
        ],
        phone: [
          {required: true, message: '請輸入手機號', trigger: 'blur'},
          {pattern: /^\d{11}$/, message: '手機號碼必須是11位的純數字', trigger: 'blur'}
        ],
        email: [
          {required: true, message: '請輸入郵箱', trigger: 'blur'},
          {
            pattern: /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/,
            message: '請輸入正確格式的電子郵箱',
            trigger: 'blur'
          }
        ]
      }
    }
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if (formName === 'ruleForm') {
            let url = URLTitle.PASSPORT_LOGIN;
            console.log('url = ' + url);

            SimpleAxios.post(url, this.ruleForm).then((response) => {
              let jsonResult = response.data;
              if (jsonResult.state = 20000) {
                let loginInfo = jsonResult.data;
                localStorage.setItem('loginInfo', JSON.stringify(loginInfo));
                console.log('已經將登錄信息存入到localStorage中，即將跳轉頁面……');
                // this.showLoginOrRegButton = false;
                // this.dialogTableVisible = false;
                this.$router.go(0);
              }
            });
          }
          if (formName === 'regForm') {
            let url = URLTitle.FRONT_ACCOUNT_USERS_REGISTER;
            console.log('url = ' + url);

            SimpleAxios.post(url, this.regForm).then((response) => {
              let jsonResult = response.data;
              if (jsonResult.state = 20000) {
                this.checkLogin();
                this.dialogTableVisible = false;
              }
            });
          }
        } else {
          return false;
        }
      });
    },
    checkLogin() {
      this.dialogTableVisible = true;
      this.dialogTitile = "登入";
    },
    checkReg() {
      this.dialogTableVisible = true;
      this.dialogTitile = "注冊";
    },
    logout() {
      localStorage.setItem('loginInfo', '');
      this.showLoginOrRegButton = true;
      this.$router.go(0);
    },
    /**
     * 初始化檢查是否攜帶登入信息
     */
    toCheckLogged() {
      let loginInfo = localStorage.getItem('loginInfo');
      if (loginInfo !== null) {
        if (loginInfo !== '') {
          this.showLoginOrRegButton = false;
          this.nowUserName = JSON.parse(loginInfo).username;
        } else {
          this.showLoginOrRegButton = true;
        }
      } else {
        this.showLoginOrRegButton = true;

      }
    },
    loadCategoryList() {
      let categoryList = [
        {id: 1, name: '推薦', url: '/recommend'},
        {id: 2, name: '跑步', url: '/run'},
        {id: 3, name: '足球', url: '/soccer'},
        {id: 4, name: '籃球', url: '/basketball'},
        {id: 5, name: '健身與訓練', url: '/workout'},
        {id: 6, name: '瑜珈', url: '/yoga'},
        {id: 7, name: '滑板', url: '/skateboard'},
        {id: 8, name: '網球', url: '/tennis'},
      ];
      this.categoryList = categoryList;
    },
    loadCopyright() {
      let copyright = '<p>版權所有：達內時代科技集團, IT學院, Java教學研發部</p>' +
          '<p>備案號：京ICP備17010360號（模擬數據）' +
          '<img style="position: relative; top: 5px; margin-left: 20px; margin-right: 5px;" ' +
          'src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABQAAAAWCAYAAADAQbwGAAAACXBIWXMAAAsTAAALEwEAmpwYAAA5z2lUWHRYTUw6Y29tLmFkb2JlLnhtcAAAAAAAPD94cGFja2V0IGJlZ2luPSLvu78iIGlkPSJXNU0wTXBDZWhpSHpyZVN6TlRjemtjOWQiPz4KPHg6eG1wbWV0YSB4bWxuczp4PSJhZG9iZTpuczptZXRhLyIgeDp4bXB0az0iQWRvYmUgWE1QIENvcmUgNS41LWMwMTQgNzkuMTUxNDgxLCAyMDEzLzAzLzEzLTEyOjA5OjE1ICAgICAgICAiPgogICA8cmRmOlJERiB4bWxuczpyZGY9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkvMDIvMjItcmRmLXN5bnRheC1ucyMiPgogICAgICA8cmRmOkRlc2NyaXB0aW9uIHJkZjphYm91dD0iIgogICAgICAgICAgICB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iCiAgICAgICAgICAgIHhtbG5zOnhtcE1NPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvbW0vIgogICAgICAgICAgICB4bWxuczpzdEV2dD0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL3NUeXBlL1Jlc291cmNlRXZlbnQjIgogICAgICAgICAgICB4bWxuczpkYz0iaHR0cDovL3B1cmwub3JnL2RjL2VsZW1lbnRzLzEuMS8iCiAgICAgICAgICAgIHhtbG5zOnBob3Rvc2hvcD0iaHR0cDovL25zLmFkb2JlLmNvbS9waG90b3Nob3AvMS4wLyIKICAgICAgICAgICAgeG1sbnM6dGlmZj0iaHR0cDovL25zLmFkb2JlLmNvbS90aWZmLzEuMC8iCiAgICAgICAgICAgIHhtbG5zOmV4aWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20vZXhpZi8xLjAvIj4KICAgICAgICAgPHhtcDpDcmVhdG9yVG9vbD5BZG9iZSBQaG90b3Nob3AgQ0MgKFdpbmRvd3MpPC94bXA6Q3JlYXRvclRvb2w+CiAgICAgICAgIDx4bXA6Q3JlYXRlRGF0ZT4yMDE5LTA5LTIzVDEyOjA5OjI4KzA4OjAwPC94bXA6Q3JlYXRlRGF0ZT4KICAgICAgICAgPHhtcDpNZXRhZGF0YURhdGU+MjAxOS0wOS0yM1QxMjowOToyOCswODowMDwveG1wOk1ldGFkYXRhRGF0ZT4KICAgICAgICAgPHhtcDpNb2RpZnlEYXRlPjIwMTktMDktMjNUMTI6MDk6MjgrMDg6MDA8L3htcDpNb2RpZnlEYXRlPgogICAgICAgICA8eG1wTU06SW5zdGFuY2VJRD54bXAuaWlkOjg0OTNiNTZlLWM2MmMtY2I0Zi1hMzBmLWM5ZDk3MGNjZTkyMjwveG1wTU06SW5zdGFuY2VJRD4KICAgICAgICAgPHhtcE1NOkRvY3VtZW50SUQ+eG1wLmRpZDo5MmFhMDM0YS1lMDk3LTc3NDktYWI1YS03ZTJiMTI1MTk2ZjY8L3htcE1NOkRvY3VtZW50SUQ+CiAgICAgICAgIDx4bXBNTTpPcmlnaW5hbERvY3VtZW50SUQ+eG1wLmRpZDo5MmFhMDM0YS1lMDk3LTc3NDktYWI1YS03ZTJiMTI1MTk2ZjY8L3htcE1NOk9yaWdpbmFsRG9jdW1lbnRJRD4KICAgICAgICAgPHhtcE1NOkhpc3Rvcnk+CiAgICAgICAgICAgIDxyZGY6U2VxPgogICAgICAgICAgICAgICA8cmRmOmxpIHJkZjpwYXJzZVR5cGU9IlJlc291cmNlIj4KICAgICAgICAgICAgICAgICAgPHN0RXZ0OmFjdGlvbj5jcmVhdGVkPC9zdEV2dDphY3Rpb24+CiAgICAgICAgICAgICAgICAgIDxzdEV2dDppbnN0YW5jZUlEPnhtcC5paWQ6OTJhYTAzNGEtZTA5Ny03NzQ5LWFiNWEtN2UyYjEyNTE5NmY2PC9zdEV2dDppbnN0YW5jZUlEPgogICAgICAgICAgICAgICAgICA8c3RFdnQ6d2hlbj4yMDE5LTA5LTIzVDEyOjA5OjI4KzA4OjAwPC9zdEV2dDp3aGVuPgogICAgICAgICAgICAgICAgICA8c3RFdnQ6c29mdHdhcmVBZ2VudD5BZG9iZSBQaG90b3Nob3AgQ0MgKFdpbmRvd3MpPC9zdEV2dDpzb2Z0d2FyZUFnZW50PgogICAgICAgICAgICAgICA8L3JkZjpsaT4KICAgICAgICAgICAgICAgPHJkZjpsaSByZGY6cGFyc2VUeXBlPSJSZXNvdXJjZSI+CiAgICAgICAgICAgICAgICAgIDxzdEV2dDphY3Rpb24+c2F2ZWQ8L3N0RXZ0OmFjdGlvbj4KICAgICAgICAgICAgICAgICAgPHN0RXZ0Omluc3RhbmNlSUQ+eG1wLmlpZDo4NDkzYjU2ZS1jNjJjLWNiNGYtYTMwZi1jOWQ5NzBjY2U5MjI8L3N0RXZ0Omluc3RhbmNlSUQ+CiAgICAgICAgICAgICAgICAgIDxzdEV2dDp3aGVuPjIwMTktMDktMjNUMTI6MDk6MjgrMDg6MDA8L3N0RXZ0OndoZW4+CiAgICAgICAgICAgICAgICAgIDxzdEV2dDpzb2Z0d2FyZUFnZW50PkFkb2JlIFBob3Rvc2hvcCBDQyAoV2luZG93cyk8L3N0RXZ0OnNvZnR3YXJlQWdlbnQ+CiAgICAgICAgICAgICAgICAgIDxzdEV2dDpjaGFuZ2VkPi88L3N0RXZ0OmNoYW5nZWQ+CiAgICAgICAgICAgICAgIDwvcmRmOmxpPgogICAgICAgICAgICA8L3JkZjpTZXE+CiAgICAgICAgIDwveG1wTU06SGlzdG9yeT4KICAgICAgICAgPGRjOmZvcm1hdD5pbWFnZS9wbmc8L2RjOmZvcm1hdD4KICAgICAgICAgPHBob3Rvc2hvcDpDb2xvck1vZGU+MzwvcGhvdG9zaG9wOkNvbG9yTW9kZT4KICAgICAgICAgPHRpZmY6T3JpZW50YXRpb24+MTwvdGlmZjpPcmllbnRhdGlvbj4KICAgICAgICAgPHRpZmY6WFJlc29sdXRpb24+NzIwMDAwLzEwMDAwPC90aWZmOlhSZXNvbHV0aW9uPgogICAgICAgICA8dGlmZjpZUmVzb2x1dGlvbj43MjAwMDAvMTAwMDA8L3RpZmY6WVJlc29sdXRpb24+CiAgICAgICAgIDx0aWZmOlJlc29sdXRpb25Vbml0PjI8L3RpZmY6UmVzb2x1dGlvblVuaXQ+CiAgICAgICAgIDxleGlmOkNvbG9yU3BhY2U+NjU1MzU8L2V4aWY6Q29sb3JTcGFjZT4KICAgICAgICAgPGV4aWY6UGl4ZWxYRGltZW5zaW9uPjIwPC9leGlmOlBpeGVsWERpbWVuc2lvbj4KICAgICAgICAgPGV4aWY6UGl4ZWxZRGltZW5zaW9uPjIyPC9leGlmOlBpeGVsWURpbWVuc2lvbj4KICAgICAgPC9yZGY6RGVzY3JpcHRpb24+CiAgIDwvcmRmOlJERj4KPC94OnhtcG1ldGE+CiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgCjw/eHBhY2tldCBlbmQ9InciPz4jh4n+AAAAIGNIUk0AAHolAACAgwAA+f8AAIDpAAB1MAAA6mAAADqYAAAXb5JfxUYAAATMSURBVHjarNVbbBR1FMfx78zOzs5Od7v3btcuEmgFilyqgNdaBI1cAgUxIWCMikFEjRqJPGh8IRgDLxjReAliFEKMCglJIQSoNcolgjEQAbmUUqC7pbS73XZnd2d3ZmfHB7GKwfji7+08/D/5n+T/P0ewbZv/MxJAme4bRTVQBcZxOvcd4I5FL9YWLp1o/X7fz8+X9LL88GNTtwSnTNl9dtdXicaFS0CuB/pJX9gNQGjcmj/AW2QWsPqLF19vtfVOpaek4gj4Kew894H+Sdt7zY+MbQM+B/b886B4C0zp+Hhrx9Or2pfmileUcR4HK5XrrFZ6eOD+KMH7o9LKVQcfP7B5Qxvg+i8wXjqxfv/7O4ZZvnIsz8az+D49Q/q8RbrLYnjBJyyyTvHatmVs2WVin//sW0D5N7Dx6p63u1oWnGrxN0R5OXyK7h1ZxFF+LM2AUgUh4ufoK0dYqp4g+uBdPLpg78KBw1svAeNvAvNnu5CK+z1t573y8d7pzJuukTuZRbw6jOB34Rq0KB65jnNMCCWnk/ryGEvmGHRcnMj2Q1bMI+TdwTHP3HTDeOWn7zZVBDdIBaq1XrJ94LRMGCwi1KvI0/yI1/LIVEh169QFSyAIZHIiroi0CRgzAl45d/AJI5lsDnhkKLuwYzHCzbXksNEeCGEtj+NZ14T62hTEiErNtDD5ggG2TaxGhcTJWYM9R58bAVVPoDGrmYyNFEEJceCYijS7mvTC0VhJDeHXPIW91zG7h5DXNhN8fSo7t2WAKpomS+S7+ikmuuIjYGWwd2E2IzEprhG/R+aj7VX8sCFB04wYPtVL4UyGSn+J4qkM3uQQp9dfYPM2F9EmP5PrhkklS5iD55pHQFHK+7WsSPXABZbOsSibHsw9fYS+/gWxzokRcVCpkQjPjhHruERqy2nyhoMlrU686U60rITkFZ0jX8+t3t4ujr/cakXrWHGvxqa6CB8lGzF7zhBKdyL63OhnMmgNNSQDMb4R3VDtZ8V9/ZS9cfx3CqiBUXtHQBBr7KxNb5fGpMZu3nljGm+vbWGPNhmyJq2FNEG/yM69XnKoQIA1LynMaOiktxMoFqiUtakjoOQ3ppe9Ucw+k0T/WV5tdrGkvYqOfQXOiVOYO/EiMXeKclsdRkZn1ASVt+Ze5vrRyxRMBSUsI/nlCQCCbduUcsefTB/buEPvMyilTEhcoX5xE66oAlYaKy2R69fxjQnC6BAkUnTvPo/uDeMZ68Fdq+JrWD5TDsz/8U8Qh+B6avD0pg+tou5LHepDqrKpndmINahjFvKIqoISrEKQnaSPdlG2JHzT6zGLpH3jW5bJTrVdDsz/o+VSqgLoyWKfW1eiFV90dgPWcB5Ly4Iq45D9yG4whnLkExmU0SGUeJTSUIFiUrPdo3IY5AgGbjwbB4dxcPjNmru9tc5AjNJwhYolUbEcFAc0sCsY6WFynQMIDicIAsVrKZxukehDsbCsai/IqvbXxHaFZgAszvQe2mgkflshOwpVashFOW+AYOIoDVHWDbwTwjgUB4aeQ6wKIwRqr5al+i2KGnn3phVwIwXglczZ6Dp9eGBeyeyZedvEyMSKJngUvxiU3F60a1x1R/wFqabl5GBXf7t+JXNg9ENU/j4Phf97Sf0+ADr3CMhtMiuNAAAAAElFTkSuQmCC"/>' +
          '京公網安備 110111010110號（模擬數據）</p>' +
          '<p><span>聯系郵箱：java@tedu.cn</span>' +
          '<span style="margin-left: 30px;">公眾號：達內教育</span></p>' +
          '<p>建議您使用Chrome、Firefox、Edge、IE10及以上版本和360等主流瀏覽器瀏覽本網站</p>';
      this.copyright = copyright;
    },
    checkActiveMenuItem() {
      if (this.$store.state.articleCategoryLink) {
        this.activeMenuItem = this.$store.state.articleCategoryLink;
      }
    }
  },
  created() {
    this.loadCategoryList();
    this.loadCopyright();
    this.toCheckLogged();
  },
  mounted() {
    this.toCheckLogged();
    this.checkActiveMenuItem();
  }
}
</script>

<style>

/*登入注冊*/
#shortcut {
  border-bottom: 1px solid #ddd;
  background-color: #e3e4e5;
}

#shortcut .w {
  height: 30px;
  line-height: 30px;
  color: #999;
}

.fr {
  float: right;
}

#shortcut li {
  float: left;
}

#ttbar-login {
  margin-right: 8px;
  z-index: 20;
}

#ttbar-login a {
  color: #0D0D0D;
}

#shortcut a {
  color: #999;
}

#shortcut .style-red {
  color: #f10215;
}

#shortcut li.spacer {
  overflow: hidden;
  margin: 11px 5px 0;
  width: 1px;
  height: 10px;
  background-color: #ccc;
}

.login-from {
  padding: 20px 50px 20px 0px;
}

.login-bnt {
  background-image: linear-gradient(90deg, #404040, #0D0D0D) !important;
  height: 45px;
  width: 100%;
  line-height: 45px;
  margin-top: 30px;
  border-radius: 50px !important;
  font-size: 16px;
  text-align: center;
  color: #fff;
}

/*導航欄*/
.header-layout {
  height: 100px !important;
  background: #fff;
  text-align: center;
  padding: 0;
}

.header-wrap {
  width: 1200px;
  margin: 30px auto 0 auto;
}

.header-wrap .logo {
  width: 160px;
  height: 50px;
  float: left;
}

.header-wrap .menu {
  margin-left: 200px;
}

.main-layout {
  text-align: center;
  padding: 0 !important;
}

.main-container {
  margin-top: 20px;
}

.main-wrap {
  width: 1200px;
  margin: 0 auto;
  padding-top: 10px;
}

.main-wrap .nav-breadcrumb {
  margin-bottom: 20px;
}

.footer-layout {
  text-align: center;
}

.footer-wrap {
  width: 1200px;
  margin: 20px auto 0 auto;
  padding: 10px 0 30px 0;
  border-top: 2px solid #0D0D0D !important;
}

.footer-wrap .copyright {
  color: dimgray;
  font-size: 13px;
}

.footer-wrap .copyright p {
  line-height: 30px;
}

.float-link-container {
  position: fixed;
  right: 30px;
  bottom: 30px;
  z-index: 1;
}

.left-aside-container {
  margin-right: 5px;
  width: 875px;
  float: left;
}

.left-aside-wrap {

}

.left-aside-wrap .article-list {
  background: #fff;
  border-radius: 5px;
  padding: 35px;
}

.article-list-item .left .image {
  width: 220px;
  height: 150px;
  border-radius: 5px;
  float: left;
}

.article-list-item .right {
  margin-left: 240px;
  text-align: left;
}

.article-list-item .right .title {
  padding: 6px 0;
  font-size: 20px;
}

.article-list-item .right .title a {
  display: -webkit-box;
  overflow: hidden;
  text-overflow: ellipsis;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 1;
  color: #000;
}

.article-list-item .right .title a:hover {
  color: #666;
}

.article-list-item .right .content {
  margin: 25px 0;
  display: -webkit-box;
  overflow: hidden;
  text-overflow: ellipsis;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 2;
  font-size: 14px;
  color: #333;
}

.article-list-item .right .tag {
  font-size: 13px;
  font-style: normal;
  color: #999;
  margin-right: 3px;
}

.article-list-item .right .up {
  font-size: 13px;
  font-style: normal;
  color: #999;
  margin-right: 20px;
}

.article-list-item .right .down {
  font-size: 13px;
  font-style: normal;
  color: #999;
  margin-right: 20px;
}

.article-list-item .right .date {
  font-size: 13px;
  font-style: normal;
  color: #999;
}

.btn-load-more {
  width: 200px;
}

.el-menu--horizontal {
  border: none !important;
}

.el-menu-item {
  font-size: 20px !important;
}

.el-menu-item.is-active {
  background: transparent !important;
  border-bottom: 2px solid #0D0D0D !important;
}

.right-aside-container {
  width: 300px;
  padding-left: 900px;
}

.el-menu-item:hover {
  color: #0D0D0D !important;
  background: transparent !important;
}
</style>