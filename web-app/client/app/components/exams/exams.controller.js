class ExamsController {
    static $inject = ['studentService'];

 constructor(studentService) {
        this.studentService = studentService;
        this.currentAcademicYear = 2017;
        this.setupEnrollmentCharts();
        this.setupDropdowns();
        this.loadData();
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
        this.setupExamParticipationByAcademicYear();
        this.setupEnrollmentDepartment();
        this.setupEnrollmentByBudget();
        this.setupEnrollmentByRepeating();
    }

    setupExamParticipationByAcademicYear() {
        this.labels1 = ["2014", "2015", "2016", "2017"];
        this.series1 = ['Series'];
        this.data1 = [
            35, 20, 17, 16
        ];
    }

    setupEnrollmentDepartment() {
        this.labels2 = ["RI", "AiE", "EE", "TK"];
        this.series2 = ['Series'];
    
        this.data2 = [
            40, 45, 17, 15

        ];
    }

    setupDropdowns(){
        this.academy_years = [
        {
            name: '2017',
            value: '2017'
        },
        {
          name: '2016',
            value: '2016'
        },{
          name: '2015',
            value: '2015'
        },{
          name: '2014',
            value: '2014'
        }
        ];
        this.subjects = [
        {
          name: 'Baze Podataka',
            value: 'Baze Podataka'
        },
        {
          name: 'Paralelni Racunarski Sistemi',
            value: 'Paralelni Racunarski Sistemi'
        },{
          name: 'Optimizacija Resursa',
            value: 'Optimizacija Resursa'
        },
        {
          name: 'Multimedijalni Sistemi',
            value: 'Multimedijalni Sistemi'
        },
        {
          name: 'DRAOS',
            value: 'DRAOS'
        }
        ];
        this.departments = [{
          name: 'RI',
            value: 'RI'
        },{
          name: 'AiE',
            value: 'AiE'
        },
        {
          name: 'EE',
            value: 'EE'
        },
        {
            name: 'TK',
            value: 'TK'
        }
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
        for (let i = 1; i <= numDeps; i++) { 
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
        this.studentService.enrollmentByRepeating(1).then((response) => {
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

export default ExamsController;