package ba.unsa.etf.bp.udat.controllers;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.rmi.ServerException;
import ba.unsa.etf.bp.udat.models.AttendanceFact;
import ba.unsa.etf.bp.udat.services.AttendanceFactService;

@RestController
public class AttendanceController extends BaseController<AttendanceFact, AttendanceFactService>
{
    @ResponseBody
    @GetMapping("/attendance")
    public BigDecimal attendanceByDepartmentCourseLecturer(@RequestParam("dep") Long dep, 
    @RequestParam("course") Long course, @RequestParam("lecturer") Long lecturer) throws ServerException
    {
        return service.attendanceByDepartmentCourseLecturer(dep, course, lecturer).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    @ResponseBody
    @GetMapping("/attendancePercentage")
    public BigDecimal attendancePercentageByDepartmentCourseLecturer(@RequestParam("dep") Long dep,
                                                           @RequestParam("course") Long course, @RequestParam("lecturer") Long lecturer) throws ServerException
    {
        return service.attendancePercentageByDepartmentCourseLecturer(dep, course, lecturer).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

}