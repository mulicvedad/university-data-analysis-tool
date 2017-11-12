import angular from 'angular';
import Home from './home/home.module';
import Login from './login/login.module';
import NavigationBar from './navigationBar/navigationBar.module';

let Components = angular.module('app.components', [
  Home,
  Login,
  NavigationBar
])
.name;

export default Components;
