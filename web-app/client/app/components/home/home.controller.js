class HomeController {
    static $inject = ["studentService", "importDataService", "predictionService"];

    constructor(studentService, importDataService, predictionService) {
        this.studentService = studentService;
        this.importDataService = importDataService;
        this.predictionService = predictionService;
        this.predictedNumber = 0;
        this.budgetNum = 1;
    }

    loadPredictionData(budgetNum) {
        this.predictionService.enrollmentPrediction(budgetNum).then((response) => {
            this.predictedNumber = response.data;
        });
    }

    importData() {
        this.importDataService.importData();
    }
}


export default HomeController;
