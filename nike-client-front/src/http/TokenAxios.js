import axios from 'axios'
import qs from 'qs'
const tokenInstance = axios.create(
    // {'headers': {'Authorization': (localStorage.getItem('loginInfo')==null||localStorage.getItem('loginInfo')==='')?'':JSON.parse(localStorage.getItem('loginInfo')).token}}
)

const tokenAxios = {
    /**
     * 發送一個get請求
     * @param {string} url  請求資源路徑
     * @param {object} params  請求參數對象  {key:value, key2:value2}
     */
    get(url, params){
        return tokenInstance({
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
        return tokenInstance({
            url,
            method: 'POST',
            data: qs.stringify(params)
        })
    }

}
export default tokenAxios