import angular from 'angular';
import uiRouter from 'angular-ui-router';
import angularChart from 'angular-chart.js';
import dataImportComponent from './dataImport.component';

let dataImport = angular.module('dataImport', [
  uiRouter,
  angularChart
])

.config(($stateProvider, $urlRouterProvider) => {
  "ngInject";

  $urlRouterProvider.otherwise('/');

  $stateProvider
    .state('dataImport', { 
      url: '/ucitavanje-podataka',
      component: 'dataImport'
    });
})
.component('dataImport', dataImportComponent)  
.name;

export default dataImport;
