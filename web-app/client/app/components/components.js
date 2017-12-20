import angular from 'angular';
import Home from './home/home.module';
import Login from './login/login.module';
import NavigationBar from './navigationBar/navigationBar.module';
import Students from './students/students.module';
import Exams from './exams/exams.module';
import Courses from './courses/courses.module';
import DataImport from './dataImport/dataImport.module';

let Components = angular.module('app.components', [
  Home,
  Login,
  NavigationBar,
  Students,
  Exams,
  Courses,
  DataImport
  ])
.name;

export default Components;
