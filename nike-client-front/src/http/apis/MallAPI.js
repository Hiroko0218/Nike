import URLTitle from "../URLTitle";
import TokenAxios from "../TokenAxios";
import BaseUrl from "@/http/BaseUrl";

/** 封裝商城所有的接口方法 */
const MallAPI= {
    /**
     * 查詢類別樹
     * @param {Object} params {id:1}
     */
    getCategoriesTree(params){
        let url =URLTitle.FRONT_MALL_CATEGORIES_TREE;
        return TokenAxios.get(url,params)
    },
    /**
     * 根據父級查詢子級列表
     */
    getCategoriesByParent(params){
        let url = URLTitle.FRONT_MALL_CATEGORIES_LIST_BY_PARENT;
        return TokenAxios.get(url, params)
    },
    /**
     * 根據ID查詢商品
     */
    getGoodsByID(params){
        let url =BaseUrl.FRONT_MALL+"/goods/"+params.id;
        return TokenAxios.get(url,params)
    },
    /**
     * 查詢推薦的商品列表
     */
    getGoodsByRecommend(params){
        let url = URLTitle.FRONT_MALL_GOODS_LIST_BY_RECOMMEND
        return TokenAxios.get(url,params)
    },
    /**
     * 根據類別查詢商品列表
     */
    getGoodsByCategory(params){
        let url = URLTitle.FRONT_MALL_GOODS_LIST_BY_CATEGORY
        return TokenAxios.get(url,params)
    },
    /**
     * 添加收貨地址
     */
    AddAddresses(params){
        let url = URLTitle.FRONT_MALL_ADDRESSES_ADD
        return TokenAxios.post(url,params)
    },
    /**
     * 刪除收貨地址
     */
    DeleteAddressesByID(params){
        let url =BaseUrl.FRONT_MALL+"/receiver-addresses/"+params+"/delete";
        return TokenAxios.post(url,params)
    },
    /**
     * 修改收貨地址
     */
    UpdateAddressesByID(params){
        let url =BaseUrl.FRONT_MALL+"/receiver-addresses/"+params+"/update";
        return TokenAxios.post(url,params)
    },
    /**
     * 設置默認收貨地址
     */
    SetDefaultAddressesByID(params){
        let url =BaseUrl.FRONT_MALL+"/receiver-addresses/"+params+"/set-default";
        return TokenAxios.post(url,params)
    },
    /**
     * 根據ID查詢收貨地址
     */
    getAddressesByID(params){
        let url =BaseUrl.FRONT_MALL+"/receiver-addresses/"+params.id;
        return TokenAxios.get(url,params)
    },
    /**
     * 查詢收貨地址列表
     */
    getAddressesList(params){
        let url = URLTitle.FRONT_MALL_ADDRESSES_LIST
        return TokenAxios.get(url,params)
    },

    /**
     * 發表商品評論
     */
    AddComments(params){
        let url = URLTitle.FRONT_MALL_COMMENTS_ADD
        return TokenAxios.post(url,params)
    },
    /**
     * 查詢商品的評論列表
     */
    getCommentsList(params){
        let url = URLTitle.FRONT_MALL_COMMENTS_LIST
        return TokenAxios.get(url,params)
    },

    /**
     * 添加商品到購物車
     */
    AddCartList(params){
        let url = URLTitle.FRONT_MALL_CARTS_ADD
        return TokenAxios.post(url,params)
    },

    /**
     * 查詢商品的評論列表
     */
    getOrdersList(params){
        let url = URLTitle.FRONT_MALL_ORDERS
        return TokenAxios.get(url,params)
    },
}

export default MallAPI;
