import axiosApi from './http-common';

class BackendService {
    hello() {
        return axiosApi.get(`/hello`);
    }

    getUser(userId) {
        return axiosApi.get(`/user/` + userId);
    }

    createUser(firstName, lastName) {
        return axiosApi.post(`/user/` + firstName + '/' + lastName);
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