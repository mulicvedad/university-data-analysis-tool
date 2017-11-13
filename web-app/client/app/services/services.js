import angular from 'angular';
import accountService from './accountService';
import swalService from './swalService';
import sessionService from './sessionService';
import studentService from './studentService';
import jwtService from './jwtService';

let servicesModule = angular.module('app.services', [])
.service({
    accountService,
    swalService,
    sessionService,
    studentService,
    jwtService
})
.name;

export default servicesModule;