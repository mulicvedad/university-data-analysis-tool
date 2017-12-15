package ba.unsa.etf.bp.udat.services;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BaseReportService {

    private String REPORT_FILENAME;
    private String REPORT_PDF_EXTENSION;
    private String DOCUMENT_CREATOR;
    private String DOCUMENT_TITLE;

    @Autowired
    private AcademicYearDimService academicYearDimService;
    @Autowired
    private CourseDimService courseDimService;
    @Autowired
    private DepartmentDimService departmentDimService;
    @Autowired
    private LecturerDimService lecturerDimService;
    @Autowired
    private SemesterDimService semesterDimService;
    @Autowired
    private TimeDimService timeDimService;


    public String getAUDIT_REPORT_FILENAME() {
        return REPORT_FILENAME;
    }

    public void setAUDIT_REPORT_FILENAME(String AUDIT_REPORT_FILENAME) {
        this.REPORT_FILENAME = AUDIT_REPORT_FILENAME;
    }

    public String getREPORT_PDF_EXTENSION() {
        return REPORT_PDF_EXTENSION;
    }

    public void setREPORT_PDF_EXTENSION(String REPORT_PDF_EXTENSION) {
        this.REPORT_PDF_EXTENSION = REPORT_PDF_EXTENSION;
    }

    public String getDOCUMENT_CREATOR() {
        return DOCUMENT_CREATOR;
    }

    public void setDOCUMENT_CREATOR(String DOCUMENT_CREATOR) {
        this.DOCUMENT_CREATOR = DOCUMENT_CREATOR;
    }

    public String getAUDIT_DOCUMENT_TITLE() {
        return DOCUMENT_TITLE;
    }

    public void setAUDIT_DOCUMENT_TITLE(String AUDIT_DOCUMENT_TITLE) {
        this.DOCUMENT_TITLE = AUDIT_DOCUMENT_TITLE;
    }

    public void setBaseProperties(String filename, String extension, String documentCreator, String documentTitle) {
        this.REPORT_FILENAME = filename;
        this.REPORT_PDF_EXTENSION = extension;
        this.DOCUMENT_CREATOR = documentCreator;
        this.DOCUMENT_TITLE = documentTitle;
    }

    protected void initializeDocument (Document document) throws DocumentException {
        document.addTitle(DOCUMENT_TITLE);
        document.addCreationDate();
        document.addCreator(DOCUMENT_CREATOR);
    }

    protected void addGeneralInfo(Document document, String title) throws DocumentException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY HH:mm");
        Date now = new Date();
        String dateString = dateFormat.format(now);

        Paragraph paragraph = new Paragraph(dateString);
        paragraph.add(new Paragraph(title,
                new Font(Font.FontFamily.HELVETICA, 18,
                        Font.BOLD)));

        paragraph.add(new Paragraph(" "));

        document.add(paragraph);
    }

    protected void setTableHeaders(PdfPTable table, List<String> headers) {
        for(String h : headers) {
            PdfPCell cell = new PdfPCell(new Phrase(h));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(cell);
        }

        table.setHeaderRows(1);
    }

    protected void addRowToTable(PdfPTable table, List<String> values) {
        for(String v : values) {
            PdfPCell cell = new PdfPCell(new Phrase(v));
            table.addCell(cell);
        }
    }

    // asinhrono brisanje datoteke nakon sto obradjen zahtjev
    public void deleteReportFile(String filepath) {
        File file = new File(filepath);
        if (file != null) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    file.delete();
                }
            }).start();
        }
    }

    public String generateReport() throws Exception{ return null; }
}

