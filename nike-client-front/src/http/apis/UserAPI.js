import URLTitle from "../URLTitle"
import SimpleAxios from "../SimpleAxios"
import TokenAxios from "../TokenAxios"



/** 封裝用戶所有的接口方法 */
const userAPI= {
    /**
     * 登入
     * @param {Object} params {username:xx, password:xxx}
     */
    login(params){
        let url =URLTitle.PASSPORT_LOGIN;
        return SimpleAxios.post(url, params)
    },
    /**
     * 注冊
     * @param {Object} params {avatar: null, confirmPassword: 123456, email: student@tedu.cn, password: 123456, phone: 13800138008,username: student1}
     */
    register(params){
        let url = URLTitle.FRONT_ACCOUNT_USERS_REGISTER;
        return SimpleAxios.post(url, params)
    },
    /**
     * 退出登入
     */
    logout(){
        let url = URLTitle.PASSPORT_LOGOUT
        return TokenAxios.post(url)
    },
    /**
     * 修改基本信息
     */
    UpdateUsersInfoByID(params){
        let url = URLTitle.FRONT_ACCOUNT_USERS_INFO_UPDATE;
        return TokenAxios.post(url, params)
    },
    /**
     * 修改密碼
     */
    UpdateUsersPasswordByID(params){
        let url = URLTitle.FRONT_ACCOUNT_USERS_PASSWORD_UPDATE;
        return TokenAxios.post(url, params)
    },
    /**
     * 修改頭像
     */
    UpdateUsersAvatarByID(params){
        let url = URLTitle.FRONT_ACCOUNT_USERS_AVATAR_UPDATE;
        return TokenAxios.post(url, params)
    },
    /**
     * 修改手機號碼
     */
    UpdateUsersPhoneByID(params){
        let url = URLTitle.FRONT_ACCOUNT_USERS_PHONE_UPDATE;
        return TokenAxios.post(url, params)
    },
    /**
     * 修改電子郵箱
     */
    UpdateUsersEmailByID(params){
        let url = URLTitle.FRONT_ACCOUNT_USERS_EMAIL_UPDATE;
        return TokenAxios.post(url, params)
    },
    /**
     * 查詢當前用戶基本信息
     */
    GetUsersInfoByID(params){
        let url = URLTitle.FRONT_ACCOUNT_USERS_INFO;
        return TokenAxios.get(url, params)
    },
    /**
     * 查詢登入日誌列表
     */
    GetLoginLogs(params){
        let url = URLTitle.PASSPORT_LOGIN_LOGS;
        return TokenAxios.get(url, params)
    },
}

export default userAPI;
