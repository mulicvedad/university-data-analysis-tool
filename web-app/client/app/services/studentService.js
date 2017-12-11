import BaseService from './baseService';

export default class StudentService extends BaseService {
    enrollmentByAcademicYear(academic_year) {
        return super.get("enrollment/academic_year?ay=" + academic_year);
    }
    enrollmentByDepartment(department_id) {
        return super.get("enrollment/department?dep=" + department);
    } 
    enrollmentByBudget(isOnBudget) {
        return super.get("enrollment/budget?budget=" + isOnBudget);
    }
    enrollmentByRepeating(isRepeating) {
        return super.get("enrollment/isRepeating?isRepeating=" + isRepeating);
    }
    budgetPercentage() {
        return super.get("enrollment/budgetPercentage");
    }

}