export default class SessionService {
    static $inject = ['$window'];

    constructor($window){
        this.$window = $window;
        this.user = JSON.parse($window.localStorage.getItem('user'));
    }

    userInfo(){
        return this.user;
    }

    startSession(userInfo){
        this.$window.localStorage.setItem('user', JSON.stringify(userInfo));
        this.user = userInfo;
    }

    endSession(){
        this.$window.localStorage.setItem('user', null);
        this.user = null;
    }

    isUserLoggedIn(){
        return this.user == null ? false : true;
    }

    userRole() {
        return this.user == null ? "" : this.user.role;
    }

}