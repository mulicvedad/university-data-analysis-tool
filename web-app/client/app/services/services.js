import angular from 'angular';
import accountService from './accountService';

let servicesModule = angular.module('app.services', [])
.service({
    accountService
})
.name;

export default servicesModule;