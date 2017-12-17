class CoursesController {
    static $inject = ['studentService'];

 constructor(studentService) {
        this.studentService = studentService;
        this.currentAcademicYear = 2017;
        this.setupCoursesCharts();
        this.setupDropdowns();
        this.loadData();
    }

    setupCoursesCharts() {
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
        this.setupCourseHighestAttendance();
        this.setupEnrollmentDepartment();
        this.setupEnrollmentByBudget();
        this.setupEnrollmentByRepeating();
    }

    setupCourseHighestAttendance(){
        this.dataattendance = [ 100, 30 , 54, 60, 29 ];
        this.labelsattendance = ["Željko Jurić", "Huse Fatkić", "Narcis Behlilović", "Hasna Šamić", "Haris Šupić"];   
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
        this.semesters = [
        {
            name: 'I',
            value: 'I'
        },
        {
          name: 'II',
            value: 'II'
        },{
          name: 'III',
            value: 'III'
        },{
          name: 'IV',
            value: 'IV'
        },
        {
          name: 'V',
            value: 'V'
        },
        {
          name: 'VI',
            value: 'VI'
        }
        ];
        this.days = [
        {
          name: 'Ponedjeljak',
            value: 'Ponedjeljak'
        },
        {
          name: 'Utorak',
            value: 'Utorak'
        },{
          name: 'Srijeda',
            value: 'Srijeda'
        },
        {
          name: 'Cetvrtak',
            value: 'Cetvrtak'
        },
        {
          name: 'Petak',
            value: 'Petak'
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

export default CoursesController;