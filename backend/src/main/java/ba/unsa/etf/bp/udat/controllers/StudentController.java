package ba.unsa.etf.bp.udat.controllers;

import ba.unsa.etf.bp.udat.models.EnrollmentFact;
import ba.unsa.etf.bp.udat.services.EnrollmentFactService;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class StudentController extends BaseController<EnrollmentFact, EnrollmentFactService>
{
/*
    @ResponseBody
    @GetMapping("/freshmen")
    public Collection<EnrollmentFact> filterByFresmen() {
        return service.filterByFreshmen();
    }
    @ResponseBody
    @GetMapping("/academic_year")
    public int filterByAcademicYear(@RequestParam("year_value") int year_value) {
        return service.filterByAcademicYear(year_value);
    }
    @ResponseBody
    @GetMapping("/registered_students")
    public int filterByAcademicYearAllStudents(@RequestParam("year_value") int year_value) {
        return service.filterByAcademicYearAllStudents(year_value);
    }
*/

}
