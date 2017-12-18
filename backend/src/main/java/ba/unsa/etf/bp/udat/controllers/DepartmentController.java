package ba.unsa.etf.bp.udat.controllers;
import org.springframework.web.bind.annotation.*;
import ba.unsa.etf.bp.udat.models.DepartmentDim;
import ba.unsa.etf.bp.udat.services.DepartmentDimService;
import org.springframework.http.ResponseEntity;

@RestController
public class DepartmentController extends BaseController<DepartmentDim, DepartmentDimService>
{
    @Override
    @ResponseBody
    @GetMapping("/departments")
    public ResponseEntity getPage(@RequestParam("page") int pageNumber) {
        return super.getPage(pageNumber);
    }

}