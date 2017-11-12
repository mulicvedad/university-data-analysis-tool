import angular from 'angular';
import accountService from './accountService';
import swalService from './swalService';
import sessionService from './sessionService';
import studentService from './studentService';

let servicesModule = angular.module('app.services', [])
.service({
    accountService,
    swalService,
    sessionService,
    studentService
})
.name;

export default servicesModule;