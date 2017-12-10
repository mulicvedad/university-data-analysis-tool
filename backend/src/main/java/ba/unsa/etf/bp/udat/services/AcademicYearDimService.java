package ba.unsa.etf.bp.udat.services;

import ba.unsa.etf.bp.udat.models.AcademicYearDim;
import ba.unsa.etf.bp.udat.repositories.AcademicYearDimRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AcademicYearDimService extends BaseService<AcademicYearDim, AcademicYearDimRepository>{

    public AcademicYearDim save(AcademicYearDim model) throws ServiceException {
        Collection<AcademicYearDim> lista = repository.findAllByAcademicYearId(model.getAcademicYearId());
        for(AcademicYearDim ayd : lista )
        {
            if(ayd.getTitle().equals(model.getTitle()) &&
                    ayd.getActive().equals(model.getActive()) &&
                    ayd.getStart_year().equals(model.getStart_year()))
                return null;
        }
        return super.save(model);
    }

}
