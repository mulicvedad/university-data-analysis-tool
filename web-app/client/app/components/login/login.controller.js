class LoginController {
    static $inject = ["accountService", "$state", "swalService", "sessionService"];

    constructor(accountService, $state, swalService, sessionService) {
        this.accountService = accountService;
        this.$state = $state;
        this.swalService = swalService;
        this.sessionService = sessionService;
        this.error = "";
        this.user = {};
    }

    login() {
        if (!this.form.$valid) {            
    		return;
    	}
        this.accountService.login(this.user).then((response) => {
            this.sessionService.startSession(response.data);
            this.$state.go("home");
        }, (error) => {
            this.swalService.displayError("Neispravni podaci za prijavu.");
        });
    }

}

export default LoginController;