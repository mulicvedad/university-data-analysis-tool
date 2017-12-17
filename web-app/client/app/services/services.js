import angular from 'angular';
import accountService from './accountService';
import swalService from './swalService';
import sessionService from './sessionService';
import studentService from './studentService';
import jwtService from './jwtService';
import importDataService from './importDataService';

let servicesModule = angular.module('app.services', [])
.service({
    accountService,
    swalService,
    sessionService,
    studentService,
    jwtService,
    importDataService
})
.name;

export default servicesModule;