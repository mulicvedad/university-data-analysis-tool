class LoginController {
    static $inject = ["accountService", "$state"];

    constructor(accountService, $state, sessionService) {
        this.accountService = accountService;
        this.$state = $state;
        this.error = "";
        this.user = {};
    }

    login() {
        if (!this.form.$valid) {
    		return;
    	}
        this.accountService.login(this.user).then((response) => {
            this.$state.go("home");
        }, (error) => {});
    }

}

export default LoginController;