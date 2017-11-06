import angular from 'angular';
import uiRouter from 'angular-ui-router';
import Components from './components/components';
import AppComponent from './app.component';
import env from './env';

angular.module('app', [
    uiRouter,
    Components
])
.constant('ENV', env)
.config(($locationProvider, $httpProvider, $qProvider) => {
    'ngInject';
    // @see: https://github.com/angular-ui/ui-router/wiki/Frequently-Asked-Questions
    // #how-to-configure-your-server-to-work-with-html5mode
    $qProvider.errorOnUnhandledRejections(false);
    $locationProvider.html5Mode(true).hashPrefix('!');
})
.component('app', AppComponent);
