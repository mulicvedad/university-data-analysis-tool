import angular from 'angular';
import uiRouter from 'angular-ui-router';
import angularChart from 'angular-chart.js';
import components from './components/components';
import appComponent from './app.component';
import env from './env';
import services from './services/services';
import authInterceptor from './interceptors/auth.interceptor';
import showAuthenticated from './directives/showAuthenticated.directive';
import showForRole from './directives/showForRole.directive';
import sessionService from './services/sessionService';

angular.module('app', [
    uiRouter,
    angularChart,
    components,
    services
])
.constant('ENV', env)
.config(($locationProvider, $httpProvider, $qProvider) => {
    'ngInject';
    // @see: https://github.com/angular-ui/ui-router/wiki/Frequently-Asked-Questions
    // #how-to-configure-your-server-to-work-with-html5mode
    $qProvider.errorOnUnhandledRejections(false);
    $locationProvider.html5Mode(true).hashPrefix('!');
    $httpProvider.interceptors.push(authInterceptor);
})
.component('app', appComponent)
.directive('showAuthenticated', showAuthenticated)
.directive('showForRole', showForRole)
.run((sessionService, $state, $transitions) => {
    'ngInject';
    
    $transitions.onStart({ to: 'login' }, (trans) => {
        if (sessionService.isUserLoggedIn() && trans.$to.name != "login") {
            return $state.target('students');
        }
        else {
            return true;
        }
    });
    

    $transitions.onStart({ to: (state) =>{
        return state.name != 'login';
    } }, (trans) => {
        if (!sessionService.isUserLoggedIn()) {
            return $state.target('login');
        }
        else {
            return true;
        }
    });

});
