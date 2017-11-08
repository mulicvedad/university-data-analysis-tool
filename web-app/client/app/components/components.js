import angular from 'angular';
import Home from './home/home';
import Login from './login/login.module';

let Components = angular.module('app.components', [
  Home,
  Login
])
.name;

export default Components;
