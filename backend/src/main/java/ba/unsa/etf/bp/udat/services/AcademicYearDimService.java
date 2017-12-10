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
                    ayd.getStartYear().equals(model.getStartYear()))
                return null;
        }
        return super.save(model);
    }
    public AcademicYearDim findAcademicYearDim(Integer id) throws ServiceException
    {
        AcademicYearDim ayd = repository.findAcademicYearDim(id);
        return ayd;
    }

}
