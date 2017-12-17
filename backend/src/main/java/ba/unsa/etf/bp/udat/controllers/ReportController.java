package ba.unsa.etf.bp.udat.controllers;

import ba.unsa.etf.bp.udat.services.BaseReportService;
import ba.unsa.etf.bp.udat.services.EnrollmentReportService;
import ba.unsa.etf.bp.udat.services.ExamReportService;
import org.apache.commons.io.IOUtils;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;
@RestController
public class ReportController {

    @Autowired
    private EnrollmentReportService enrollmentReportService;
    @Autowired
    private ExamReportService examReportService;

    //EXAMS
    @ResponseBody
    @GetMapping("/report/exam/overallReport")
    public ResponseEntity<byte[]> examOverallReport() {
        try {
            String filepath = examReportService.generateOverallReport();
            return createResponse(filepath);
        }
        catch (Exception e) {
            return error(e);
        }
    }

    @ResponseBody
    @GetMapping("/report/exam/turnout")
    public ResponseEntity<byte[]> examTurnoutReport(@RequestParam("ay") Long ay, @RequestParam("dep") Long dep,
                                             @RequestParam("course") Long course) {
        try {
            String filepath = examReportService.generateTurnoutReport(ay, dep, course);
            return createResponse(filepath);
        }
        catch (Exception e) {
            return error(e);
        }
    }

    @ResponseBody
    @GetMapping("/report/exam/points")
    public ResponseEntity<byte[]> examPointsReport(@RequestParam("ay") Long ay, @RequestParam("dep") Long dep,
                                                    @RequestParam("course") Long course) {
        try {
            String filepath = examReportService.generatePointsReport(ay, dep, course);
            return createResponse(filepath);
        }
        catch (Exception e) {
            return error(e);
        }
    }

    private ResponseEntity<byte[]> createResponse(String filepath) {
        FileInputStream fileStream;
        try {
            fileStream = new FileInputStream(new File(filepath));
            byte[] contents = IOUtils.toByteArray(fileStream);

            fileStream.close();
            BaseReportService.deleteReportFile(filepath);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/pdf"));

            ResponseEntity<byte[]> response = new ResponseEntity<>(contents, headers, HttpStatus.OK);

            return response;
        }
        catch (Exception e) {
            return error(e);
        }
    }

    @ResponseBody
    private ResponseEntity error(Exception e) {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder()
                .add("message", e.getMessage())
                .add("error", "Error occured");
        JsonObject responseObj = objectBuilder.build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObj);
    }
}