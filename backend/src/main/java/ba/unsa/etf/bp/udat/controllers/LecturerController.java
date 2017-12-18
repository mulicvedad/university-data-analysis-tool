package ba.unsa.etf.bp.udat.controllers;
import org.springframework.web.bind.annotation.*;
import ba.unsa.etf.bp.udat.models.LecturerDim;
import ba.unsa.etf.bp.udat.services.LecturerDimService;

import org.springframework.http.ResponseEntity;

@RestController
public class LecturerController extends BaseController<LecturerDim, LecturerDimService>
{
    @Override
    @ResponseBody
    @GetMapping("/lecturers")
    public ResponseEntity getPage(@RequestParam("page") int pageNumber) {
        return super.getPage(pageNumber);
    }

}