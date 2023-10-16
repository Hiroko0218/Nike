import axios from 'axios';
import qs from 'qs';

const TokenAxios = {
    get(url, params) {
        return axios
            .create({
                'headers': {
                    'Authorization': JSON.parse(localStorage.getItem('loginInfo')).token
                }
            })
            .get(url, qs.stringify(params));
    },
    post(url, params) {
        return axios
            .create({
                'headers': {
                    'Authorization': JSON.parse(localStorage.getItem('loginInfo')).token
                }
            })
            .post(url, qs.stringify(params));
    }
}

export default TokenAxios;