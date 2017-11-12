import BaseService from './baseService';

export default class StudentService extends BaseService {
    enrollmentByYears(numOfYears) {
        return super.get("studentReport?numYears=" + numOfYears);
    } 
}