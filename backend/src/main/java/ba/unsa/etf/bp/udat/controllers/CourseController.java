package ba.unsa.etf.bp.udat.controllers;
import org.springframework.web.bind.annotation.*;
import ba.unsa.etf.bp.udat.models.CourseDim;
import ba.unsa.etf.bp.udat.services.CourseDimService;
import org.springframework.http.ResponseEntity;

@RestController
public class CourseController extends BaseController<CourseDim, CourseDimService>
{
    @Override
    @ResponseBody
    @GetMapping("/courses")
    public ResponseEntity getPage(@RequestParam("page") int pageNumber) {
        return super.getPage(pageNumber);
    }

}