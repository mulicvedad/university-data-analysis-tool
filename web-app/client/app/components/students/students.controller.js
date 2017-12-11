class StudentsController {
    static $inject = ['studentService'];

 constructor(studentService) {
        this.studentService = studentService;
        this.currentAcademicYear = 2017;
        this.setupEnrollmentCharts();
        //this.loadData();
    }

    setupEnrollmentCharts() {
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
        this.setupEnrollmentByAcademicYear();
        this.setupEnrollmentDepartment();
        this.setupEnrollmentByBudget();
        this.setupEnrollmentByRepeating();
    }

    setupEnrollmentByAcademicYear() {
        this.labelsAY = ["2014", "2015", "2016", "2017"];
        this.seriesAY = ['Series'];
        
        this.data = [
            35, 20, 17, 16
        ];
    }

    setupEnrollmentDepartment() {
        this.labelsDep = ["RI", "AiE", "EE", "TK"];
        this.seriesDep = ['Series'];
    
        this.data2 = [
            35, 20, 17, 15
        ];
    }

    setupEnrollmentByBudget() {
        this.labelsBudget = ["Redovni", "Samofinansirajuci"];
        this.seriesBudget = ['Series'];
        
        this.data3 = [
            20, 25
        ];
    }

    setupEnrollmentByRepeating() {
        this.labelsRepeating = ["Redovni", "Ponovci"];
        this.seriesRepeating = ['Series'];
        
        this.data4 = [
            14, 20
        ];
    }

    loadData() {
        this.loadEnrollmentByAY();
        this.loadEnrollmentByDepartment();
        this.loadEnrollmentByBudget();
        this.loadEnrollmentByRepeating();
    }

    loadEnrollmentByAY(numYears = 4) {
        var tmp = 0;
        for (var i = 0; i < numYears; i++) { 
            tmp = this.currentAcademicYear - numYears + 1 + i;
            this.enrollmentForYear(i, tmp);
        }   
    }

    loadEnrollmentByDepartment(numDeps = 4) {
        for (var i = 1; i <= numDeps; i++) { 
            this.studentService.enrollmentByDepartment(i + 8).then((response) => {
                console.log("vrijednost od i: " + i);
                this.data2[i - 1] = response.data;
            }, (error) => {
                console.log("Greska: " + error);
            });
        }   
    }

    loadEnrollmentByBudget() {
        this.studentService.enrollmentByBudget(1).then((response) => {
            this.data3[0] = response.data;
        }, (error) => {
            console.log("Greska: " + error);
        });
        this.studentService.enrollmentByBudget(0).then((response) => {
            this.data3[1] = response.data;
        }, (error) => {
            console.log("Greska: " + error);
        });
    }
    
    loadEnrollmentByRepeating() {
        this.studentService.enrollmentByRepeating(0).then((response) => {
            this.data4[0] = response.data;
        }, (error) => {
            console.log("Greska: " + error);
        });
        this.studentService.enrollmentByRepeating(0).then((response) => {
            this.data4[1] = response.data;
        }, (error) => {
            console.log("Greska: " + error);
        });
    }

    enrollmentForYear(idx, year) {
        this.studentService.enrollmentByAcademicYear(year).then((response) => {
            this.data[idx] = response.data; 
        }, (error) => {
            console.log("Greska: " + error);
        });
    }

}

export default StudentsController;