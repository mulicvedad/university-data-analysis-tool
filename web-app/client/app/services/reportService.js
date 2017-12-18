export default class ReportService {
    static $inject = ['$http', 'ENV'];
    
    constructor($http, ENV) {
        this.$http = $http;
        this.ENV = ENV;
        this.reportsPath = 'report/';
        this.overallReportSubpath = '/overallReport';
    }

    enrollmentOverallReport() {
        this.generateReport('enrollment', 'enrollment-report');
    }

    examOverallReport() {
        this.generateReport('exam', 'exam-report');
    }

    attendanceOverallReport() {
        this.generateReport('attendance', 'attendance-report');
    }

    generateReport(entityName, fileName) {
        return this.$http.get(this.ENV.BACKEND_BASE_URL + this.reportsPath + entityName + this.overallReportSubpath , 
        {
			responseType: 'arraybuffer'
		}).then(response => {
            //male tajne velikih majstora
			let file = new Blob([response.data], {type: 'application/pdf'});
       		let fileURL = window.URL.createObjectURL(file);

      		let  linkElement = document.createElement('a');

      		linkElement.setAttribute('href', fileURL);
      		linkElement.setAttribute("download", fileName);
     		linkElement.click();
		}); 
	}
}