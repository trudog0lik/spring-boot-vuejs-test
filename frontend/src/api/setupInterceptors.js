import axiosInstance from "./http-common";

const setupInterceptors = (store, router) => {
    axiosInstance.interceptors.response.use(
        (res) => {
            return res;
        },
        async (err) => {
            const originalConfig = err.config;

            // we should exclude urls 'auth/login' and 'bookmarks/isIn'
            // because NotAuthorized error on this endpoints does not make sense
            if (originalConfig.url !== "/secured" && err.response) {
                // Access Token was expired
                if (err.response.status === 401 || err.response.status === 400) {
                    originalConfig._retry = true;
                    // store.dispatch("auth/logout");
                    router.push('/login');
                }
            }
            return Promise.reject(err);
        }
    );
}

export default setupInterceptors;