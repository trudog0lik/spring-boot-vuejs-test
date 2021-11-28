import axiosApi from './http-common';

class BackendService {
    hello() {
        return axiosApi.get(`/hello`);
    }

    getUser(userId) {
        return axiosApi.get(`/user/` + userId);
    }

    createUser(username, password) {
        return axiosApi.post('auth/signup', {
            username,
            password
        });
    }

    getSecured(user, password) {
        return axiosApi.get(`/secured/`,{
            auth: {
                username: user,
                password: password
            }});
    }
}

export default new BackendService();