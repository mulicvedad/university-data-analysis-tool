package ba.unsa.etf.bp.udat.controllers;
import ba.unsa.etf.bp.udat.RemoteDb;
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
    @Autowired
    ExamFactService examFactService;
    @Autowired
    EnrollmentFactService enrollmentFactService;
    @Autowired
    AttendanceFactService attendanceFactService;
    @Autowired
    ImportTimeService importTimeService;

    @RequestMapping("/import")
    @ResponseBody
    public Integer populateDatabase() throws SQLException {

        RemoteDb etf = new RemoteDb();
        etf.connect();
        etf.setSchema();
        // Populate dimensions first
        etf.populateSemesterDim("SELECT id, title FROM  semester ORDER BY id", semesterDimService, importTimeService);
        etf.populateDepartmentDim("SELECT id, title FROM department", departmentDimService, importTimeService);
        etf.populateCourseDim("SELECT id, title FROM course ORDER BY id", courseDimService, importTimeService);

        etf.populateAcademicYearDim("SELECT id, title, active, EXTRACT(YEAR FROM \"start\") AS start_year" +
                                            " FROM academicyear" +
                                            " ORDER BY start_year", academicYearDimService, importTimeService);

        etf.populateTimeDim("SELECT scheduled AS full_date, extract(YEAR FROM scheduled) AS year, extract(MONTH FROM scheduled) AS month," +
                                    " extract(DAY FROM scheduled) AS day, extract(HOUR FROM scheduled) as hour," +
                                    " to_char(scheduled, 'MONTH') AS month_word, to_char(scheduled, 'DAY') AS day_word" +
                                    " FROM exam" +
                                    " UNION ALL" +
                                    " SELECT scheduled AS \"date\", extract(YEAR FROM scheduled) AS year, extract(MONTH FROM scheduled) AS month," +
                                    " extract(DAY FROM scheduled) AS day, extract(HOUR FROM scheduled) as hour," +
                                    " to_char(scheduled, 'MONTH') AS month_word, to_char(scheduled, 'DAY') AS day_word" +
                                    " FROM class" +
                                    " ORDER BY full_date DESC",timeDimService, importTimeService);

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
                                    " ORDER BY zud.userid", lecturerDimService, importTimeService);
        // Populate facts
       etf.populateExamFact("SELECT cd.academicyearid AS ayid, e.scheduled as full_date, cd.semesterid as semester_id, " +
                                    "    cd.departmentid as department_id, cd.courseid as course_id, " +
                                    "    Round(Avg(er.points), 2) AS avg_points, count(er.studentid) AS turnout" +
                                    " FROM examresult er, exam e, course_department cd" +
                                    " WHERE er.examid = e.id AND e.course_departmentid = cd.id " +
                                    " GROUP BY cd.academicyearid, e.scheduled, cd.semesterid, cd.departmentid, cd.courseid" +
                                    " ORDER BY cd.academicyearid", examFactService, academicYearDimService, timeDimService, semesterDimService, departmentDimService,courseDimService, importTimeService);

        etf.populateEnrollmentFact("SELECT d.id AS dep_id, ay.id AS ay_id, s.id AS semester_id, sd.budget, " +
                                    " Decode " +
                                    " ( " +
                                    "   (SELECT e.title FROM enrollment e WHERE e.id = ue.enrollmentid), 'Regular', 0, 'Repeated', 1, 0) AS is_repeating, " +
                                    "  Count(zud.userid) AS enrolled_count " +
                                    " FROM zamgeruserdetails zud, USER_enrollment ue, course_department cd, academicyear ay, semester s, " +
                                    "  department d, studentdetails sd " +
                                    " WHERE ue.userid = zud.userid AND ue.course_departmentid = cd.id AND cd.academicyearid = ay.id " +
                                    "    AND cd.semesterid = s.id AND cd.departmentid = d.id AND sd.zamgeruserdetailsuserid = zud.userid " +
                                    " GROUP BY d.id, ay.id, s.id, sd.budget,5, ue.enrollmentid" +
                                    " ORDER BY d.id, ay.id, s.id, sd.budget, ue.enrollmentid", enrollmentFactService, departmentDimService,academicYearDimService,semesterDimService, importTimeService);

        etf.populateAttendanceFact("SELECT cd.departmentid as department_id, cd.courseid as course_id, c.scheduled as full_date, " +
                                    "    c.lecturerid as lecturer_id, " +
                                    "  Sum(ca.present) AS attendance, " +
                                    "  Round(Sum(ca.present) / Decode(Count(ca.id), 0, 1, Count(ca.id)), 2) AS attendance_percentage " +
                                    " FROM classattendance ca, class c, course_department cd " +
                                    " WHERE ca.classid = c.id AND c.course_departmentid = cd.id " +
                                    " GROUP BY cd.departmentid, cd.courseid, c.scheduled, c.lecturerid " +
                                    " ORDER BY cd.courseid", attendanceFactService, courseDimService, departmentDimService, timeDimService, lecturerDimService, importTimeService);

        // etf.disconnect();
        return 1;
    }
}