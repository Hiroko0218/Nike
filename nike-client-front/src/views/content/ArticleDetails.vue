<template>
  <div>
    <!-- 浮動導航 -->
    <div class="float-link-container">
      <FloatSubjectLinkComponent/>
    </div>
    <!-- 頁面主體 -->
    <div class="main-container">
      <div class="nav-breadcrumb">
        <!-- 面包屑導航 -->
        <el-breadcrumb separator="/" style="font-size: 16px;">
          <el-breadcrumb-item :to="{ path: '/' }">首頁</el-breadcrumb-item>
          <el-breadcrumb-item><span v-text="articleDetails.categoryName">Xxx</span></el-breadcrumb-item>
          <el-breadcrumb-item><span v-text="articleDetails.title">Xxx</span></el-breadcrumb-item>
        </el-breadcrumb>
      </div>
      <div class="main-wrap">
        <!-- 左側主體 -->
        <div class="left-aside-container">
          <div class="left-aside-wrap">
            <!-- 左側的文章詳情 -->
            <div class="article-details">
              <h1 class="title" v-text="articleDetails.title"></h1>
              <div class="publish-info">
                <span>作者：</span>
                <span class="author" v-text="articleDetails.authorName"></span>
                <span>發布時間：</span>
                <span class="publish-time" v-text="articleDetails.gmtCreate"></span>
              </div>
              <div class="content" v-html="articleDetails.detail"></div>
            </div>
          </div>
        </div>
        <!-- 右側的列表 -->
        <div class="right-aside-container">
          <div class="right-aside-wrap">
            <!-- 搜尋區域 -->
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
    RecommendListComponent
  },
  data() {
    return {
      //網頁抬頭
      pageTitle: '',
      //搜尋關鍵字
      defaultSearchKeywords: '',
      categoryList: [],
      //推薦清單
      recommendList: [],
      //文章詳情
      articleDetails: {},
    }
  },
  methods: {
    loadCategoryList() {
      let categoryList = [
        {id: 1, name: '跑步', url: '/run'},
        {id: 2, name: '足球', url: '/soccer'},
        {id: 3, name: '籃球', url: '/basketball'},
        {id: 4, name: '健身與訓練', url: '/workout'},
        {id: 5, name: '瑜珈', url: '/yoga'},
        {id: 6, name: '滑板', url: '/skateboard'},
        {id: 7, name: '網球', url: '/tennis'}
      ];
      this.categoryList = categoryList;
    },
    //網頁抬頭
    loadPageTitle() {
      this.pageTitle = this.articleDetails.title + ' - Nike';
      document.title = this.pageTitle;
    },
    //搜尋關鍵詞
    loadDefaultSearchKeywords() {
      let defaultSearchKeywords = '推薦';
      this.defaultSearchKeywords = defaultSearchKeywords;
    },
    //文章詳情
    loadArticleDetails() {
      const params = {
        id: this.$router.currentRoute.query.id
      };
      ArticlesAPI.getArticleByID(params)
          .then((response) => {
            const jsonResult = response.data;
            this.articleDetails = jsonResult.data;
            this.total = jsonResult.data.total;
            this.loadPageTitle();
          })
          .catch((error) => {
            console.error('載入文章時發生錯誤：', error);
          });
      this.$store.state.articleCategoryLink = '/jinji'

    },
    //推薦文章
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
  },
  mounted() {
    this.loadDefaultSearchKeywords();
    this.loadCategoryList();
    this.loadRecommendList();
    this.loadArticleDetails();
  }
}
</script>

<style>
.article-details {
  background: #fff;
  border-radius: 5px;
  padding: 35px;
}

.article-details .title {
  margin: 0px auto 20px auto;
}

.article-details .publish-info {
  color: #999;
  font-size: 14px;
  margin: 0px auto 20px auto;
}

.article-details .publish-info .author {
  margin-right: 30px;
}

.article-details .publish-info .publish-time {
}

.article-details .content {
  margin: 10px auto;
  border-top: 1px solid #ddd;
  padding: 20px 20px 0px 20px;
  text-align: left;
  font-size: 18px;
  color: #333;
}

.article-details .content p {
  margin: 24px auto;
}

.friend-links {
  margin-top: 20px;
  background: #fff;
  border-radius: 5px;
  padding: 35px;
}

.friend-links .title {
  margin: 0px auto 20px auto;
}

.friend-links .content img {
  width: 810px;
}
</style>