package ba.unsa.etf.bp.udat.services;

import ba.unsa.etf.bp.udat.models.CourseDim;
import ba.unsa.etf.bp.udat.repositories.CourseDimRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CourseDimService extends BaseService<CourseDim, CourseDimRepository> {


    public CourseDim save(CourseDim model, boolean firstImport) throws ServiceException {
        if(firstImport == true)
            return super.save(model);
        Collection<CourseDim> lista = repository.findAllByCourseId(model.getCourseId());
        for(CourseDim cd : lista )
        {
            if(cd.getTitle().equals(model.getTitle()))
                return null;
        }
        return super.save(model);
    }
    public CourseDim findCourseDim(Integer id) throws ServiceException
    {
        CourseDim cd = repository.findCourseDim(id);
        return cd;
    }


}
