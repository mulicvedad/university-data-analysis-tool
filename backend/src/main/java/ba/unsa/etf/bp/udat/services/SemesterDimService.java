package ba.unsa.etf.bp.udat.services;

import ba.unsa.etf.bp.udat.models.SemesterDim;
import ba.unsa.etf.bp.udat.repositories.SemesterDimRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class SemesterDimService extends BaseService<SemesterDim, SemesterDimRepository>{

    public SemesterDim save(SemesterDim model) throws ServiceException {
        Collection<SemesterDim> lista = repository.findAllBySemesterId(model.getSemesterId());
        for(SemesterDim sd : lista )
        {
            if(sd.getTitle().equals(model.getTitle()))
                return null;
        }
        return super.save(model);
    }
    public SemesterDim findSemesterDim(Integer id) throws ServiceException
    {
        SemesterDim sd = repository.findSemesterDim(id);
        return sd;
    }


}