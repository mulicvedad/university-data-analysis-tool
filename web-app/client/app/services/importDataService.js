import BaseService from './baseService';

export default class ImportDataService extends BaseService {
    importData() {
        return super.get("import");
    } 

    allTablesInfo() {
        return super.get("import/all_tables");
    }
}