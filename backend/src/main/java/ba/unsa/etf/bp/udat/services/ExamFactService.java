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
            if(ef.getAcademicYearDim().equals(model.getAcademicYearDim()) &&
                    ef.getTimeDim().equals(model.getTimeDim()) &&
                    ef.getSemesterDim().equals(model.getSemesterDim()) &&
                    ef.getDepartmentDim().equals(model.getDepartmentDim()) &&
                    ef.getCourseDim().equals(model.getCourseDim()) &&
             //BUG       ef.getAveragePoints().equals(model.getAveragePoints())
                    ef.getTurnout().equals(model.getTurnout()))
                return null;
        }
        return super.save(model);
    }

}
