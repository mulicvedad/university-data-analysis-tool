package ba.unsa.etf.bp.udat.services;

import ba.unsa.etf.bp.udat.models.ImportTime;
import ba.unsa.etf.bp.udat.models.SemesterDim;
import ba.unsa.etf.bp.udat.repositories.ImportTimeRepository;
import ba.unsa.etf.bp.udat.repositories.SemesterDimRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class ImportTimeService extends BaseService<ImportTime, ImportTimeRepository>{

    public ImportTime findByTableName(String tableName) throws ServiceException
    {
        ImportTime it = repository.findByTableName(tableName);
        return it;
    }


}