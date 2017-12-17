package ba.unsa.etf.bp.udat.controllers;
import org.springframework.web.bind.annotation.*;
import ba.unsa.etf.bp.udat.models.SemesterDim;
import ba.unsa.etf.bp.udat.services.SemesterDimService;

import org.springframework.http.ResponseEntity;

@RestController
public class SemesterController extends BaseController<SemesterDim, SemesterDimService>
{
    @Override
    @ResponseBody
    @GetMapping("/semesters")
    public ResponseEntity getPage(@RequestParam("page") int pageNumber) {
        return super.getPage(pageNumber);
    }

}