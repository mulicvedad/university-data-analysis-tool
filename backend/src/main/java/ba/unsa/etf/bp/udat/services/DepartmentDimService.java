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


    public DepartmentDim save(DepartmentDim model, boolean firstImport) throws ServiceException {
        if(firstImport == true)
            return super.save(model);
        Collection<DepartmentDim> lista = repository.findAllByDepartmentId(model.getDepartmentId());
        for(DepartmentDim dd : lista )
        {
            if(dd.getTitle().equals(model.getTitle()))
                return null;
        }
        return super.save(model);
    }
    public DepartmentDim findDepartmentDim(Integer id) throws ServiceException
    {
        DepartmentDim dd = repository.findDepartmentDim(id);
        return dd;
    }
    public DepartmentDim findDepartmentDimByTitle(String title) throws ServiceException
    {
        DepartmentDim dd = repository.findDepartmentDimByTitle(title);
        return dd;
    }


}
