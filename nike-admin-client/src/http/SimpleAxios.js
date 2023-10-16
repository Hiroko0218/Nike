import axios from 'axios';
import qs from 'qs';

const SimpleAxios = {
    get(url, params) {
        return axios
            .get(url, qs.stringify(params));
    },
    post(url, params) {
        return axios
            .post(url, qs.stringify(params));
    }
}

export default SimpleAxios;