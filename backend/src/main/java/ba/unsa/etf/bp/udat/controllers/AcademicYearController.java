package ba.unsa.etf.bp.udat.controllers;
import org.springframework.web.bind.annotation.*;
import ba.unsa.etf.bp.udat.models.AcademicYearDim;
import ba.unsa.etf.bp.udat.services.AcademicYearDimService;
import org.springframework.http.ResponseEntity;

@RestController
public class AcademicYearController extends BaseController<AcademicYearDim, AcademicYearDimService>
{
    @Override
    @ResponseBody
    @GetMapping("/academic_years")
    public ResponseEntity getPage(@RequestParam("page") int pageNumber) {
        return super.getPage(pageNumber);
    }

}