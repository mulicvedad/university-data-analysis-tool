import angular from 'angular';
import uiRouter from 'angular-ui-router';
import loginComponent from './login.component';


let loginModule = angular.module('login', [
    uiRouter
])
.config(($stateProvider, $urlRouterProvider) => {
    "ngInject";

    $urlRouterProvider.otherwise('/');

    $stateProvider
        .state('login', { 
        url: '/prijava',
        component: 'login'
        });
})
.component('login', loginComponent)  
.name;

export default loginModule;