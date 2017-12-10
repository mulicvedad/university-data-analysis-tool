package ba.unsa.etf.bp.udat.services;

import ba.unsa.etf.bp.udat.models.ExamFact;
import ba.unsa.etf.bp.udat.repositories.ExamFactRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ExamFactService extends BaseService<ExamFact, ExamFactRepository> {
    public ExamFact save(ExamFact model) throws ServiceException {
        Collection<ExamFact> lista = repository.findAll();
        for(ExamFact ef : lista )
        {
            if(ef.getAcademic_year_dim().equals(model.getAcademic_year_dim()) &&
                    ef.getTime_dim().equals(model.getTime_dim()) &&
                    ef.getSemester_dim().equals(model.getSemester_dim()) &&
                    ef.getDepartment_dim().equals(model.getDepartment_dim()) &&
                    ef.getCourse_dim().equals(model.getCourse_dim()) &&
             //BUG       ef.getAveragePoints().equals(model.getAveragePoints())
                    ef.getTurnout().equals(model.getTurnout()))
                return null;
        }
        return super.save(model);
    }

}
