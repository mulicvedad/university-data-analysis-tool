package ba.unsa.etf.bp.udat.services;

import ba.unsa.etf.bp.udat.models.CourseDim;
import ba.unsa.etf.bp.udat.models.DepartmentDim;
import ba.unsa.etf.bp.udat.models.LecturerDim;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AttendanceReportService extends BaseReportService {
    private final String REPORT_FILENAME = "attendance_report";
    private final String FILE_EXTENSION = ".pdf";
    private final String DOCUMENT_TITLE = "Izvjestaj o nastavi";
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

            this.<DepartmentDim>addTable(document, new ArrayList<String>(Arrays.asList("Odsjek", "Prosjecan broj prisutnih", "Procenat prisutnih")),
                attendanceFactService.groupByDepartments());
            super.addEmptyRow(document);
            this.<CourseDim>addTable(document, new ArrayList<String>(Arrays.asList("Predmet", "Prosjecan broj prisutnih", "Procenat prisutnih")),
                    attendanceFactService.groupByCourses());
            super.addEmptyRow(document);
            this.<LecturerDim>addTable(document, new ArrayList<String>(Arrays.asList("Nastavnik", "Prosjecan broj prisutnih", "Procenat prisutnih")),
                    attendanceFactService.groupByLecturers());

            document.close();
            file.close();

            return reportFilename;
        }
        catch (Exception e) {
            throw new ServiceException("Došlo je do greške prilikom kreiranja PDF dokumenta.");
        }
    }

    //malo generickih funkcija hehe nije se dzabe iz TPa 6 upisalo
    private <T> void addTable(Document document, List<String> headers, List<Object[]> rows) throws DocumentException{
        PdfPTable pdfTable = new PdfPTable(headers.size());
        super.setTableHeaders(pdfTable, headers);
        for (Object[] row : rows) {
            T entity = (T)row[0];
            Double attendanceNumber = roundToTwoDecimalPlaces((Double) row[1]);
            Double attendancePercentage = roundToTwoDecimalPlaces((Double) row[2]);

            ArrayList<String> values = new ArrayList<>(Arrays.asList(entity.toString(),
                    Double.toString(attendanceNumber),
                    Double.toString(attendancePercentage)));
            super.addRowToTable(pdfTable, values);
        }
        document.add(pdfTable);
    }

    private Double roundToTwoDecimalPlaces(Double num) {
        Double newNumber = Math.round(num * 100) / 100.0;
        return newNumber;
    }
}
