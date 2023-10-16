<template>
  <div>
    <!-- 浮動導航 -->
    <div class="float-link-container">
      <FloatMallLinkComponent/>
    </div>

    <!-- 頁面主體 -->
    <div class="main-container">
      <div class="nav-breadcrumb">
        <!-- 面包屑導航 -->
        <el-breadcrumb separator="/" style="font-size: 16px;">
          <el-breadcrumb-item :to="{ path: '/mall' }">商城</el-breadcrumb-item>
          <el-breadcrumb-item><span v-text="commodityDetails.categoryName">Xxx</span></el-breadcrumb-item>
          <el-breadcrumb-item><span v-text="commodityDetails.title">Xxx</span></el-breadcrumb-item>
        </el-breadcrumb>
      </div>
      <div class="pro-intro g-b-f g-b-r10 g-flex">
        <div id="magnifier">
          <el-image class="image" :src="commodityDetails.coverUrl" :alt="commodityDetails.title"></el-image>
        </div>
        <div class="right">
          <div class="main-intro">
            <h1 class="g-font22 g-m10-b">{{ commodityDetails.title }}</h1>
            <div class="g-c-9 g-font14 g-m15-b">{{ commodityDetails.keywords }}</div>
            <div class="g-flex g-flex-cns">
              <div class="price g-font30 g-m10-b">{{ commodityDetails.salePrice }}</div>
            </div>
            <div class="g-font16 g-m10-bt">數量</div>
            <el-input-number v-model="num" @change="handleChange" :min="1" :max="10" label="描述文字"></el-input-number>
          </div>
          <div id="order-car">加入購物車</div>
          <div id="order-submit"><a :href="'/mall/CartLayout'" target="_blank" style="color: white">立即購買</a></div>
          <ul class="extra-info g-flex-item g-flex g-wrap g-c-7">
            <li class="ncui-icons">全場包郵</li>
            <li class="ncui-icons">30天退換</li>
            <li class="ncui-icons">正品保證</li>
            <li class="ncui-icons">假一賠十</li>
          </ul>
        </div>
      </div>
      <div class="pro-details g-flex g-flex-bet">
        <div>
          <div class="g-flex so-box g-b-f">
            <div class="ncui-iconsbd">
              <i class="el-icon-search"></i>
            </div>
            <input type="text" class="g-bnt0 so-input" value="" id="wd" autocomplete="off" placeholder="輸入關鍵字">
            <a class="so-bnt g-c-f g-center" id="so"  @click="search">搜尋</a>
          </div>

          <ul class="g-b-f g-b-r10" v-for="item in leftCommdityList">
            <li>
              <a :href="'/mall/commodity?id='+item.id" target="_blank" class="corr-img">
                <el-image class="image" :src="item.coverUrl" :alt="item.title"></el-image>
              </a>
              <h3><a :href="'/mall/commodity?id='+item.id" target="_blank" cg-txtcutlass="">{{ item.title }}</a></h3>
              <div class="corr-price g-font18">{{ item.salePrice }}</div>
            </li>
          </ul>
        </div>

        <div class="commodityTabs">
          <el-tabs v-model="activeName" @tab-click="handleClick">
            <el-tab-pane label="商品詳情" name="commodityDetailTab">
              <div class="content pro-details-cont g-b-f g-b-r10">
                <div v-html="commodityDetails.detail"></div>
              </div>
            </el-tab-pane>
            <el-tab-pane label="商品評價" name="commentTab">
              <div class="content pro-details-cont g-b-f g-b-r10">
                <div class="comment-percent">
                  <strong class="percent-tit">好評數</strong>
                  <div class="percent-con">{{ commodityDetails.positiveCommentCount }}</div>
                </div>
                <div v-for="commentItem in commentList">
                  <div class="comment-item">
                    <div class="user-column">
                      <div class="user-info">{{ commentItem.authorName }}</div>
                    </div>
                    <div class="comment-column J-comment-column">
<!--                      <el-rate-->
<!--                          v-model="commentItem.type"-->
<!--                          disabled-->
<!--                          show-score-->
<!--                          text-color="#ff9900"-->
<!--                          score-template="{value}">-->
<!--                      </el-rate>-->
                        <div class="item"  >
                          <img class="image" v-if="commentItem.type == 0" src="../../../public/images/讚.png" ></img>
                          <img class="image" v-else-if="commentItem.type == 2" src="../../../public/images/倒讚.png" ></img>
                          <img class="image" v-else src="../../../public/images/普通.png" ></img>
                        </div>
                      <p class="comment-con">{{ commentItem.content }}</p>
                      <div class="J-pic-view-wrap clearfix" data-rotation="0">
                      </div>
                      <div class="comment-message">
                        <div class="order-info">
                          <span>{{ commentItem.gmtCreate }}</span>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </el-tab-pane>
          </el-tabs>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import FloatMallLinkComponent from '@/components/FloatMallLinkComponentView.vue';
import MallAPI from "@/http/apis/MallAPI";
import URLTitle from "@/http/URLTitle";
import TokenAxios from "@/http/TokenAxios";

export default {
  components: {
    FloatMallLinkComponent
  },
  data() {
    return {
      activeName: '',
      pageTitle: '',
      defaultSearchKeywords: '',
      searchKeywords: '',
      searchSuggestKeywords: [],
      num: 1,
      //商品詳情
      commodityDetails: {},
      //推薦商品
      leftCommdityList: [],
      //評論數據
      commentList: [],
      // 分頁相關數據
      currentPage: this.$router.currentRoute.query.page ? parseInt(this.$router.currentRoute.query.page) : 1,
      pageSize: 20,
      total: 0,
    }
  },
  methods: {
    // 網頁抬頭
    loadPageTitle() {
      this.pageTitle = this.commodityDetails.title+ '- Nike';
      document.title = this.pageTitle;
    },
    // 關鍵字搜尋
    loadDefaultSearchKeywords() {
      let defaultSearchKeywords = '輸入關鍵字';
      this.defaultSearchKeywords = defaultSearchKeywords;
    },
    // 搜尋功能
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
    //
    handleChange(value) {
      console.log(value);
    },
    //
    handleClick(tab, event) {
      this.activeName = tab.name;
      console.log(tab, event);
    },
    // 推薦清單
    loadRecommendList() {
      const params = {
        pageSize: this.pageSize,
        currentPage: this.currentPage,
      };
      MallAPI.getGoodsByRecommend(params)
          .then((response) => {
            const jsonResult = response.data;
            this.leftCommdityList = this.leftCommdityList.concat(jsonResult.data.list);
            this.currentPage = jsonResult.data.currentPage;
            this.pageSize = jsonResult.data.pageSize;
            this.total = jsonResult.data.total;
          })
          .catch((error) => {
            console.error('載入推薦商品時發生錯誤：', error);
          });
    },
    // 取得評論數據
    getCommodityDetail() {
      const params = {
        pageSize: this.pageSize,
        currentPage: this.currentPage,
        goodsId: this.$router.currentRoute.query.id
      };
      MallAPI.getCommentsList(params)
          .then((response) => {
            const jsonResult = response.data;
            this.commentList = this.commentList.concat(jsonResult.data.list);
            this.currentPage = jsonResult.data.currentPage;
            this.pageSize = jsonResult.data.pageSize;
            this.total = jsonResult.data.total;
          })
          .catch((error) => {
             console.error('載入評論時發生錯誤：', error);
          });
    },
    // 取得商品數據
    loadGoodDetails(){
      const params = {
        id: this.$router.currentRoute.query.id
      };
      MallAPI.getGoodsByID(params)
          .then((response) => {
            const jsonResult = response.data;
            this.commodityDetails=jsonResult.data;
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
    this.loadGoodDetails();
    this.loadRecommendList();
    this.getCommodityDetail();
  },
  created() {

  }
}
</script>

<style>
a {
  color: #000000;
  text-decoration: none;
  -webkit-tap-highlight-color: transparent;
}

.main-container {
  width: 1200px;
  margin: 0 auto;
}

.float-link-container {
  position: fixed;
  right: 30px;
  bottom: 30px;
  z-index: 1;
}

.nav-breadcrumb {
  margin: 10px 0;
}

.pro-intro {
  padding: 40px 40px 20px;
  box-sizing: border-box;
}

.g-b-r10 {
  border-radius: 10px;
}

.g-b-f {
  background: #fff;
}

#magnifier {
  position: relative;
  width: 450px;
  height: 450px;
  margin-bottom: 20px;

}

#magnifier img {
  width: auto;
  height: auto;
  max-height: 450px;
  max-width: 450px;
}

.pro-intro > .right {
  margin-left: 40px;
}

.main-intro {
  text-align: left;

}

.g-font22 {
  font-size: 22px;
}

.pro-intro .price {
  color: #ff5000;
}

.g-flex-cns {
  align-items: center;
}

.pro-intro .price::before {
  content: "$";
  margin-right: 3px;
  font-size: 16px;
  font-weight: 400;
}

.g-m10-b {
  margin-bottom: 10px;
}

.g-m10-bt {
  margin: 10px 0;
}

.g-font16 {
  font-size: 16px;
}

.g-m15-b {
  margin-bottom: 15px;
}

.g-font30 {
  font-size: 30px;
}

.g-m15-b {
  margin-bottom: 15px;
}

.g-c-9 {
  color: #999;
}

.g-font14 {
  font-size: 14px;
}

#order-submit {
  max-width: 480px;
  height: 45px;
  line-height: 45px;
  background: #ff5722;
  text-align: center;
  border-radius: 10px;
  color: #fff;
  box-shadow: 0 0.03rem 0.24rem 0 #b5b4b4;
  cursor: pointer;
  user-select: none;
  margin-top: 50px;
}

#order-car {
  max-width: 480px;
  height: 45px;
  line-height: 45px;
  background: #1fab7a;
  text-align: center;
  border-radius: 10px;
  color: #fff;
  box-shadow: 0 0.03rem 0.24rem 0 #b5b4b4;
  cursor: pointer;
  user-select: none;
  margin-top: 50px;
}

.extra-info {
  margin: 25px 0;
}

.g-wrap {
  flex-flow: wrap;
}

.g-flex-item {
  flex: 1;
}

.g-flex {
  display: flex;
}

.g-c-7 {
  color: #777;
}

.extra-info li {
  margin: 0 15px 0 0;
}

.extra-info li::before {
  content: "*";
  margin-right: 3px;
  color: #e0620d;
  font-size: 12px;
}

.pro-details {
  margin: 30px 0;
  text-align: left;
}

.g-flex-bet {
  justify-content: space-between;
}

.so-box {
  height: 40px;
  line-height: 40px;
  margin-bottom: 10px;
}

.pro-details ul {
  padding: 30px 30px 10px;
  width: 300px;
  height: fit-content;
  box-sizing: border-box;
}

.pro-details ul > li {
  border-bottom: 1px solid #f1f1f1;
  padding-bottom: 10px;
  margin-bottom: 20px;
}

.corr-img {
  display: block;
  border-radius: 5px;
  overflow: hidden;
  background: #F5F5F5;
  margin-bottom: 5px;
}

.pro-details img {
  display: block;
  border: none;
  width: 100%;
}

.pro-details ul > li img:hover {
  transition: 0.3s all ease-in-out;
  transform: scale(1.05);
}

.pro-details ul > li h3 {
  font-weight: 400;
  font-size: 14px;
}

.g-b-r10 {
  border-radius: 10px;
}

.ncui-iconsbd {
  font-family: "nciconsbd";
  font-weight: normal;
  font-style: normal;
  -webkit-font-smoothing: antialiased;
}

.ncui-iconsbd > i {
  margin: 10px;
}

.so-input {
  width: 169px;
}

.so-input {
  flex: 1;
  height: 42px;
  width: 169px;
  padding: 0 10px;
  outline: none;
}

.g-bnt0 {
  background-color: rgba(255, 255, 255, 0);
  border: none;
}

.so-bnt {
  background: #0D0D0D;
  width: 80px;
  cursor: pointer;
  text-align: center;
  color: #fff;
}

.corr-price {
  color: #ff5000;
}

.corr-price::before {
  content: "$";
  margin-right: 5px;
  font-size: 16px;
  font-weight: 400;
}

.commodityTabs .content {
  padding: 30px 20px 0;
  box-sizing: border-box;
  font-size: 17px;
  color: #454545;
  text-align: justify;
  line-height: 1.85;
  border-top: 1px solid #f1f1f1;
}

.pro-details-cont {
  width: 880px;
  box-sizing: border-box;
  padding: 50px 65px 20px !important;
}

.commodityTabs .content img {
  max-width: 100%;
  width: auto;
  margin: 0 auto 22px;
  border-radius: 5px;
}

.pro-details-cont p {
  margin-bottom: 16px !important;
}

.pro-details-cont .comment-percent {
  width: 100%;
  float: left;
}

.pro-details-cont .percent-con {
  line-height: 110%;
  font-size: 45px;
  color: #e4393c;
  font-family: arial;
}

.pro-details-cont .percent-con span {
  font-size: 23px;
}

/*評論*/
.comment-item {
  zoom: 1;
  border-bottom: 1px solid #ddd;
}

/*評論圖片*/
.item{
  height: 20px;
  width: 20px;
}

</style>
