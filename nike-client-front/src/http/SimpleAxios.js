import axios from 'axios'
import qs from 'qs'
const instance = axios.create()

const simpleAxios = {
    /**
     * 發送一個get請求
     * @param {string} url  請求資源路徑
     * @param {object} params  請求參數對象  {key:value, key2:value2}
     */
    get(url, params){
        return instance({
            method: 'GET',
            url,
            params,
        })
    },

    /**
     * 發送post請求
     * @param {string} url  請求路徑
     * @param {object} params 請求參數對象 {page:1}
     */
    post(url, params){
        return instance({
            url,
            method: 'POST',
            data: qs.stringify(params)
        })
    }

}
export default simpleAxios