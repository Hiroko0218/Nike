/** src/http/index.js
 *  httpApi對象中封裝所有的接口方法 */
import userAPI from "./apis/UserAPI";
import UploadAPI from "./apis/UploadAPI";
import MallAPI from "./apis/MallAPI";
import BasicAPI from "./apis/BasicAPI";
import ArticlesAPI from "./apis/ArticlesAPI";


const httpApi = {
    userAPI,
    UploadAPI,
    MallAPI,
    BasicAPI,
    ArticlesAPI
}

export default httpApi;