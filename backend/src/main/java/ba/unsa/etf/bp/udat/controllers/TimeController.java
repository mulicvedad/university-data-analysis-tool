package ba.unsa.etf.bp.udat.controllers;
import org.springframework.web.bind.annotation.*;
import ba.unsa.etf.bp.udat.models.TimeDim;
import ba.unsa.etf.bp.udat.services.TimeDimService;

import org.springframework.http.ResponseEntity;

@RestController
public class TimeController extends BaseController<TimeDim, TimeDimService>
{
    @Override
    @ResponseBody
    @GetMapping("/times")
    public ResponseEntity getPage(@RequestParam("page") int pageNumber) {
        return super.getPage(pageNumber);
    }

}