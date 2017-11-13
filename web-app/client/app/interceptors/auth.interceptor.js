function authInterceptor(jwtService, ENV, $q, $injector) {
    'ngInject';

    return {
        // add headers with authorization information to all requests for out API
        request: (config) => {
            if(config.url.indexOf(ENV.BACKEND_BASE_URL) === 0 && jwtService.getToken()) {
                config.headers.Authorization = 'Bearer ' + jwtService.getToken();
            }
            return config;
        },

        responseError: (rejection) => {            
            return $q.reject(rejection);
        }
    }
}

export default authInterceptor;