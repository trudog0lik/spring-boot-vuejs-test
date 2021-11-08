import axios from 'axios'

const axiosApi = axios.create({
    baseURL: `/api`,
    timeout: 1000,
    headers: { 'Content-Type': 'application/json' }
});

// class User {
//     id;
//     firstName;
//     lastName;
// }

export default {
    hello() {
        return axiosApi.get(`/hello`);
    },
    getUser(userId) {
        return axiosApi.get(`/user/` + userId);
    },
    createUser(firstName, lastName) {
        return axiosApi.post(`/user/` + firstName + '/' + lastName);
    },
    getSecured(user, password) {
        return axiosApi.get(`/secured/`,{
            auth: {
                username: user,
                password: password
            }});
    }
}


