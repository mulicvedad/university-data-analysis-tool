package ba.unsa.etf.bp.udat.services;

import ba.unsa.etf.bp.udat.models.AcademicYearDim;
import ba.unsa.etf.bp.udat.models.CourseDim;
import ba.unsa.etf.bp.udat.models.DepartmentDim;
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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import java.lang.Math;

@Service
public class ExamReportService extends BaseReportService {
    private final String REPORT_FILENAME = "exam_report";
    private final String FILE_EXTENSION = ".pdf";
    private final String DOCUMENT_TITLE = "Exam Report";
    private final int ACADEMIC_YEAR = 2017;

    private static Logger logger = Logger.getLogger(EnrollmentReportService.class.getName());

    @Autowired
    private ExamFactService examFactService;

    public String generateOverallReport() throws ServiceException {
        try {
            String reportFilename = REPORT_FILENAME + Math.random() + FILE_EXTENSION;

            OutputStream file = new FileOutputStream(new File(reportFilename));
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, file);
            document.open();

            super.setBaseProperties(REPORT_FILENAME, FILE_EXTENSION, "UDAT", DOCUMENT_TITLE);
            super.initializeDocument(document);
            super.addGeneralInfo(document, DOCUMENT_TITLE);

            addAcademicYearTable(document);
            super.addEmptyRow(document);
            addDepartmentTable(document);
            super.addEmptyRow(document);
            addCourseTable(document);

            document.close();
            file.close();

            return reportFilename;
        }
        catch (Exception e) {
            throw new ServiceException("Došlo je do greške prilikom kreiranja PDF dokumenta.");
        }
    }

    public String generateTurnoutReport(Long ay, Long dep, Long course) {
        return null;
    }

    public String generatePointsReport(Long ay, Long dep, Long course) {
        return null;
    }


    private void addAcademicYearTable(Document document) throws DocumentException{
        PdfPTable pdfTable = new PdfPTable(3);
        super.setTableHeaders(pdfTable, new ArrayList<String>(Arrays.asList("Godina", "Prosjecna izlaznost", "Prosjecan uspjeh")));
        List<Object[]> rows = examFactService.groupByAcademicYear();
        for(Object[] row : rows) {
            AcademicYearDim ayd = (AcademicYearDim)row[0];
            Double turnout = roundToTwoDecimalPlaces((Double)row[1]);
            Double points = roundToTwoDecimalPlaces((Double)row[2]);

            ArrayList<String> values = new ArrayList<>(Arrays.asList(Integer.toString(ayd.getStartYear()), Double.toString(turnout),
                    Double.toString(points)));
            super.addRowToTable(pdfTable, values);
        }
        document.add(pdfTable);
    }

    private void addDepartmentTable(Document document) throws DocumentException {
        PdfPTable pdfTable = new PdfPTable(3);
        super.setTableHeaders(pdfTable, new ArrayList<String>(Arrays.asList("Odsjek", "Prosjecna izlaznost", "Prosjecan uspjeh")));
        List<Object[]> rows = examFactService.groupByDepartments();
        for(Object[] row : rows) {
            DepartmentDim dd = (DepartmentDim)row[0];
            Double turnout = roundToTwoDecimalPlaces((Double)row[1]);
            Double points = roundToTwoDecimalPlaces((Double)row[2]);

            ArrayList<String> values = new ArrayList<>(Arrays.asList(dd.getTitle(), Double.toString(turnout),
                    Double.toString(points)));
            super.addRowToTable(pdfTable, values);
        }
        document.add(pdfTable);
    }

    private void addCourseTable(Document document) throws DocumentException {
        PdfPTable pdfTable = new PdfPTable(3);
        super.setTableHeaders(pdfTable, new ArrayList<String>(Arrays.asList("Predmet", "Prosjecna izlaznost", "Prosjecan uspjeh")));
        List<Object[]> rows = examFactService.groupByCourses();
        for (Object[] row : rows) {
            CourseDim cd = (CourseDim)row[0];
            Double turnout = roundToTwoDecimalPlaces((Double) row[1]);
            Double points = roundToTwoDecimalPlaces((Double) row[2]);

            ArrayList<String> values = new ArrayList<>(Arrays.asList(cd.getTitle(), Double.toString(turnout),
                    Double.toString(points)));
            super.addRowToTable(pdfTable, values);
        }
        document.add(pdfTable);
    }

    private Double roundToTwoDecimalPlaces(Double num) {
        Double newNumber = Math.round(num * 100) / 100.0;
        return newNumber;
    }
}
