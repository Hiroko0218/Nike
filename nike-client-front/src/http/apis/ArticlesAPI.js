import URLTitle from "../URLTitle"
import SimpleAxios from "@/http/SimpleAxios";
import TokenAxios from "../TokenAxios"
import BaseUrl from "@/http/BaseUrl";


/** 封裝文章所有的接口方法 */
const ArticlesAPI= {
    /**
     * 查詢類別列表
     */
    getCategoriesList(params){
        let url = URLTitle.FRONT_CONTENT_CATEGORIES;
        return TokenAxios.get(url, params)
    },
    /**
     * 根據ID查詢文章
     * @param {Object} params {id:1}
     */
    getArticleByID(params){
        let url =BaseUrl.FRONT_CONTENT+"/articles/"+params.id;
        return TokenAxios.get(url,params)
    },
    /**
     * 查詢推薦的文章列表
     */
    getArticlesByRecommend(params){
        let url = URLTitle.FRONT_CONTENT_ARTICLES_RECOMMEND;
        return TokenAxios.get(url, params)
    },
    /**
     * 根據類別查詢文章列表
     */
    getArticlesByCategory(params){
        let url = URLTitle.FRONT_CONTENT_ARTICLES_LIST_BY_CATEGORY
        return TokenAxios.get(url,params)
    },
    /**
     * 發表文章評論
     */
    AddContentComments(params){
        let url = URLTitle.FRONT_CONTENT_COMMENTS_ADD;
        return TokenAxios.post(url, params)
    },
    /**
     * 根據文章查詢評論列表
     */
    getContentCommentsByArticles(params){
        let url = URLTitle.FRONT_CONTENT_COMMENTS_BY_ARTICLES;
        return TokenAxios.get(url, params)
    },
}

export default ArticlesAPI;
