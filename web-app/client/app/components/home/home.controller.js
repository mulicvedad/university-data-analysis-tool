class HomeController {
    static $inject = ["studentService"];

    constructor(studentService) {
        this.studentService = studentService;
        this.setupEnrollmentChart();
        //this.loadData();
    }

    setupEnrollmentChart() {
        this.labels = ["2013/14", "2014/15", "2015/16", "2016/17", "2017/18"];
        this.series = ['Redovni', 'Samofinansirajuci'];
        this.data = [
            [100, 150, 120, 95, 150 ],
            [360, 381, 380, 453, 462]
        ];
        
    }

    loadData() {
        this.loadEnrollmentData();
    }

    loadEnrollmentData(numYears = 5) {
        this.studentService.report(5).then((response) => {
            console.log("SUCCES");                
            this.data = response.data;
        }, (error) => {
            console.log("Greska:" + error);
        });
    }

}


export default HomeController;
