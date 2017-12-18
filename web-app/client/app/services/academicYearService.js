import BaseService from './baseService';

export default class AcademicYearService extends BaseService {
    academicYears(page) {
        return super.get("academic_years?page=" + page);
    }
    
}