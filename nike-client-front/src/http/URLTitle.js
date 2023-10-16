import BaseUrl from "../http/BaseUrl"

const URLTitle = {

    // 所有的非動態url
    //BASIC
    BASIC_DICT_LIST: BaseUrl.BASIC + "/dict/district/list-by-parent",//根據父級查詢子級地區列表

    //PASSPORT
    PASSPORT_LOGIN: BaseUrl.PASSPORT + "/passport/login",//用戶登入
    PASSPORT_LOGOUT: BaseUrl.PASSPORT + "/passport/logout",//退出登入
    PASSPORT_LOGIN_LOGS: BaseUrl.PASSPORT + "/login-logs",//查詢登入日誌列表

    //ATTACHMENT
    ATTACHMENT_UPLOAD_AVATAR: BaseUrl.ATTACHMENT + "/upload/image/user-avatar",//上傳用戶頭像
    ATTACHMENT_UPLOAD_ARTICLE: BaseUrl.ATTACHMENT + "/upload/image/article",//上傳文章圖片
    ATTACHMENT_UPLOAD_GOODS: BaseUrl.ATTACHMENT + "/upload/image/goods",//上傳商品圖片

    //FRONT_ACCOUNT
    FRONT_ACCOUNT_USERS_REGISTER: BaseUrl.FRONT_ACCOUNT + "/users/register",//用戶注冊
    FRONT_ACCOUNT_USERS_INFO_UPDATE: BaseUrl.FRONT_ACCOUNT + "/users/info/update",//修改基本信息
    FRONT_ACCOUNT_USERS_PASSWORD_UPDATE: BaseUrl.FRONT_ACCOUNT + "/users/password/update",//修改密碼
    FRONT_ACCOUNT_USERS_AVATAR_UPDATE: BaseUrl.FRONT_ACCOUNT + "/users/avatar/update",//修改頭像
    FRONT_ACCOUNT_USERS_PHONE_UPDATE: BaseUrl.FRONT_ACCOUNT + "/users/phone/update",//修改手機號碼
    FRONT_ACCOUNT_USERS_EMAIL_UPDATE: BaseUrl.FRONT_ACCOUNT + "/users/email/update",//修改電子郵箱
    FRONT_ACCOUNT_USERS_INFO: BaseUrl.FRONT_ACCOUNT + "/users/info",//查詢當前用戶基本信息

    //FRONT_CONTENT
    FRONT_CONTENT_CATEGORIES: BaseUrl.FRONT_CONTENT + "/categories",//查詢類別列表
    FRONT_CONTENT_ARTICLES_RECOMMEND: BaseUrl.FRONT_CONTENT + "/articles/recommend",//查詢推薦的文章列表
    FRONT_CONTENT_ARTICLES_LIST_BY_CATEGORY: BaseUrl.FRONT_CONTENT + "/articles/list-by-category",//根據類別查詢文章列表
    FRONT_CONTENT_COMMENTS_ADD: BaseUrl.FRONT_CONTENT + "/comments/add-new/article-comment",//發表文章評論
    FRONT_CONTENT_COMMENTS_BY_ARTICLES: BaseUrl.FRONT_CONTENT + "/comments/list-by-article",//根據文章查詢評論列表

    //FRONT_MALL
    FRONT_MALL_CATEGORIES_TREE: BaseUrl.FRONT_MALL + "/categories/tree",//查詢類別樹
    FRONT_MALL_CATEGORIES_LIST_BY_PARENT: BaseUrl.FRONT_MALL + "/categories/list-by-parent",//根據父級查詢子級列表
    FRONT_MALL_GOODS_LIST_BY_RECOMMEND: BaseUrl.FRONT_MALL + "/goods/list-by-recommend",//查詢推薦的商品列表
    FRONT_MALL_GOODS_LIST_BY_CATEGORY: BaseUrl.FRONT_MALL + "/goods/list-by-category",//根據類別查詢商品列表

    FRONT_MALL_ADDRESSES_ADD: BaseUrl.FRONT_MALL + "/receiver-addresses/add-new",//添加收貨地址
    FRONT_MALL_ADDRESSES_LIST: BaseUrl.FRONT_MALL + "/receiver-addresses",//查詢收貨地址列表

    FRONT_MALL_COMMENTS_ADD: BaseUrl.FRONT_MALL + "/comments/add-new/goods-comment",//發表商品評論
    FRONT_MALL_COMMENTS_LIST: BaseUrl.FRONT_MALL + "/comments/list-by-goods",//查詢商品的評論列表

    FRONT_MALL_CARTS_ADD: BaseUrl.FRONT_MALL + "/carts/add",//添加商品到購物車

    FRONT_MALL_ORDERS: BaseUrl.FRONT_MALL + "/orders", //查詢訂單列表

}


export default URLTitle