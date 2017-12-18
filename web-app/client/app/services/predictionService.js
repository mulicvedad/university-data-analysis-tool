import BaseService from './baseService';

export default class ImportDataService extends BaseService {
    enrollmentPrediction(budgetNum) {
        return super.get("prediction/enrollment?budget_students=" + budgetNum);
    } 
}