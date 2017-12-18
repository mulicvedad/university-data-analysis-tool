class CoursesController {
    static $inject = ['studentService',  'departmentService', 'attendanceService', 'reportService'];

 constructor(studentService, departmentService, attendanceService, reportService) {
        this.departmentService = departmentService;
        this.studentService = studentService;
        this.attendanceService = attendanceService;
        this.reportService = reportService;
        this.currentAcademicYear = 2017;
        this.selectedDepartment = null;
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
        this.setupAttendanceChart();
        this.setupCourseHighestAttendance();
    }

    setupCourseHighestAttendance(){
        this.dataattendance = [ 100, 30 , 54, 60, 29 ];
        this.labelsattendance = ["Željko Jurić", "Huse Fatkić", "Narcis Behlilović", "Hasna Šamić", "Haris Šupić"];   
    }

    setupAttendanceChart() {
        this.attendanceLabels = ["Prisutni", "Odsutni"];
        this.attendanceData = [0.5, 0.5];
    }

    setupDropdowns(){
        //zatrebace soon
        /*Promise.all([this.loadLocationTypes(), this.loadAllLocations()]).then(([locTypesRes, locRes]) => {
            this.locationTypes = locTypesRes.data;
            this.allLocations = locRes.data;

            this.loadLocations(page);
        });*/
        this.departments = [];
        this.loadDepartments();

    }
    
    loadDepartments(page = 1) {
        this.departmentService.departments(page).then((response) => {
            this.departments = response.data.content;    
        });
    }

    loadData() {
    }

    selectedChanged() {
        this.attendanceService.attendancePercentage(this.selectedDepartment.id, -1, -1).then((response) => {
            this.attendanceData[0] = response.data;
            this.attendanceData[1] = 1 - response.data;
        });
    }

    attendanceReport() {
        this.reportService.attendanceOverallReport();
    }


}

export default CoursesController;