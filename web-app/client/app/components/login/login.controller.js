class LoginController {
    static $inject = ["accountService", "$state", "swalService"];

    constructor(accountService, $state, swalService) {
        this.accountService = accountService;
        this.$state = $state;
        this.swalService = swalService;
        this.error = "";
        this.user = {};
    }

    login() {
        if (!this.form.$valid) {            
    		return;
    	}
        this.accountService.login(this.user).then((response) => {
            this.$state.go("home");
        }, (error) => {
            this.swalService.displayError("Neispravni podaci za prijavu.");
        });
    }

}

export default LoginController;