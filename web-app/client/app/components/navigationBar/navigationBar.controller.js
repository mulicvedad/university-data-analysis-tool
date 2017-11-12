class NavigationBarController {
    static $inject = ['sessionService', '$state'];

    constructor(sessionService, $state) {
        this.sessionService = sessionService;
        this.$state = $state;
    }

    logout() {
        this.sessionService.endSession();
        this.$state.go('login');
    }
}

export default NavigationBarController;