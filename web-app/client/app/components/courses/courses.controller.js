class CoursesController {
    static $inject = ["studentService"];

    constructor(studentService) {
        this.studentService = studentService;
        this.currentAcademicYear = 2017;
        this.setupEnrollmentChart();
        this.loadData();
    }

    /* changeChartView() {
        this.data = {
        singleSelect: null,
        option1: 'option-1'
        };
        }*/

    setupEnrollmentChart() {
        this.labels = ["2012", "2013", "2014", "2015", "2016", "2017"];
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
         4000, 6000, 2000, 7000, 8000, 10000 
        ];
        this.colors1 = ["#2196f3", "#e3f2fd", "#bbdefb", "#90caf9", "#64b5f6", "#42a5f5"];
        this.data2 = [
            35, 10, 65, 40, 100
        ];
        this.labels2 = ["2017", "2016", "2015", "2014", "2013"];
        this.colors2 =["#2196f3", "#e3f2fd", "#bbdefb", "#90caf9", "#64b5f6", "#42a5f5"];
        this.data3 = [40, 43, 45, 45, 48, 55, 59, 64, 70, 78];
        this.labels3 = [
        "Finance", "Business Management", "English Literature", "Tech Engineering",
         "Human Resource Management", "Computer Science","Civil Engineering", 
         "Electrical Engineering","Performing Arts","Music"];
         this.colors3 = [ "#0000ff", "#0000ff", "#0000ff","#0000ff","#0000ff","#0000ff","#0000ff","#0000ff","#0000ff","#0000ff"];
         this.colors4 = [ "#4169e1", "#4169e1", "#4169e1", "#4169e1", "#4169e1", "#4169e1", "#4169e1", "#4169e1", "#4169e1","#4169e1"];
         this.data4 = [70, 70, 73, 75, 78, 78, 80, 82, 84, 86];
         this.labels4 = [
        "Finance", "Business Management", "English Literature", "Tech Engineering",
         "Human Resource Management", "Computer Science","Civil Engineering", 
         "Electrical Engineering","Performing Arts","Music"];
    }


    loadData() {
        this.loadEnrollmentData();
    }

    loadEnrollmentData(numYears = 6) {
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
    

export default CoursesController;
