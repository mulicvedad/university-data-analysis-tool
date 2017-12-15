package ba.unsa.etf.bp.udat.controllers;

import ba.unsa.etf.bp.udat.services.EnrollmentReportService;
import org.apache.commons.io.IOUtils;
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

    @ResponseBody
    @GetMapping("/report/test")
    public ResponseEntity<byte[]> pdfEnrollmentReport() {
        FileInputStream fileStream;
        try {
            String filePath = enrollmentReportService.generateReport();
            fileStream = new FileInputStream(new File(filePath));
            byte[] contents = IOUtils.toByteArray(fileStream);

            fileStream.close();
            enrollmentReportService.deleteReportFile(filePath);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/pdf"));

            ResponseEntity<byte[]> response = new ResponseEntity<>(contents, headers, HttpStatus.OK);

            return response;
        }
        catch (FileNotFoundException e) {
            return error(e);
        }
        catch (IOException e) {
            return error(e);
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