import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '../views/HomeView.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    component: HomeView,
    redirect: '/admin/index',
    children: [
      {
        path: '/admin/index',
        component: () => import('../views/admin/AdminIndexView.vue')
      },
      // ====== 帳號管理 ======
      {
        path: '/admin/account',
        component: () => import('../views/admin/account/AdminAccountIndexView.vue')
      },
      {
        path: '/admin/account/user',
        component: () => import('../views/admin/account/UserManagementView.vue')
      },
      {
        path: '/admin/account/user/add-new',
        component: () => import('../views/admin/account/UserAddNewView.vue')
      },
      {
        path: '/admin/account/login-log',
        component: () => import('../views/admin/account/LoginLogListView.vue')
      },
      // ====== 資訊管理 ======
      {
        path: '/admin/content',
        component: () => import('../views/admin/content/AdminContentIndexView.vue')
      },
      {
        path: '/admin/content/category',
        component: () => import('../views/admin/content/CategoryManagementView.vue')
      },
      {
        path: '/admin/content/category/add-new',
        component: () => import('../views/admin/content/CategoryAddNewView.vue')
      },
      {
        path: '/admin/content/article',
        component: () => import('../views/admin/content/ArticleManagementView.vue')
      },
      {
        path: '/admin/content/article/add-new',
        component: () => import('../views/admin/content/ArticleAddNewView.vue')
      },
      {
        path: '/admin/content/check-log/article',
        component: () => import('../views/admin/content/ArticleCheckLogManagementView.vue')
      },
      {
        path: '/admin/content/comment',
        component: () => import('../views/admin/content/CommentManagementView.vue')
      },
      {
        path: '/admin/content/check-log/comment',
        component: () => import('../views/admin/content/CommentCheckLogManagementView.vue')
      },
      // ====== 商城管理 ======
      {
        path: '/admin/mall',
        component: () => import('../views/admin/mall/AdminMallIndexView.vue')
      },
      {
        path: '/admin/mall/category',
        component: () => import('../views/admin/mall/CategoryManagementView.vue')
      },
      {
        path: '/admin/mall/category/add-new',
        component: () => import('../views/admin/mall/CategoryAddNewView.vue')
      },
      {
        path: '/admin/mall/goods',
        component: () => import('../views/admin/mall/GoodsManagementView.vue')
      },
      {
        path: '/admin/mall/goods/add-new',
        component: () => import('../views/admin/mall/GoodsAddNewView.vue')
      },
      {
        path: '/admin/mall/check-log/goods',
        component: () => import('../views/admin/mall/GoodsCheckLogManagementView.vue')
      },
      {
        path: '/admin/mall/comment',
        component: () => import('../views/admin/mall/CommentManagementView.vue')
      },
      {
        path: '/admin/mall/check-log/comment',
        component: () => import('../views/admin/mall/CommentCheckLogManagementView.vue')
      },
    ]
  },
  {
    path: '/login',
    component: () => import('../views/LoginView.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
