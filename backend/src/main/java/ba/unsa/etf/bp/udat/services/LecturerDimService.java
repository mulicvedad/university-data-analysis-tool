package ba.unsa.etf.bp.udat.services;

import ba.unsa.etf.bp.udat.models.LecturerDim;
import ba.unsa.etf.bp.udat.repositories.LecturerDimRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class LecturerDimService extends BaseService<LecturerDim, LecturerDimRepository> {
    public LecturerDim save(LecturerDim model) throws ServiceException {
        Collection<LecturerDim> lista = repository.findLecturerDimsByLecturerZamgerUserId(model.getLecturerZamgerUserId());
        for(LecturerDim ld : lista )
        {
            if(ld.getFirstName().equals(model.getFirstName()) &&
                    ld.getLastName().equals(model.getLastName()) &&
                    ld.getIsStudent().equals(model.getIsStudent()) &&
              //BUG      ld.getSalary().equals(model.getSalary()) &&
                    ld.getGender().equals(model.getGender()))
                return null;
        }
        return super.save(model);
    }
    public LecturerDim findLecturerDim(Integer id) throws ServiceException
    {
        LecturerDim ld = repository.findLecturerDim(id);
        return ld;
    }



}
