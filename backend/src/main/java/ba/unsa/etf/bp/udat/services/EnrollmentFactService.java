package ba.unsa.etf.bp.udat.services;

import ba.unsa.etf.bp.udat.models.EnrollmentFact;
import ba.unsa.etf.bp.udat.repositories.EnrollmentFactRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class EnrollmentFactService extends BaseService<EnrollmentFact, EnrollmentFactRepository>{
    public EnrollmentFact save(EnrollmentFact model) throws ServiceException {
        Collection<EnrollmentFact> lista = repository.findAll();
        for(EnrollmentFact ef : lista )
        {
            if(ef.getAcademic_year_dim().equals(model.getAcademic_year_dim()) &&
                    ef.getDepartment_dim().equals(model.getDepartment_dim()) &&
                    ef.getSemester_dim().equals(model.getSemester_dim()) &&
                    ef.isBudget().equals(model.isBudget()) &&
                    ef.getIs_repeating().equals(model.getIs_repeating()) &&
                    ef.getEnrolled_count().equals(model.getEnrolled_count()))
                return null;
        }
        return super.save(model);
    }

}
