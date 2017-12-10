package ba.unsa.etf.bp.udat.services;

import ba.unsa.etf.bp.udat.models.DepartmentDim;
import ba.unsa.etf.bp.udat.repositories.DepartmentDimRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class DepartmentDimService extends BaseService<DepartmentDim, DepartmentDimRepository> {


    public DepartmentDim save(DepartmentDim model) throws ServiceException {
        Collection<DepartmentDim> lista = repository.findAllByDepartmentId(model.getDepartmentId());
        for(DepartmentDim dd : lista )
        {
            if(dd.getTitle().equals(model.getTitle()))
                return null;
        }
        return super.save(model);
    }

}
