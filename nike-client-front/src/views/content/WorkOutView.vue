<template>
  <div>
    <!-- 浮動導航 -->
    <div class="float-link-container">
      <FloatSubjectLinkComponent/>
    </div>

    <!-- 頁面主體 -->
    <div class="main-container">
      <div class="main-wrap">
        <!-- 左側主體 -->
        <div class="left-aside-container">
          <div class="left-aside-wrap">
            <!-- 左側的文章列表 -->
            <ul class="article-list">
              <li v-for="item in articleList" :key="item.id">
                <div class="article-list-item">
                  <div class="left">
                    <el-image class="image" :src="item.coverUrl" :alt="item.title"></el-image>
                  </div>
                  <div class="right">
                    <h1 class="title"><a :href="'/article/details?id=' + item.id" v-text="item.title"></a></h1>
                    <p class="content" v-text="item.brief"></p>
                    <div>
                      <span class="up">讚 {{ item.upCount }}</span>
                      <span class="down">倒讚 {{ item.downCount }}</span>
                      <span class="date" v-text="item.gmtCreate"></span>
                    </div>
                    <div style="clear: both;"></div>
                  </div>
                </div>
                <el-divider/>
              </li>
            </ul>
            <div style="margin-top: 20px;" v-if="articleList.length < total">
              <el-button class="btn-load-more" @click="loadMoreArticle">查看更多</el-button>
            </div>
          </div>
        </div>
        <!-- 右側的列表 -->
        <div class="right-aside-container">
          <div class="right-aside-wrap">
            <!-- 搜索區域 -->
            <SearchComponent :defaultSearchKeywords="defaultSearchKeywords"/>
            <!-- 推薦列表 -->
            <RecommendListComponent :recommendList="recommendList"/>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import FloatSubjectLinkComponent from '@/components/FloatSubjectLinkComponentView.vue';
import SearchComponent from '@/components/SearchComponentView.vue';
import RecommendListComponent from '@/components/RecommendListComponentView.vue';
import ArticlesAPI from '@/http/apis/ArticlesAPI';

export default {
  components: {
    FloatSubjectLinkComponent,
    SearchComponent,
    RecommendListComponent,

  },
  data() {
    return {
      currentCategory: {
        id: 4,
        name: '健身與訓練',
        url: '/workout'
      },
      //網頁抬頭
      pageTitle: '',
      //搜尋關鍵字
      defaultSearchKeywords: '',
      //推薦清單
      recommendList: [],
      //文章數據
      articleList: [],
      // 分頁相關數據
      currentPage: this.$router.currentRoute.query.page ? parseInt(this.$router.currentRoute.query.page) : 1,
      pageSize: 20,
      total: 0,
    }
  },
  methods: {
    loadPageTitle() {
      this.pageTitle = '健身與訓練文章-Nike';
      document.title = this.pageTitle;
    },
    loadDefaultSearchKeywords() {
      let defaultSearchKeywords = '健身與訓練';
      this.defaultSearchKeywords = defaultSearchKeywords;
    },
    loadRecommendList() {
      const params = {
        pageSize: this.pageSize,
        currentPage: this.currentPage,
      };
      ArticlesAPI.getArticlesByRecommend(params)
          .then((response) => {
            const jsonResult = response.data;
            this.recommendList = this.recommendList.concat(jsonResult.data.list);
            this.currentPage = jsonResult.data.currentPage;
            this.pageSize = jsonResult.data.pageSize;
            this.total = jsonResult.data.total;
          })
          .catch((error) => {
            console.error('載入文章時發生錯誤：', error);
          });
    },
    loadMoreArticle() {
      this.currentPage++;
      this.loadArticleList();
    },
    loadArticleList() {
      const params = {
        pageSize: this.pageSize,
        currentPage: this.currentPage,
        categoryId: 4
      };
      ArticlesAPI.getArticlesByCategory(params)
          .then((response) => {
            const jsonResult = response.data;
            this.articleList = this.articleList.concat(jsonResult.data.list);
            this.currentPage = jsonResult.data.currentPage;
            this.pageSize = jsonResult.data.pageSize;
            this.total = jsonResult.data.total;
          })
          .catch((error) => {
            console.error('載入文章時發生錯誤：', error);
          });
    }
  },
  mounted() {
    this.loadPageTitle();
    this.loadDefaultSearchKeywords();
    this.loadArticleList();
    this.loadRecommendList();
  }
}
</script>

<style>
.btn-load-more{
  background-color:#0D0D0D;
  color: white;
}
</style>