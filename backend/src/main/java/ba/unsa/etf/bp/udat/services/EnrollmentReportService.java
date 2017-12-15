package ba.unsa.etf.bp.udat.services;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;


import java.util.logging.Logger;

@Service
public class EnrollmentReportService extends BaseReportService{
    private final String ENROLLMENT_REPORT_FILENAME = "enrollment";
    private final String FILE_EXTENSION = ".pdf";
    private final String DOCUMENT_TITLE = "Enrollment Report";
    private final int ACADEMIC_YEAR = 2017;

    private static Logger logger = Logger.getLogger(EnrollmentReportService.class.getName());

    @Autowired
    private EnrollmentFactService enrollmentFactService;

    @Override
    public String generateReport() throws Exception{
        try {
            logger.entering("EnrollmentFactSErvice", "generateReport");
            String auditReportFilename = ENROLLMENT_REPORT_FILENAME + Math.random() + FILE_EXTENSION;

            OutputStream file = new FileOutputStream(new File(auditReportFilename));
            Document document = new Document(PageSize.A3.rotate());
            logger.info("Kreiran file i document");
            PdfWriter.getInstance(document, file);
            document.open();
            super.setBaseProperties(ENROLLMENT_REPORT_FILENAME, FILE_EXTENSION, "UDAT", DOCUMENT_TITLE);
            super.initializeDocument(document);
            super.addGeneralInfo(document, DOCUMENT_TITLE);
            addEnrollmentByAcademicYearTable(document, 5);
            //addEnrollmentByDepartmentTable();
            document.close();
            file.close();

            return auditReportFilename;
        }
        catch (Exception e) {
            throw new Exception("Došlo je do greške prilikom kreiranja PDF dokumenta.");
        }
    }

    private void addEnrollmentByAcademicYearTable(Document document, int numberOfYears) throws DocumentException{
        PdfPTable pdfTable = new PdfPTable(2);
        super.setTableHeaders(pdfTable, new ArrayList<String>(Arrays.asList("Godina", "Broj upisanih")));

        for(int i = 0; i < numberOfYears; i++) {
            String year = Integer.toString(ACADEMIC_YEAR - i);
            String enrolledCount = Integer.toString(this.enrollmentFactService.filterByAcademicYear(ACADEMIC_YEAR - i));
            ArrayList<String> values = new ArrayList<>(Arrays.asList(year, enrolledCount));
            super.addRowToTable(pdfTable, values);
        }

        document.add(pdfTable);

    }

    private void addEnrollmentByDepartmentTable() {

    }

}
