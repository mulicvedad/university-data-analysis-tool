class CoursesController {
    static $inject = ['studentService',  'departmentService', 'attendanceService', 'reportService'];

 constructor(studentService, departmentService, attendanceService, reportService) {
        this.departmentService = departmentService;
        this.studentService = studentService;
        this.attendanceService = attendanceService;
        this.reportService = reportService;
        this.currentAcademicYear = 2017;
        this.selectedDepartment = null;
        this.title = "Podaci o nastavi";
        this.setupCoursesCharts();
        this.setupDropdowns();
        this.loadData();
    }

    setupCoursesCharts() {
        this.options = {
            scales: {
              yAxes: [
                {
                    ticks: {
                        beginAtZero:true,
                        max: 100
                    },
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
        this.maxAttendanceData = [ 100, 30 , 54, 60 ];
        this.maxAttendanceLabels = ["Huse Fatkić", "Narcis Behlilović", "Hasna Šamić", "Haris Šupić"];   
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
        this.loadMaxAttendanceByLecturers();
    }

    loadMaxAttendanceByLecturers() {
        this.attendanceService.maxAttendanceByLecturers().then((response) => {
            //this.maxAttendanceData = response.data;
            let i = 0;
            for(i=0; i<4; i++) {
                this.maxAttendanceData[i] = response.data[i][1];
                this.maxAttendanceLabels[i] = response.data[i][0].firstName + " " + response.data[i][0].lastName;
            }
        }).catch((error) => {
            console.log("Error in 'loadMaxAttendanceByLecturers()':\n " + error);
        })
    }
    selectedChanged() {
        this.attendanceService.attendancePercentage(this.selectedDepartment.id, -1, -1).then((response) => {
            this.attendanceData[0] = response.data;
            this.attendanceData[1] = 100 - response.data;
        });
    }

    attendanceReport() {
        this.reportService.attendanceOverallReport();
    }


}

export default CoursesController;