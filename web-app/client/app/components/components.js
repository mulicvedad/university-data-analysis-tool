import angular from 'angular';
import Home from './home/home';

let Components = angular.module('app.components', [
  Home
])
.name;

export default Components;
