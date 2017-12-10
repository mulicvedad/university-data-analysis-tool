import angular from 'angular';
import uiRouter from 'angular-ui-router';
import angularChart from 'angular-chart.js';
import studentsComponent from './students.component';

let studentsModule = angular.module('students', [
  uiRouter,
  angularChart
])

.config(($stateProvider, $urlRouterProvider) => {
  "ngInject";

  $urlRouterProvider.otherwise('/');

  $stateProvider
    .state('students', { 
      url: '/',
      component: 'students'
    });
})
.component('students', studentsComponent)  
.name;

export default studentsModule;