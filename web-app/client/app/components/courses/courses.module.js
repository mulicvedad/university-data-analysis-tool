import angular from 'angular';
import uiRouter from 'angular-ui-router';
import angularChart from 'angular-chart.js';
import coursesComponent from './courses.component';

let coursesModule = angular.module('courses', [
  uiRouter,
  angularChart
])

.config(($stateProvider, $urlRouterProvider) => {
  "ngInject";

  $urlRouterProvider.otherwise('/');

  $stateProvider
    .state('courses', { 
      url: '/',
      component: 'courses'
    });
})
.component('courses', coursesComponent)  
.name;

export default coursesModule;