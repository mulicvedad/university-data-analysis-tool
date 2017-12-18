import BaseService from './baseService';

export default class DepartmentService extends BaseService {
    departments(page) {
        return super.get("departments?page=" + page);
    }
}