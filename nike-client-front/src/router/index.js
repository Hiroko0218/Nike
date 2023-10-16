import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from '@/views/HomeView.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    component: HomeView,
    redirect: '/recommend',
    children: [
      {
        path: '/recommend',
        component: () => import('@/views/content/RecommendView.vue')
      },
      {
        path: '/soccer',
        component: () => import('@/views/content/SoccerView.vue')
      },
      {
        path: '/basketball',
        component: () => import('@/views/content/BasketBallView.vue')
      },
      {
        path: '/workout',
        component: () => import('@/views/content/WorkOutView.vue')
      },
      {
        path: '/run',
        component: () => import('@/views/content/RunView.vue')
      },
      {
        path: '/yoga',
        component: () => import('@/views/content/YogaView.vue')
      },
      {
        path: '/skateboard',
        component: () => import('@/views/content/SkateBoardView.vue')
      },
      {
        path: '/tennis',
        component: () => import('@/views/content/TennisView.vue')
      },
      {
        path: '/article/details',
        component: () => import('@/views/content/ArticleDetails.vue')
      },
      {
        path: '/mall',
        component: () => import('@/views/mall/MallIndexView.vue')
      },
      {
        path: '/mall/commodity',
        component: () => import('@/views/mall/CommodityDetail.vue')
      },
      {
        path: '/mall/sameCommodity',
        component: () => import('@/views/mall/SameCommodityView.vue')
      },
      {
        path: '/mall/recommendCommodity',
        component: () => import('@/views/mall/RecommendCommodityView.vue')
      },
      {
        path: '/mall/myOrderList',
        component: () => import('@/views/mall/MyOrderList.vue')
      },

      {
        path: '/mall/cartLayout',
        component: () => import('@/views/mall/CartLayout.vue')
      },
      {
        path: '/receiverAddresses',
        component: () => import('@/views/mall/ReceiverAddresses.vue')
      },
      {
        path: '/userAbout',
        component: () => import('@/views/mall/UserAbout.vue')
      },
    ]
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router