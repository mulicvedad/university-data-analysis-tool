import angular from 'angular';
import accountService from './accountService';
import swalService from './swalService';
import sessionService from './sessionService';

let servicesModule = angular.module('app.services', [])
.service({
    accountService,
    swalService,
    sessionService
})
.name;

export default servicesModule;