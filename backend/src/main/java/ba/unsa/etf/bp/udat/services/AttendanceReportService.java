package ba.unsa.etf.bp.udat.services;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class AttendanceReportService extends BaseReportService {
    private final String REPORT_FILENAME = "attendance_report";
    private final String FILE_EXTENSION = ".pdf";
    private final String DOCUMENT_TITLE = "Izvjestaj o ispitima";
    private final int ACADEMIC_YEAR = 2017;

    @Autowired
    private AttendanceFactService attendanceFactService;

    @Override
    public String generateOverallReport() throws Exception {
        try {
            String reportFilename = REPORT_FILENAME + Math.random() + FILE_EXTENSION;

            OutputStream file = new FileOutputStream(new File(reportFilename));
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, file);
            document.open();

            super.setBaseProperties(REPORT_FILENAME, FILE_EXTENSION, "UDAT", DOCUMENT_TITLE);
            super.initializeDocument(document);
            super.addGeneralInfo(document, DOCUMENT_TITLE);

           // addAcademicYearTable(document);
            super.addEmptyRow(document);
           // addDepartmentTable(document);
            super.addEmptyRow(document);
          //  addCourseTable(document);

            document.close();
            file.close();

            return reportFilename;
        }
        catch (Exception e) {
            throw new ServiceException("Došlo je do greške prilikom kreiranja PDF dokumenta.");
        }
    }
}
