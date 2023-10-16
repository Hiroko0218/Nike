<template>
  <div>
    <!-- 浮動導航 -->
    <div class="float-link-container">
      <FloatMallLinkComponent/>
    </div>

    <!-- 頁面主體 -->
    <div class="main-container">
      <div class="order-list-container" v-for="item in orderList">
        <!--左側導航結束-->
        <div class="panel panel-default">
          <div class="panel-heading">
            <p class="panel-title">
              訂單號：{{ item.orderNo }}，下單時間：{{ item.gmtPay }} ，收貨人：{{ item.receiverName }}
            </p>
          </div>
          <div class="panel-body">
            <table class="orders-table" width="100%">
              <thead>
              <tr>
                <th width="15%"></th>
                <th width="30%">商品</th>
                <th width="8%">單價</th>
                <th width="8%">數量</th>
                <th width="9%">小計</th>
                <th width="10%">售後</th>
                <th width="10%">狀態</th>
                <th width="10%">操作</th>
              </tr>
              </thead>
              <tbody class="orders-body" v-for="innerItem in item.orderItemContentList">
              <tr>
                <td><img :src="innerItem.orderInnerItemImg" class="img-responsive"/></td>
                <td>{{ innerItem.orderInnerItemTitle }}</td>
                <td>$<span>{{ innerItem.orderInnerItemPrice }}</span></td>
                <td>{{ innerItem.goodsNum }}件</td>
                <td>$<span>{{ innerItem.orderInnerItemAmount }}</span></td>
                <td><a href="#">申請售後</a></td>
                <td>
                  <div>已發貨</div>
                  <div><a href="orderInfo.html">訂單詳情</a></div>
                </td>
                <td><a href="#" class="btn btn-default btn-xs">確認收貨</a></td>
              </tr>

              </tbody>
            </table>
            <div>
              <span class="pull-right">訂單總金額：${{ item.totalPrice }}</span>
            </div>
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
      orderList: [
        {
          orderItemContentList: [
            {
              orderInnerItemImg: '/images/20200111_CB1C45EC35954CD46AD94B8E2959EFA0.jpg',
              orderInnerItemTitle: '2023年明前春芽綠茶 特級高山有機茶 匠心系列 125克',
              orderInnerItemPrice: 212,
              orderInnerItemCount: 1,
              orderInnerItemAmount: 212

            },
          ]
        },
      ],
      // 分頁相關數據
      currentPage: this.$router.currentRoute.query.page ? parseInt(this.$router.currentRoute.query.page) : 1,
      pageSize: 20,
      total: 0,
    }
  },
  methods: {
    loadPageTitle() {
      this.pageTitle = 'Nike商城-訂單';
      document.title = this.pageTitle;
    },
    loadListByCategory(){
      const params = {
        pageSize: this.pageSize,
        currentPage: this.currentPage,
      };
      MallAPI.getOrdersList(params)
          .then((response) => {
            const jsonResult = response.data;
            this.orderList = this.orderList.concat(jsonResult.data.list);
            this.currentPage = jsonResult.data.currentPage;
            this.pageSize = jsonResult.data.pageSize;
            this.total = jsonResult.data.total;
          })
          .catch((error) => {
            console.error('載入訂單時發生錯誤：', error);
          });
    }

  },
  mounted() {
    this.loadPageTitle();
    this.loadListByCategory();
  },
  created() {

  }
}
</script>

<style>
/*通用的去ul默認格式*/
ul, li {
  margin: 0;
  padding: 0;
  list-style: none;
}

a {
  color: #4caf50;;
}

body {
  font-family: "微軟雅黑", "黑體";
}

.main-container {
  text-align: left !important;
  width: 1200px;
  margin: 0 auto;
}

.order-list-container {
  margin-top: 24px;
  background-color: #fff;
  -webkit-border-radius: 24px;
  -moz-border-radius: 24px;
  -ms-border-radius: 24px;
  border-radius: 24px;
  overflow: hidden;
}

.panel-default {
  border-color: #ddd;
}

.panel {
  margin-bottom: 20px;
  background-color: #fff;
  border: 1px solid transparent;
  -webkit-box-shadow: 0 1px 1px rgba(0, 0, 0, .05);
  box-shadow: 0 1px 1px rgba(0, 0, 0, .05);
}

.panel-default > .panel-heading {
  color: #333;
  background-color: #f5f5f5;
  border-color: #ddd;
}

.panel-heading {
  padding: 10px 15px;
  border-bottom: 1px solid transparent;
  border-top-left-radius: 3px;
  border-top-right-radius: 3px;
}

.panel-title {
  margin-top: 0;
  margin-bottom: 0;
  font-size: 16px;
  color: inherit;
}

.panel-body {
  padding: 25px;
}

.img-responsive {
  display: block;
  width: 100%;
  max-width: 100%;
  height: auto;
}

/*表格文字居中*/
.orders-table {
  text-align: center;
}

/*表頭文字居中*/
.orders-table th {
  text-align: center;
}

/*數量數字居中*/
.orders-table input {
  text-align: center;
}

/*每行間隙*/
.orders-table tbody tr {
  border-top: 1px dashed #cccccc;
}

.pull-right {
  float: right !important;
}
</style>
