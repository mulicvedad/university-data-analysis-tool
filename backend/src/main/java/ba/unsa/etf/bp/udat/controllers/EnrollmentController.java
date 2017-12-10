package ba.unsa.etf.bp.udat.controllers;

import ba.unsa.etf.bp.udat.models.EnrollmentFact;
import ba.unsa.etf.bp.udat.services.EnrollmentFactService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
public class EnrollmentController extends BaseController<EnrollmentFact, EnrollmentFactService>
{

    @ResponseBody
    @GetMapping("/enrollment/academic_year")
    public Integer enrollmentByAcademicYear(@RequestParam("ay") Integer ay) {
        return service.filterByAcademicYear(ay);
    }
    @ResponseBody
    @GetMapping("/enrollment/academic_year/active")
    public Integer enrollmentByActiveYear() {
        return service.filterByActiveYear();
    }


    /*
    @ResponseBody
    @GetMapping("/registered_students")
    public int filterByAcademicYearAllStudents(@RequestParam("year_value") int year_value) {
        return service.filterByAcademicYearAllStudents(year_value);
    }
*/
    @ResponseBody
    @GetMapping("/enrollment/department")
    public Integer enrollmentByDepartment(@RequestParam("dep") String dep)
    {
        return service.filterByDepartment(dep);
    }

}
