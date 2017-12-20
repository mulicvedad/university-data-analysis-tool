class HomeController {
    static $inject = ["studentService", "importDataService", "predictionService"];

    constructor(studentService, importDataService, predictionService) {
        this.studentService = studentService;
        this.importDataService = importDataService;
        this.predictionService = predictionService;
        this.predictedNumber = 0;
        this.currentAcademicYear = 2017;
        this.setupCharts();
        this.loadData();
    }

    loadPredictionData(budgetNum) {
        this.predictionService.enrollmentPrediction(budgetNum).then((response) => {
            this.dataPred[0] = response.data;
        });
    }

    setupCharts() {
        this.options = {
            scales: {
              yAxes: [
                {
                    ticks: {
                        beginAtZero:true,
                        max: 300
                    },
                    id: 'y-axis-1',
                    type: 'linear',
                    display: true,
                    position: 'left'
                }
              ]
            }
        };

        this.setupChartByAcademicYear();
        this.setupPredictionChart();

    }

    setupChartByAcademicYear() {
        this.labelsAY = ["2014", "2015", "2016", "2017"];        
        this.dataAY = [
            0, 0, 0, 0
        ];
    }

    setupPredictionChart() {
        this.labelsPred = ["2018"];        
        this.dataPred = [
            0
        ];
    }

    loadData() {
        this.loadEnrollmentByAY();
    }

    loadEnrollmentByAY(numYears = 4) {
        var tmp = 0;
        for (var i = 0; i < numYears; i++) { 
            tmp = this.currentAcademicYear - numYears + 1 + i;
            this.enrollmentForYear(i, tmp);
        }   
    }

    enrollmentForYear(idx, year) {
        this.studentService.enrollmentByAcademicYear(year).then((response) => {
            this.dataAY[idx] = response.data;             
        }, (error) => {
            console.log("Greska: " + error);
        });
    }


}


export default HomeController;
