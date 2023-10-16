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
          <el-breadcrumb-item><span v-text="'推薦'"></span></el-breadcrumb-item>
        </el-breadcrumb>
      </div>
      <!-- 相同商品列表 -->

      <ul class="pro-itmes g-wrap g-flex" v-for="index of (Math.ceil(recommendCommodityList.length / 4))">
        <li v-for="item in recommendCommodityList.slice((index - 1) * 4, (index - 1) * 4 + 4)" :key="item.id">
          <a :href="'/mall/commodity?id='+ item.id" target="_blank">
            <img class="pro-img" :src='item.coverUrl'>
            <div class="g-p10">
              <div class="g-txtcut g-font16 g-m5-b">{{ item.title }}</div>
              <div class="g-c-9 g-font14 g-onecut g-m5-b">{{ item.keywords }}</div>
              <div class="price">{{ item.salePrice }}</div>
            </div>
          </a>
        </li>
      </ul>
      <div>

      </div>
      <div class="g-m10-bt g-flex g-flex-cnt" v-if="this.nowPageNum==0">
        <a class="page-d" @click=addPageNum()>下一頁</a>
      </div>
      <div class="g-m10-bt g-flex g-flex-cnt" v-else="this.nowPageNum!=0">
        <a class="page-u" @click=subPageNum()>上一頁</a>
        <a class="page-d g-l-15" @click=addPageNum()>下一頁</a>
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
      nowPageNum: 1,
      recommendCommodityList: [],
      category: {},
    }
  },
  methods: {
    loadPageTitle() {
      this.pageTitle = '推薦商品- Nike'
      document.title = this.pageTitle;
    },
    addPageNum() {
      this.nowPageNum += 1;
    },
    subPageNum() {
      this.nowPageNum -= 1;
    },
    loadGoodList() {
      const params = {
        nowPageNum: this.nowPageNum,
      };
      MallAPI.getGoodsByRecommend(params)
          .then((response) => {
            const jsonResult = response.data;
            this.recommendCommodityList = this.recommendCommodityList.concat(jsonResult.data.list);
            this.category = jsonResult.data;
            this.nowPageNum = jsonResult.data.nowPageNum;
            this.total = jsonResult.data.total;
          })
          .catch((error) => {
            console.error('載入文章時發生錯誤：', error);
          });
    }
  },
  mounted() {
    this.loadPageTitle();
    this.loadGoodList();
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

.g-b-f {
  background: #fff;
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

.g-c-9 {
  color: #999;
}

.g-font14 {
  font-size: 14px;
}

.g-wrap {
  flex-flow: wrap;
}

.g-flex {
  display: flex;
}

.g-b-r10 {
  border-radius: 10px;
}

.g-font-b {
  font-weight: 700;
}

.g-font20 {
  font-size: 20px;
}

.pro-itmes li {
  text-align: left;
  width: 275px;
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

.pro-img {
  width: 230px;
  height: 230px;
  background: #f5f5f5;
}

.g-txtcut {
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
}

.g-onecut {
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}

.price {
  color: #ff5000;
  font-size: 17px;
}

.price::before {
  content: "$";
  margin-right: 5px;
  font-size: 14px;
  font-weight: 400;
}

.g-flex-cnt {
  justify-content: center;
}

.page-d, .page-u {
  background: #fff;
  height: 50px;
  line-height: 50px;
  width: 300px;
  display: block;
  border-radius: 20px;
  text-align: center;
  color: #666;
  -webkit-transition: all .15s linear;
  transition: all .15s linear;
}

.page-d:hover, .page-u:hover {
  background: #0D0D0D;
  color: #fff;
}

.nc-join {
  margin: 50px 0 20px;
}

.nc-join img {
  display: block;
  border: none;
  width: 100%;
}

.g-l-15 {
  margin-left: 15px;
}
</style>
