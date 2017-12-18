import angular from 'angular';
import accountService from './accountService';
import swalService from './swalService';
import sessionService from './sessionService';
import studentService from './studentService';
import jwtService from './jwtService';
import importDataService from './importDataService';
import attendanceService from './attendanceService';
import departmentService from './departmentService';
import academicYearService from './academicYearService';
import examService from './examService';
import reportService from './reportService';

let servicesModule = angular.module('app.services', [])
.service({
    accountService,
    swalService,
    sessionService,
    studentService,
    jwtService,
    importDataService,
    attendanceService,
    departmentService,
    academicYearService,
    examService,
    reportService
})
.name;

export default servicesModule;