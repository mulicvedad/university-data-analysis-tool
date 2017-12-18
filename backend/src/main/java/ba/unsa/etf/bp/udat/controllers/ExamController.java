package ba.unsa.etf.bp.udat.controllers;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.rmi.ServerException;
import ba.unsa.etf.bp.udat.models.ExamFact;
import ba.unsa.etf.bp.udat.services.ExamFactService;

@RestController
public class ExamController extends BaseController<ExamFact, ExamFactService>
{
    @ResponseBody
    @GetMapping("/exams/turnout")
    public Integer turnoutByAcademicYearDepartmentCourse(@RequestParam("ay") Long ay, 
    @RequestParam("dep") Long dep, @RequestParam("course") Long course) throws ServerException
    {
        return service.turnoutByAcademicYearDepartmentCourse(ay, dep, course);
    }
    @ResponseBody
    @GetMapping("/exams/averagePoints")
    public BigDecimal averagePointsByAcademicYearDepartmentCourse(@RequestParam("ay") Long ay, 
    @RequestParam("dep") Long dep, @RequestParam("course") Long course) throws ServerException
    {
        return service.averagePointsByAcademicYearDepartmentCourse(ay, dep, course).setScale(2, BigDecimal.ROUND_HALF_UP);
    }


}