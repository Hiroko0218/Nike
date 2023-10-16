<template>
  <div>
    <!-- 浮動導航 -->
    <div class="float-link-container">
      <FloatMallLinkComponent/>
    </div>

    <!-- 頁面主體 -->
    <div class="main-container">
      <div class="cart">
        <div class="cart-filter-bar">
          <span class="switch-cart">購物車（全部{{ checkAmountNum }}）</span>
          <div class="cart-sum">
            <span class="pay-text">已選商品（不含運費）</span>
            <strong class="price">
              <span class="total-symbol">&nbsp;</span>{{ checkedCountMoney }}
            </strong>
            <a class="submit-btn submit-btn-disabled">結算</a>
          </div>
        </div>
        <div class="cart-main">
          <div class="cart-table-th">
            <div class="th th-chk">
              <div class="select-all J_SelectAll">
                <div class="cart-checkbox">
                  <el-checkbox :indeterminate="isIndeterminate" v-model="allChecked" @change="handleCheckAllChange">
                    全選
                  </el-checkbox>
                </div>
              </div>
            </div>
            <div class="th th-item">
              <div class="td-inner">商品信息</div>
            </div>
            <div class="th th-price">
              <div class="td-inner">單價</div>
            </div>
            <div class="th th-amount">
              <div class="td-inner">數量</div>
            </div>
            <div class="th th-sum">
              <div class="td-inner">金額</div>
            </div>
          </div>
          <div class="cart-now-list">
            <el-checkbox-group v-model="checkedCartItem" @change="handleCheckedCitiesChange">
              <el-checkbox v-for="item in cartList" :label="item" :key="item" style="height: auto;">
                <div class="order-body">
                  <div class="order-content">
                    <div class="item-list">
                      <ul class="item-content">
                        <li class="td td-item">
                          <div class="td-inner">
                            <a :href="item.commodityDetail" target="_blank"
                               class="item-title J_GoldReport J_MakePoint">{{ item.goodsTitle }}</a>
                          </div>
                        </li>
                        <li class="td td-price">
                          <div class="td-inner">
                            ${{ item.goodsSalePrice }}
                          </div>
                        </li>
                        <li class="td td-amount">
                          <div class="td-inner">
                            <el-input-number v-model="item.goodsNum" @change="handleChange" :min="1" :max="10"
                                             label="描述文字"
                                             size="mini">
                            </el-input-number>
                          </div>
                        </li>
                        <li class="td td-sum">
                          <div class="td-inner">${{ item.itemAllPrice }}
                          </div>
                        </li>
                      </ul>
                    </div>
                  </div>
                </div>
              </el-checkbox>
            </el-checkbox-group>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import FloatMallLinkComponent from '@/components/FloatMallLinkComponentView.vue';
import TokenAxios from "@/http/TokenAxios";
import BaseUrl from "@/http/BaseUrl";
import MallAPI from "@/http/apis/MallAPI";

export default {
  components: {
    FloatMallLinkComponent
  },
  data() {
    return {
      checkedCartItem: [],
      isIndeterminate: '',
      allChecked: '',
      checkedCountMoney: '',
      checkAmountNum: '',
      cartList: []
    }
  },
  methods: {
    handleChange(value) {
      console.log(value);
    },
    loadPageTitle() {
      this.pageTitle = 'Nike商城-購物車';
      document.title = this.pageTitle;
    },
    handleCheckAllChange(val) {
      this.checkedCartItem = val ? this.cartList : [];
      this.isIndeterminate = false;
    },
    handleCheckedCitiesChange(value) {
      let checkedCount = value.length;
      this.allChecked = checkedCount === this.cartList.length;
      this.isIndeterminate = checkedCount > 0 && checkedCount < this.cartList.length;
    },
    //查詢購物車列表
    loadCartList() {
      let url = BaseUrl.FRONT_MALL + '/carts';
      console.log('url = ' + url);

      TokenAxios.get(url).then((response) => {
        let jsonResult = response.data;
        if (jsonResult.state == 20000) {
          this.cartList = this.cartList.concat(jsonResult.data.list);
          this.total = jsonResult.data.total;
        }
      });
    },
    //添加商品到購物車
    addGoodsNum() {
      const params = {
        goodsId: this.$router.currentRoute.query.goodsId,
        goodsNum: 1
      };
      MallAPI.AddCartList(params)
          .then((response) => {
            const jsonResult = response.data;
            this.cartList = this.cartList.concat(jsonResult.data.list);
            this.total = jsonResult.data.total;
          })
          .catch((error) => {
            console.error('添加商品到購物車時發生錯誤：', error);
          });
    },
    //刪除購物車中的商品
    deleteCartList(){

    },
    //將購物車中商品的數量 +1
    increaseNum(){

    },
    //將購物車中商品的數量 -1
    reduceNum(){

    }
  },
  mounted() {
    this.loadPageTitle();
    this.loadCartList();
  },
  created() {

  }
}
</script>

<style>
a {
  color: #3c3c3c;
}

.main-container {
  text-align: left !important;
  width: 1200px;
  margin: 0 auto;
}

.cart {
  margin-top: 24px;
  min-height: 400px;
  background-color: #fff;
  -webkit-border-radius: 24px;
  -moz-border-radius: 24px;
  -ms-border-radius: 24px;
  border-radius: 24px;
  overflow: hidden;
}

.cart-filter-bar {
  overflow: hidden;
  font-size: 12px;
  position: relative;
  height: 72px;
  padding: 0 18px;
  border-bottom: 1px solid #e6e6e6;
}

.switch-cart {
  overflow: hidden;
  color: #000;
  font-size: 18px;
  font-weight: 600;
  height: 72px;
  line-height: 72px;
}

.cart-filter-bar .cart-sum {
  position: absolute;
  right: 18px;
  top: 0;
  height: 72px;
  line-height: 72px;
  font-size: 14px;
}

.cart-sum .pay-text {
  position: relative;
  top: -2px;
}

.cart-sum .price {
  font-family: Arial, Verdana;
  font-weight: 400;
  margin-right: 12px;
  font-size: 22px;
  color: #FF5000;
}

.cart-sum .submit-btn {
  display: inline-block;
  width: 74px;
  height: 42px;
  line-height: 42px;
  color: #fff;
  background: #FF5000;
  -webkit-border-radius: 21px;
  -moz-border-radius: 21px;
  -ms-border-radius: 21px;
  border-radius: 21px;
  text-align: center;
  cursor: pointer;
  text-decoration: none;
  position: relative;
  top: -2px;
  font-size: 16px;
}

.item-list {
  margin-left: 30px;
}

.cart-main {
  min-height: 210px;
}

.cart-table-th {
  margin-left: 60px;
  overflow: hidden;
  height: 50px;
  line-height: 50px;
  color: #3c3c3c;
  font-weight: 700;
}

.cart-table-th .th {
  float: left;
}

.cart-table-th .th-chk {
  margin-right: 10px;
  font-size: 12px;
  text-align: left;
  position: relative;
  height: 50px;
}

.th-item, .td-item {
  margin-right: 10px;
  width: 45%;
}

.th-price, .td-price {
  width: 15%;
}

.th-amount, .td-amount {
  width: 15%;
}

.th-sum, .td-sum {
  width: 15%;
}

.order-body {
  margin-bottom: 15px;
  *position: relative;
}

.cart-now-list .el-checkbox {
  width: 100% !important;
}

.order-content {
  border: 1px solid #F5F5F5;
  background-color: #F5F5F5;
  -webkit-border-radius: 18px;
  -moz-border-radius: 18px;
  -ms-border-radius: 18px;
  border-radius: 18px;
  margin: 0 24px;
  overflow: hidden;
}

.cart-now-list .el-checkbox__input {
  margin: 10px;
}

.cart-now-list .el-checkbox__label {
  width: 97%;
}


.item-content {
  padding-right: 12px;

}

.item-content .td-inner {
  padding-top: 20px;
  _zoom: 1;

}

.item-content .td-chk {
  margin-right: 10px;
  position: relative;
  overflow: hidden;
}

.item-content .td {
  float: left;
  min-height: 119px;
  _height: 119px;
  overflow: hidden;
  _overflow: visible;
  _zoom: 1;
}

.item-content .td-price {
  color: #9c9c9c;
  text-align: center;
}


.item-content .td-sum {
  color: #f40;
  font-weight: 700;
  text-align: center;
}
</style>
