class StudentsController {
    static $inject = ['studentService'];

 constructor(studentService) {
        this.studentService = studentService;
        this.currentAcademicYear = 2017;
        this.setupEnrollmentChart();
        this.loadData();
    }

    setupEnrollmentChart() {
        this.labels = ["Liberal Arts", "Human P.", "Finance", "Business M.", "Computer Science"];
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
            35, 20, 17, 16, 13
        ];
        this.data2 = [
            20, 25, 17, 15, 13
        ];
        this.data3 = [
            14, 20, 30, 15, 13
        ];
        this.data4 = [
            35, 20, 17, 15, 34
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

}

export default StudentsController;