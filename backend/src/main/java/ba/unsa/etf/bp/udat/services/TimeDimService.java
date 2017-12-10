package ba.unsa.etf.bp.udat.services;

import ba.unsa.etf.bp.udat.models.TimeDim;
import ba.unsa.etf.bp.udat.repositories.TimeDimRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.Collection;
import java.util.Date;

@Service
public class TimeDimService extends BaseService<TimeDim,TimeDimRepository> {
    public TimeDim save(TimeDim model) throws ServiceException {
        Collection<TimeDim> lista = repository.findAllByFullDate(model.getFullDate());
        if(lista.size() > 0)
            return null;
        return super.save(model);
    }
    public TimeDim findTimeDim(Date date) throws ServiceException
    {
        TimeDim td = repository.findTimeDim(date);
        return td;
    }


}
