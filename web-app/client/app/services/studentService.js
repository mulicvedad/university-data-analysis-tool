import BaseService from './baseService';

export default class StudentService extends BaseService {
    enrollmentReport(academic_year) {
        return super.get("academic_year?year_value=" + academic_year);
    } 
}