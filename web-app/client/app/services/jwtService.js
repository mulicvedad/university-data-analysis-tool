export default class JwtService {
    static $inject = ['$window'];

    constructor($window){
        this.$window = $window;
    }

    getToken(){
        let user = JSON.parse(this.$window.localStorage.getItem('user'));
        return user != null ? user.jwt : null;
    }

}