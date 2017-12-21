class DataImportController {
    static $inject = ["importDataService", "swalService"];

    constructor(importDataService, swalService) {
        this.importDataService = importDataService;
        this.swalService = swalService;
        this.allTablesInfo = [{tableName: "Departments", dateDW: "22-12-2017", dateRDB: "21-12-2017", isUpToDate: true}, 
            {tableName: "Courses", dateDW: "22-12-2017", dateRDB: "21-12-2017", isUpToDate: true},
            {tableName: "Teachers", dateDW: "22-12-2017", dateRDB: "21-12-2017", isUpToDate: true}  ];
        $("#spinner-div").hide();
        this.loadData();
    }

    importData() {
        $("#spinner-div").show();        
        this.importDataService.importData().then((response) => {
            $("#spinner-div").hide();                        
            this.swalService.success("Podaci su uspjesno ucitani.");
        }, (error) => {
            $("#spinner-div").hide();            
            this.swalService.displayError("Doslo je do greske prilikom ucitavanja podataka: \n" + error.message);
        });
    }

    loadData() {
        this.loadTablesInfo();
    }

    loadTablesInfo() {
        this.importDataService.allTablesInfo().then((response) => {
            this.allTablesInfo = response.data;
        }).catch((error) => {
            console.log("Error in 'loadTablesInfo': " + error);
        })
    }
}

export default DataImportController;
