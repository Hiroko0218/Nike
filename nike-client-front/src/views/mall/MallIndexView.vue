<template>
  <div>
    <!-- 浮動導航 -->
    <div class="float-link-container">
      <FloatMallLinkComponent/>
    </div>

    <!-- 搜尋區域 -->
    <div class="search-container">
      <div class="search-wrap">
        <div class="search-box">
          <input class="inp-search" :placeholder="defaultSearchKeywords" v-model="searchKeywords"/>
          <a class="btn-search" href="javascript:;" @click="search">搜尋</a>
        </div>
        <div class="search-suggest-box">
          <ul>
            <li>熱門搜尋：</li>
            <li v-for="item in searchSuggestKeywords" :key="item">
              <a :href="'/mall/search?keywords=' + item" v-text="item"></a>
            </li>
          </ul>
        </div>
      </div>
    </div>

    <!-- 頁面主體 -->
    <div class="main-container">
      <div class="mall-main-wrap">
        <!-- 商品列表 -->
        <div id="cate-tit-ms" class="cate-wrapper g-flex g-flex-bet g-flex-cns">
          <div class="cate-tit active">推薦</div>
            <div class="cate-more g-flex g-flex-cns">
              <a :href="'/mall/recommendCommodity'" class="g-font16 g-c-9 cate-more-ico ncui-icons" target="_blank">更多</a>
            </div>
        </div>
        <div class="catechild-itmes">
          <ul class="recommend-list" v-for="index of (Math.ceil(recommendList.length / 16))">
            <li v-for="item in recommendList.slice((index - 1) * 4, (index - 1) * 4 + 4)" :key="item.id">
              <a :href="'/mall/commodity?id='+ item.id" target="_blank">
                <el-image class="image" :src="item.coverUrl" :alt="item.title"></el-image>
                <div class="g-p10">
                  <div class="g-txtcut g-font16 g-m5-b">{{ item.title }}</div>
                  <div class="g-c-9 g-font14 g-onecut g-m5-b">{{ item.keywords }}</div>
                  <div class="price">{{ item.salePrice }}</div>
                </div>
              </a>
            </li>
          </ul>
        </div>

        <div v-for="item in allCommodity">
          <div id="cate-tit-ms" class="cate-wrapper g-flex g-flex-bet g-flex-cns">
            <div class="cate-tit active"> {{ item.name }} </div>
            <div class="cate-more g-flex g-flex-cns">
              <a :href="'/mall/sameCommodity?categoryId='+ item.id" class="g-font16 g-c-9 cate-more-ico ncui-icons" target="_blank">更多</a>
            </div>
          </div>

          <div class="catechild-itmes">
            <ul class="recommend-list" v-for="index of (Math.ceil(commodityList.length / 4))">
              <li v-for="items in commodityList.slice((index - 1) * 4, (index - 1) * 4 + 4)" :key="items.id">
                <a :href="'/mall/commodity?id='+ items.id" target="_blank">
                  <el-image class="image" :src="items.coverUrl" :alt="items.title"></el-image>
                  <div class="g-p10">
                    <div class="g-txtcut g-font16 g-m5-b">{{ items.title }}</div>
                    <div class="g-c-9 g-font14 g-onecut g-m5-b">{{ items.keywords }}</div>
                    <div class="price">{{ items.salePrice }}</div>
                  </div>
                </a>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import FloatMallLinkComponent from '@/components/FloatMallLinkComponentView.vue';
import MallAPI from "@/http/apis/MallAPI";

export default {
  components: {
    FloatMallLinkComponent
  },
  data() {
    return {
      pageTitle: '',
      defaultSearchKeywords: '',
      searchKeywords: '',
      searchSuggestKeywords: [],
      allCommodity:[],
      recommendList: [],
      commodityList: [],
      // 分頁相關數據
      currentPage: this.$router.currentRoute.query.page ? parseInt(this.$router.currentRoute.query.page) : 1,
      pageSize: 20,
      total: 0,
    }
  },
  methods: {
    //商品類別
    getCategoryList() {
      const params = {
        parentId: 1
      };
      MallAPI.getCategoriesByParent(params)
          .then((response) => {
            const jsonResult = response.data;
            this.allCommodity= this.allCommodity.concat(jsonResult.data.list);
            this.total = jsonResult.data.total;
          })
          .catch((error) => {
            console.error('載入商品時發生錯誤：', error);
          });
    },
    //網頁抬頭
    loadPageTitle() {
      this.pageTitle = 'Nike商城';
      document.title = this.pageTitle;
    },
    //搜尋關鍵詞
    loadDefaultSearchKeywords() {
      let defaultSearchKeywords = '請輸入關鍵詞';
      this.defaultSearchKeywords = defaultSearchKeywords;
    },
    //熱門搜尋
    loadSearchSuggestKeywords() {
      let searchSuggestKeywords = ['男鞋', '女鞋', '童鞋', 'Jordan', '足球鞋', '跑步鞋'];
      this.searchSuggestKeywords = searchSuggestKeywords;
    },
    //搜尋
    search() {
      let keywords = this.searchKeywords;
      if (!keywords) {
        keywords = this.defaultSearchKeywords;
      }
      this.$message({
        message: '您正在嘗試搜尋【' + keywords + '】，抱歉，此功能尚未實現……',
        type: 'warning'
      });
    },
    //推薦商品名單
    loadRecommendList() {
      MallAPI.getGoodsByRecommend()
          .then((response) => {
            const jsonResult = response.data;
            this.recommendList = this.recommendList.concat(jsonResult.data.list);
            this.total = jsonResult.data.total;
          })
          .catch((error) => {
            console.error('載入商品時發生錯誤：', error);
          });
    },
    //商品名單
    loadGoodList() {
      const params = {
        categoryId: 3,
        pageSize: this.pageSize,
        currentPage: this.currentPage,
      };
      MallAPI.getGoodsByCategory(params)
          .then((response) => {
            const jsonResult = response.data;
            this.commodityList= this.commodityList.concat(jsonResult.data.list);
            this.currentPage = jsonResult.data.currentPage;
            this.pageSize = jsonResult.data.pageSize;
            this.total = jsonResult.data.total;
          })
          .catch((error) => {
            console.error('載入商品時發生錯誤：', error);
          });
    }
  },
  mounted() {
    this.loadPageTitle();
    this.loadDefaultSearchKeywords();
    this.loadSearchSuggestKeywords();
    this.loadGoodList();
    this.getCategoryList();
    this.loadRecommendList();
  },
  created() {

  }
}
</script>

<style>

.float-link-container {
  position: fixed;
  right: 30px;
  bottom: 30px;
  z-index: 1;
}

.search-container {
  height: 342px;
  background-image: url('../../../public/images/Nike.jpg');
}

.search-container .search-wrap {
  padding: 180px 0 0 0;
}

.search-container .search-box {
  width: 720px;
  height: 60px;
  margin: 0 auto;
  text-align: left;
  position: relative;
}

.search-container .search-box .inp-search {
  width: 550px;
  height: 60px;
  padding: 0 90px 0 30px;
  opacity: 0.85;
  border: none;
  border-radius: 30px;
  outline: none;
  font-size: 16px;
  color: #666;
}

.search-container .search-box a.btn-search {
  display: inline-block;
  padding: 0 30px;
  width: 50px;
  height: 60px;
  line-height: 60px;
  position: absolute;
  right: 0;
  top: 0;
  background: #0D0D0D;
  border-radius: 30px;
  text-align: center;
  font-size: 16px;
  color: #fff;
}

.search-container .search-suggest-box {
  margin-top: 30px;
}

.search-container .search-suggest-box li {
  display: inline-block;
  color: #0D0D0D;
  margin: 0 10px;
  font-weight: bold;
}

.search-container .search-suggest-box li a {
  color: #0F1926;
}

.search-container .search-suggest-box li a:hover {
  color: #fff;
  text-decoration: underline;
}

.main-container {
  width: 1200px;
  margin: 0 auto;
}

.cate-tit.active {
  font-weight: 700;
  font-size: 26px;
  color: #333;
}

.cate-wrapper {
  margin-bottom: 20px;
}

.recommend-list {
  display: flex;
  margin-bottom: 20px;
  align-items: center;
  justify-content: space-between;
}

.recommend-list li {
  width: 280px;
  height: 390px;
  background: #fff;
  box-sizing: border-box;
  -webkit-transition: all .15s linear;
  transition: all .15s linear;
  border-radius: 5px;
  overflow: hidden;
  cursor: pointer;
  margin: 0 25px 30px 0;
  padding: 25px;
}

.recommend-list li a {
  color: #000000;
}

.recommend-list li:hover {
  z-index: 2;
  -webkit-box-shadow: 0 15px 30px rgb(0 0 0 / 10%);
  box-shadow: 0 15px 30px rgb(0 0 0 / 10%);
  -webkit-transform: translate3d(0, -2px, 0);
  transform: translate3d(0, -2px, 0);
}

.price::before {
  content: "$";
  margin-right: 5px;
  font-size: 14px;
  font-weight: 400;
}

.price {
  color: #ff5000;
  font-size: 17px;
}

.g-p10 {
  text-align: left;
  padding: 10px;
}

.g-txtcut {
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
}

.cate-wrapper {
  margin-bottom: 20px;
}

.g-flex-cns {
  align-items: center;
}

.g-flex-bet {
  justify-content: space-between;
}

.g-flex {
  display: flex;
}

.g-m5-b {
  margin-bottom: 5px;
}

.g-font16 {
  font-size: 16px;
}

.g-m5-b {
  margin-bottom: 5px;
}

.g-c-9 {
  color: #999;
}

.g-font14 {
  font-size: 14px;
}

.ncui-icons {
  font-family: "ncicons";
  font-weight: normal;
  font-style: normal;
  -webkit-font-smoothing: antialiased;
}

.cate-more {
  cursor: pointer;
  user-select: none;
  margin-right: 15px;
}

.cate-more-ico::after {
  content: ">";
  margin-left: 5px;
  font-size: 18px;
}

.cate-more-ico:hover {
  color: #fda400;
}
</style>
