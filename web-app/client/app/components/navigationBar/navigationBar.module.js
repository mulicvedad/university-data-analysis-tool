import angular from 'angular';
import uiRouter from 'angular-ui-router';
import navigationBarComponent from './navigationBar.component';

let navigationBarModule = angular.module('navigationBar', [
  uiRouter
])
.component('navigationBar', navigationBarComponent)
.name;

export default navigationBarModule;