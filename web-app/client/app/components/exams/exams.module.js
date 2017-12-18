import angular from 'angular';
import uiRouter from 'angular-ui-router';
import angularChart from 'angular-chart.js';
import examsComponent from './exams.component';

let examsModule = angular.module('exams', [
  uiRouter,
  angularChart
])

.config(($stateProvider, $urlRouterProvider) => {
  "ngInject";

  $urlRouterProvider.otherwise('/');

  $stateProvider
    .state('exams', { 
      url: '/',
      component: 'exams'
    });
})
.component('exams', examsComponent)  
.name;

export default examsModule;