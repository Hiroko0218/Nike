import URLTitle from "../URLTitle"
import TokenAxios from "../TokenAxios"



/** 封裝基礎數據服務所有的接口方法 */
const BasicAPI= {
    /**
     * 根據父級查詢子級地區列表
     */
    GetBasicDictList(params){
        let url = URLTitle.BASIC_DICT_LIST;
        return TokenAxios.get(url, params)
    },

}

export default BasicAPI;
