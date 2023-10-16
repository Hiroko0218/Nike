import URLTitle from "../URLTitle";
import TokenAxios from "../TokenAxios";
import BaseUrl from "@/http/BaseUrl";


/** 封裝附件上傳服務所有的接口方法 */
const UploadAPI= {
    /**
     * 上傳用戶頭像
     */
    UploadAvatar(params){
        let url = URLTitle.ATTACHMENT_UPLOAD_AVATAR;
        return TokenAxios.post(url, params)
    },
    /**
     * 上傳文章圖片
     */
    UploadArticle(params){
        let url = URLTitle.ATTACHMENT_UPLOAD_ARTICLE;
        return TokenAxios.post(url, params)
    },
    /**
     * 上傳商品圖片
     */
    UploadGoods(params){
        let url = URLTitle.ATTACHMENT_UPLOAD_GOODS;
        return TokenAxios.post(url, params)
    },
}

export default UploadAPI;
