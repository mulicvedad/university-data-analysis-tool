package ba.unsa.etf.bp.udat.controllers;
import ba.unsa.etf.bp.udat.models.EnrollmentFact;
import ba.unsa.etf.bp.udat.services.EnrollmentFactService;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.rmi.ServerException;


@RestController
public class EnrollmentController extends BaseController<EnrollmentFact, EnrollmentFactService>
{

    @ResponseBody
    @GetMapping("/enrollment/academic_year")
    public Integer enrollmentByAcademicYear(@RequestParam("ay") Integer ay) {
        return service.filterByAcademicYear(ay);
    }
    @ResponseBody
    @GetMapping("/enrollment/academic_year/budget")
    public Integer enrollmentByAcademicYearBudget(@RequestParam("ay") Integer ay, @RequestParam("budget") Boolean budget) {
        return service.filterByAcademicYearBudget(ay,budget);
    }   
    @ResponseBody
    @GetMapping("/enrollment/academic_year/active")
    public Integer enrollmentByActiveYear() {
        return service.filterByActiveYear();
    }

    @ResponseBody
    @GetMapping("/enrollment/department")
    public Integer enrollmentByDepartment(@RequestParam("dep") Long dep)
    {
        return service.filterByDepartment(dep);
    }
    @ResponseBody
    @GetMapping("/enrollment/budget")
    public Integer enrollmentByBudget(@RequestParam("budget") Boolean budget)
    {
        return service.filterByBudget(budget);
    }
    @ResponseBody
    @GetMapping("/enrollment/isRepeating")
    public Integer enrollmentByRepeating(@RequestParam("isRepeating") Boolean isRepeating) throws ServerException
    {
        return service.filterByRepeating(isRepeating);
    }
    @ResponseBody
    @GetMapping("/enrollment/percentage/activeAcademicYear")
    public BigDecimal enrollmentIncreaseByYear(@RequestParam("ay") Integer ay) throws ServerException
    {
        Double currentYearEnrollment = service.filterByAcademicYear(ay).doubleValue();
        Double lastYearEnrollment = service.filterByAcademicYear(ay - 1).doubleValue();
        Double increasePercentage = (currentYearEnrollment - lastYearEnrollment) / lastYearEnrollment;
        return  new BigDecimal(increasePercentage.toString()).setScale(4, BigDecimal.ROUND_HALF_UP);
    }
    @ResponseBody
    @GetMapping("/enrollment/percentage/repeating")
    public BigDecimal increaseByRepeatingStudents(@RequestParam("ay") Integer ay) throws ServerException
    {
        Double currentYearRepeaters = service.filterByAcademicYearRepeatingStudents(ay).doubleValue();
        Double lastYearRepeaters = service.filterByAcademicYearRepeatingStudents(ay - 1).doubleValue();
        Double increasePercentage = (currentYearRepeaters - lastYearRepeaters) / lastYearRepeaters;
        return  new BigDecimal(increasePercentage.toString()).setScale(4, BigDecimal.ROUND_HALF_UP);

    }


}
