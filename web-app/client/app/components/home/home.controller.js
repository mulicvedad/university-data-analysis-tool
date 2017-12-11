class HomeController {
    static $inject = ["studentService", "importDataService"];

    constructor(studentService, importDataService) {
        this.studentService = studentService;
        this.importDataService = importDataService;
        this.currentAcademicYear = 2017;
        this.setupEnrollmentChart();
        //this.loadData();
    }

    setupEnrollmentChart() {
        this.labels = ["2013/14", "2014/15", "2015/16", "2016/17", "2017/18"];
        this.series = ['Redovni'];
        this.options = {
            scales: {
              yAxes: [
                {
                  id: 'y-axis-1',
                  type: 'linear',
                  display: true,
                  position: 'left'
                }
              ]
            }
        };
        this.data = [
            100, 150, 120, 95, 150 
        ];
        this.data2 = [
            100, 150, 120, 95, 150 
        ];
        
    }

    loadData() {
        this.loadEnrollmentData();
    }

    loadEnrollmentData(numYears = 5) {
        var tmp = 0;
        for (var i = 0; i < numYears; i++) { 
            tmp = this.currentAcademicYear - numYears + 1 + i;
            this.enrollmentForYear(i, tmp);
            this.registeredStudentsForYear(i,tmp);
        }   
    }
    
    enrollmentForYear(idx, year) {
        this.studentService.enrollmentReport(year).then((response) => {
            this.data[idx] = response.data; 
        }, (error) => {
            console.log("Greska: " + error);
        });
    }
    registeredStudentsForYear(idx, year)
    {
        this.studentService.registeredStudentsReport(year).then((response) => {
            this.data2[idx] = response.data;     
        }, (error) => {
            console.log("Greska: " + error);
        });
    }

    importData() {
        this.importDataService.importData();
    }
}


export default HomeController;
