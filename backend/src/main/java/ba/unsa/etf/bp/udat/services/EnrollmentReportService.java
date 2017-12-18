package ba.unsa.etf.bp.udat.services;

import ba.unsa.etf.bp.udat.models.AcademicYearDim;
import ba.unsa.etf.bp.udat.models.CourseDim;
import ba.unsa.etf.bp.udat.models.DepartmentDim;
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
import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.Arrays;


import java.util.List;
import java.util.logging.Logger;

@Service
public class EnrollmentReportService extends BaseReportService{
    private final String REPORT_FILENAME = "enrollment_report";
    private final String FILE_EXTENSION = ".pdf";
    private final String DOCUMENT_TITLE = "Izvestaj o broju upisanih studenata";
    private final int ACADEMIC_YEAR = 2017;

    private static Logger logger = Logger.getLogger(EnrollmentReportService.class.getName());

    @Autowired
    private EnrollmentFactService enrollmentFactService;

    @Override
    public String generateOverallReport() throws Exception{
        try {
            String reportFilename = REPORT_FILENAME + Math.random() + FILE_EXTENSION;

            OutputStream file = new FileOutputStream(new File(reportFilename));
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, file);
            document.open();
            super.setBaseProperties(REPORT_FILENAME, FILE_EXTENSION, "UDAT", DOCUMENT_TITLE);
            super.initializeDocument(document);
            super.addGeneralInfo(document, DOCUMENT_TITLE);
            //addEnrollmentByAcademicYearTable(document, 5);
            addAcademicYearTable(document);
            super.addEmptyRow(document);
            addDepartmentTable(document);
            super.addEmptyRow(document);
            addBudgetTable(document);
            super.addEmptyRow(document);
            addRepeatingTable(document);

            document.close();
            file.close();

            return reportFilename;
        }
        catch (Exception e) {
            throw new Exception("Došlo je do greške prilikom kreiranja PDF dokumenta.");
        }
    }

    public String generateReport(int academicYearId) {
        return null;
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

    private void addAcademicYearTable(Document document) throws DocumentException{
        PdfPTable pdfTable = new PdfPTable(2);
        super.setTableHeaders(pdfTable, new ArrayList<String>(Arrays.asList("Godina", "Broj upisanih studenata")));
        List<Object[]> rows = enrollmentFactService.groupByAcademicYear();
        for(Object[] row : rows) {
            AcademicYearDim ayd = (AcademicYearDim)row[0];
            Long enrolledCount = (Long)row[1];

            ArrayList<String> values = new ArrayList<>(Arrays.asList(Integer.toString(ayd.getStartYear()), Long.toString(enrolledCount)));
            super.addRowToTable(pdfTable, values);
        }
        document.add(pdfTable);
    }

    private void addDepartmentTable(Document document) throws DocumentException {
        PdfPTable pdfTable = new PdfPTable(2);
        super.setTableHeaders(pdfTable, new ArrayList<String>(Arrays.asList("Odsjek", "Broj upisanih studenata")));
        List<Object[]> rows = enrollmentFactService.groupByDepartments();
        for(Object[] row : rows) {
            DepartmentDim dd = (DepartmentDim)row[0];
            Long enrolledCount = (Long)row[1];

            ArrayList<String> values = new ArrayList<>(Arrays.asList(dd.getTitle(), Long.toString(enrolledCount)));
            super.addRowToTable(pdfTable, values);
        }
        document.add(pdfTable);
    }

    private void addBudgetTable(Document document) throws DocumentException {
        PdfPTable pdfTable = new PdfPTable(2);
        super.setTableHeaders(pdfTable, new ArrayList<String>(Arrays.asList("Tip finansiranja", "Broj upisanih studenata")));
        Integer onBudget = enrollmentFactService.filterByBudget(true);
        Integer notOnBudget = enrollmentFactService.filterByBudget(false);
        super.addRowToTable(pdfTable, new ArrayList<String>(Arrays.asList("Redovni", Integer.toString(onBudget))));
        super.addRowToTable(pdfTable, new ArrayList<String>(Arrays.asList("Samofinansirajuci", Integer.toString(notOnBudget))));
        document.add(pdfTable);
    }

    private void addRepeatingTable(Document document) throws DocumentException, ServerException {
        PdfPTable pdfTable = new PdfPTable(2);
        super.setTableHeaders(pdfTable, new ArrayList<String>(Arrays.asList("Tip upisa", "Broj upisanih studenata")));
        Integer repeating = enrollmentFactService.filterByRepeating(true);
        Integer notRepeating = enrollmentFactService.filterByRepeating(false);
        super.addRowToTable(pdfTable, new ArrayList<String>(Arrays.asList("Redovni", Integer.toString(repeating))));
        super.addRowToTable(pdfTable, new ArrayList<String>(Arrays.asList("Ponovci", Integer.toString(notRepeating))));
        document.add(pdfTable);
    }

    private Double roundToTwoDecimalPlaces(Double num) {
        Double newNumber = Math.round(num * 100) / 100.0;
        return newNumber;
    }

}
