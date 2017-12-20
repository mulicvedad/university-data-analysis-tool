class DataImportController {
    static $inject = ["importDataService", "swalService"];

    constructor(importDataService, swalService) {
        this.importDataService = importDataService;
        this.swalService = swalService;
        $("#spinner-div").hide();
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
}

export default DataImportController;
