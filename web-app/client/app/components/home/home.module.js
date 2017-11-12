import angular from 'angular';
import uiRouter from 'angular-ui-router';
import angularChart from 'angular-chart.js';
import homeComponent from './home.component';

let homeModule = angular.module('home', [
  uiRouter,
  angularChart
])

.config(($stateProvider, $urlRouterProvider) => {
  "ngInject";

  $urlRouterProvider.otherwise('/');

  $stateProvider
    .state('home', { 
      url: '/',
      component: 'home'
    });
})
.component('home', homeComponent)  
.name;

export default homeModule;
