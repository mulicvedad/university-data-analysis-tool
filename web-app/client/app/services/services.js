import angular from 'angular';
import accountService from './accountService';
import swalService from './swalService';

let servicesModule = angular.module('app.services', [])
.service({
    accountService,
    swalService
})
.name;

export default servicesModule;