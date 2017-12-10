package ba.unsa.etf.bp.udat.controllers;
import ba.unsa.etf.bp.udat.RemoteDb;
import ba.unsa.etf.bp.udat.models.CourseDim;
import ba.unsa.etf.bp.udat.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

@Controller
@EnableAutoConfiguration


public class ImportDataController {

    @Autowired
    SemesterDimService semesterDimService;
    @Autowired
    DepartmentDimService departmentDimService;
    @Autowired
    CourseDimService courseDimService;
    @Autowired
    AcademicYearDimService academicYearDimService;
    @Autowired
    TimeDimService timeDimService;
    @Autowired
    LecturerDimService lecturerDimService;

    @RequestMapping("/import")
    @ResponseBody
    public String populateDatabase() throws SQLException {

        RemoteDb etf = new RemoteDb();
        etf.connect();
        etf.setSchema();
        // Populate dimensions first
        etf.populateSemesterDim("SELECT id, title FROM  semester ORDER BY id", semesterDimService);
        etf.populateDepartmentDim("SELECT id, title FROM department", departmentDimService);
        etf.populateCourseDim("SELECT id, title FROM course ORDER BY id", courseDimService);

        etf.populateAcademicYearDim("SELECT id, title, active, EXTRACT(YEAR FROM \"start\") AS start_year" +
                                            " FROM academicyear" +
                                            " ORDER BY start_year", academicYearDimService);

        etf.populateTimeDim("SELECT scheduled AS full_date, extract(YEAR FROM scheduled) AS year, extract(MONTH FROM scheduled) AS month," +
                                    " extract(DAY FROM scheduled) AS day, extract(HOUR FROM scheduled) as hour," +
                                    " to_char(scheduled, 'MONTH') AS month_word, to_char(scheduled, 'DAY') AS day_word" +
                                    " FROM exam" +
                                    " UNION ALL" +
                                    " SELECT scheduled AS \"date\", extract(YEAR FROM scheduled) AS year, extract(MONTH FROM scheduled) AS month," +
                                    " extract(DAY FROM scheduled) AS day, extract(HOUR FROM scheduled) as hour," +
                                    " to_char(scheduled, 'MONTH') AS month_word, to_char(scheduled, 'DAY') AS day_word" +
                                    " FROM class" +
                                    " ORDER BY full_date DESC",timeDimService);

        etf.populateLecturerDim("SELECT zud.userid AS id, ud.firstname AS first_name, ud.lastname AS last_name," +
                                    "   Decode(" +
                                    "   (SELECT Count(*) FROM zamgeruserdetails zud1, studentdetails sd" +
                                    "   WHERE zud1.userid = sd.zamgeruserdetailsuserid AND zud1.userid = zud.userid)," +
                                    "   0, 0, 1" +
                                    "    )" +
                                    "    AS \"is_student\"," +
                                    "    ed.salary AS \"salary\", zud.gender AS \"gender\"" +
                                    " FROM zamgeruserdetails zud, users u, userdetails ud, employeedetails ed" +
                                    " WHERE zud.userid = u.id AND ud.userid = u.id AND ed.zamgeruserdetailsuserid = zud.userid" +
                                    " ORDER BY zud.userid", lecturerDimService);
        // Populate facts
        return "Helo";
    }
}