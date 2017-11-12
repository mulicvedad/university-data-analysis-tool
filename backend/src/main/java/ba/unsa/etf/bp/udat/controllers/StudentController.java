package ba.unsa.etf.bp.udat.controllers;

import ba.unsa.etf.bp.udat.models.StudentFact;
import ba.unsa.etf.bp.udat.services.StudentFactService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * Created by Edin on 12.11.2017..
 */
@RestController
public class StudentController extends BaseController<StudentFact, StudentFactService>
{

    @ResponseBody
    @GetMapping("/freshmen")
    public Collection<StudentFact> filterByFresmen() {
        return service.filterByFreshmen();
    }

}
