<template>
  <div>
    <!-- 外層容器 -->
    <el-container>
      <!-- 上半區域：頂欄 -->
      <el-header class="layout-header">
        <!-- 頂欄右側的當前用戶信息及下拉菜單 -->
        <div class="login-user">
          <span class="welcome">歡迎回來，<b>{{ loginInfo.username }}</b>！</span>
          <el-dropdown @command="handleCommand">
            <el-avatar shape="square" size="medium" :src="loginInfo.avatar"></el-avatar>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item icon="el-icon-document" command="openEditInfoDialog">修改資料</el-dropdown-item>
              <el-dropdown-item icon="el-icon-coordinate" command="openEditAvatarDialog">修改頭像</el-dropdown-item>
              <el-dropdown-item icon="el-icon-lock" command="openEditPasswordDialog">修改密碼</el-dropdown-item>
              <el-dropdown-item icon="el-icon-right" divided command="openLogoutConfirm">退出登入</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
        <!-- 頂欄的標題文字 -->
        <h1>學茶商城運營管理平台</h1>
      </el-header>
      <!-- 下半區域 -->
      <el-container class="layout-body">
        <!-- 下半區域的左側邊欄 -->
        <el-aside class="layout-aside">
          <el-menu
              router
              :default-active="activeMenuItemPath"
              class="el-menu-vertical-demo"
              background-color="#3a4856"
              text-color="#fff"
              active-text-color="#fff">
            <!-- 主頁 -->
            <el-menu-item index="/admin/index">
              <i class="el-icon-s-home"></i>
              <span>主頁</span>
            </el-menu-item>
            <!-- 帳號管理 -->
            <el-submenu index="2">
              <template slot="title">
                <i class="el-icon-s-check"></i>
                <span>帳號管理</span>
              </template>
              <el-menu-item index="/admin/account/user">
                <i class="el-icon-user-solid"></i>
                <span slot="title">用戶管理</span>
              </el-menu-item>
              <el-menu-item index="/admin/account/login-log">
                <i class="el-icon-s-operation"></i>
                <span slot="title">登入日志</span>
              </el-menu-item>
            </el-submenu>
            <!-- 內容管理 -->
            <el-submenu index="3">
              <template slot="title">
                <i class="el-icon-s-management"></i>
                <span>內容管理</span>
              </template>
              <el-menu-item index="/admin/content/category">
                <i class="el-icon-s-grid"></i>
                <span slot="title">類別管理</span>
              </el-menu-item>
              <el-menu-item index="/admin/content/article">
                <i class="el-icon-s-order"></i>
                <span slot="title">文章管理</span>
              </el-menu-item>
              <el-menu-item index="/admin/content/check-log/article">
                <i class="el-icon-s-operation"></i>
                <span slot="title">文章審核日志</span>
              </el-menu-item>
              <el-menu-item index="/admin/content/comment">
                <i class="el-icon-s-comment"></i>
                <span slot="title">評論管理</span>
              </el-menu-item>
              <el-menu-item index="/admin/content/check-log/comment">
                <i class="el-icon-s-operation"></i>
                <span slot="title">評論審核日志</span>
              </el-menu-item>
            </el-submenu>
            <!-- 商城管理 -->
            <el-submenu index="4">
              <template slot="title">
                <i class="el-icon-s-shop"></i>
                <span>商城管理</span>
              </template>
              <el-menu-item index="/admin/mall/category">
                <i class="el-icon-s-grid"></i>
                <span slot="title">類別管理</span>
              </el-menu-item>
              <el-menu-item index="/admin/mall/goods">
                <i class="el-icon-s-goods"></i>
                <span slot="title">商品管理</span>
              </el-menu-item>
              <el-menu-item index="/admin/mall/check-log/goods">
                <i class="el-icon-s-operation"></i>
                <span slot="title">商品審核日志</span>
              </el-menu-item>
              <el-menu-item index="/admin/mall/comment">
                <i class="el-icon-s-comment"></i>
                <span slot="title">評論管理</span>
              </el-menu-item>
              <el-menu-item index="/admin/mall/check-log/comment">
                <i class="el-icon-s-operation"></i>
                <span slot="title">評論審核日志</span>
              </el-menu-item>
            </el-submenu>
          </el-menu>
        </el-aside>
        <!-- 下半區域的右側主體 -->
        <el-main class="layout-main">
          <!-- 將由其它視圖組件來負責此處的顯示 -->
          <div class="body">
            <router-view/>
          </div>
          <!-- 版權信息 -->
          <div class="copyright">
            <p>Copyright &copy; 達內時代科技集團 - IT學院 - Java教學研發部</p>
          </div>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<style>
body {
  margin: 0;
}

.layout-header {
  background: #28292e;
}

.layout-header .login-user {
  float: right;
  margin-top: 12px;
  display: flex;
}

.layout-header .login-user .welcome {
  line-height: 36px;
  color: #fff;
  margin-right: 10px;
}

.layout-header h1 {
  margin: 0;
  color: #fff;
  line-height: 60px;
  font-size: 20px;
}

.layout-body {
  position: absolute;
  top: 60px;
  bottom: 0;
  left: 0;
  right: 0;
}

.layout-aside {
  background: #3a4856;
}

.layout-aside .el-menu {
  border: 0;
}

.layout-aside i {
  color: #fff !important;
}

.layout-main {
  background: #f2f4f9;
}

.layout-main .body {
  background: #fff;
  padding: 30px 30px 10px 30px;
}

.layout-main .copyright {
  width: 600px;
  margin: 0 auto;
  padding: 10px;
}

.layout-main .copyright p {
  margin: 0;
  padding: 0;
  text-align: center;
  font-size: 12px;
  color: #9e9e9f;
}

.el-menu-item.is-active {
  background: #405872 !important;
}
</style>

<script>
import BaseUrl from "@/http/BaseUrl";
import TokenAxios from "@/http/TokenAxios";

export default {
  data() {
    return {
      loginInfo: {
        username: '',
        avatar: ''
      },
      activeMenuItemPath: ''
    }
  },
  methods: {
    // 處理點擊頭像的下拉菜單項
    handleCommand(command) {
      if (command == 'openEditInfoDialog') {
        this.openEditInfoDialog();
      } else if (command == 'openEditAvatarDialog') {
        this.openEditAvatarDialog();
      } else if (command == 'openEditPasswordDialog') {
        this.openEditPasswordDialog();
      } else if (command == 'openLogoutConfirm') {
        this.openLogoutConfirm();
      }
    },
    // 打開編輯對話框【未實現】
    openEditInfoDialog() {
      let title = '提示';
      let message = '您正在嘗試修改當前登入的【' + this.loginInfo.username + '】的【基本資料】，抱歉，此功能尚未實現……';
      this.$alert(message, title, {
        confirmButtonText: '確定',
        callback: action => {
        }
      });
    },
    // 打開修改頭像對話框【未實現】
    openEditAvatarDialog() {
      let title = '提示';
      let message = '您正在嘗試修改當前登入的【' + this.loginInfo.username + '】的【頭像】，抱歉，此功能尚未實現……';
      this.$alert(message, title, {
        confirmButtonText: '確定',
        callback: action => {
        }
      });
    },
    // 打開修改密碼對話框【未實現】
    openEditPasswordDialog() {
      let title = '提示';
      let message = '您正在嘗試修改當前登入的【' + this.loginInfo.username + '】的【密碼】，抱歉，此功能尚未實現……';
      this.$alert(message, title, {
        confirmButtonText: '確定',
        callback: action => {
        }
      });
    },
    // 打開登出確認框
    openLogoutConfirm() {
      let message = '即將退出登入，是否繼續？';
      this.$confirm(message, '提示', {
        confirmButtonText: '確定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.handleLogout();
      }).catch(() => {
      });
    },
    // 處理登出
    handleLogout() {
      let url = BaseUrl.PASSPORT + '/passport/logout';
      console.log('url = ' + url);

      TokenAxios.post(url).then((response) => {
        let jsonResult = response.data;
        if (jsonResult.state == 20000) {
          this.$message({
            message: '退出登入成功！',
            type: 'success'
          });
          localStorage.removeItem('loginInfo');
          this.$router.push('/login');
        } else {
          this.$alert(jsonResult.message, '錯誤', {
            confirmButtonText: '確定',
            callback: action => {
            }
          });
        }
      });
    },
    // 加載當前登入的用戶信息
    loadCurrentLoginInfo() {
      let loginInfoString = localStorage.getItem('loginInfo');
      if (!loginInfoString) {
        this.$router.push('/login');
        return;
      }
      this.loginInfo = JSON.parse(loginInfoString);
    },
    // 處理激活某個菜單項
    handleActiveMenuItem() {
      let path = this.$router.currentRoute.path;
      if (path.startsWith('/admin/account/user')) {
        path = '/admin/account/user';
      }
      if (path.startsWith('/admin/content/category')) {
        path = '/admin/content/category';
      }
      if (path.startsWith('/admin/content/article')) {
        path = '/admin/content/article';
      }
      if (path.startsWith('/admin/content/comment')) {
        path = '/admin/content/comment';
      }
      if (path.startsWith('/admin/mall/category')) {
        path = '/admin/mall/category';
      }
      if (path.startsWith('/admin/mall/goods')) {
        path = '/admin/mall/goods';
      }
      if (path.startsWith('/admin/mall/comment')) {
        path = '/admin/mall/comment';
      }
      this.activeMenuItemPath = path;
    }
  },
  created() {
  },
  mounted() {
    this.handleActiveMenuItem();
    this.loadCurrentLoginInfo();
  }
}
</script>