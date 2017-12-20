class ExamsController {
    static $inject = ['studentService', 'departmentService', 'academicYearService', 'examService', 'reportService'];

 constructor(studentService, departmentService, academicYearService, examService, reportService) {
        this.studentService = studentService;
        this.departmentService = departmentService;
        this.academicYearService = academicYearService;
        this.examService = examService;
        this.reportService = reportService;
        this.currentAcademicYear = 2017;
        this.title = "Podaci o ispitima";
        this.setupEnrollmentCharts();
        this.setupDropdowns();
        this.loadData();
    }

    setupEnrollmentCharts() {
        this.options = {
            scales: {
              yAxes: [
                {
                    ticks: {
                        beginAtZero:true
                    },
                    id: 'y-axis-1',
                    type: 'linear',
                    display: true,
                    position: 'left'
                }
              ]
            }
        };
        this.setupTurnoutChart();
        this.setupAveragePointsChart();
    }

    setupTurnoutChart() {
        this.labels1 = ["Ukupna izlaznost"];
        this.series1 = ['Series'];
        this.data1 = [ 50 ];
    }

    setupAveragePointsChart() {
        this.labels2 = ["Prosjek bodova"];
        this.series2 = ['Series'];
        this.data2 = [ 15.5 ];
    }

    setupDropdowns(){
        this.academicYears = [];
        this.departments = [];
        this.loadDepartments();
        this.loadAcademicYears();
    }

    loadData() {
        this.selectedDepartment1 = {id: -1};
        this.selectedDepartment2 = {id: -1};
        this.selectedYear1 = {id: -1};
        this.selectedYear2 = {id: -1};
    }

    loadDepartments(page = 1) {
        this.departmentService.departments(page).then((response) => {
            this.departments = response.data.content;    
        });
    }

    loadAcademicYears(page = 1) {
        this.academicYearService.academicYears(page).then((response) => {
            this.academicYears = response.data.content;    
        });
    }

    refreshTurnout() {
        console.log("change 1");
        console.log("year: " + this.selectedYear1.id);
        console.log("dep: " + this.selectedDepartment1.id)
        this.examService.turnout(this.selectedYear1.id, this.selectedDepartment1.id).then((response) => {
            this.data1[0] = response.data;
            console.log("data: " + response.data);
        });
    }

    refreshAveragePoints() {
        console.log("change 2");
        console.log("year: " + this.selectedYear2.id);
        console.log("dep: " + this.selectedDepartment2.id)
        this.examService.averagePoints(this.selectedYear2.id, this.selectedDepartment2.id).then((response) => {
            
            this.data2[0] = response.data;
            console.log("data: " + response.data);
        });
    }

    examsReport() {
        this.reportService.examOverallReport();
    }

}

export default ExamsController;