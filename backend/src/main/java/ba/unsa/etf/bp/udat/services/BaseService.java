package ba.unsa.etf.bp.udat.services;

import ba.unsa.etf.bp.udat.models.BaseModel;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Edin on 12.11.2017..
 */
public class BaseService<M extends BaseModel, R extends PagingAndSortingRepository<M, Long>> {
    protected R repository;

    @Autowired
    public void setRepository(R repository) {
        this.repository = repository;
    }

    public Iterable<M> all() {
        return repository.findAll();
    }

    public M get(Long id) throws ServiceException {
        if (id == null) {
            return null;
        }

        M model = repository.findOne(id);

        if(model == null) {
            throw new ServiceException("Did not found such id.");
        }

        return model;
    }

    public M save(M model) throws ServiceException{
        return repository.save(model);
    }

    public void delete(Long id) throws ServiceException {
        try {
            repository.delete(id);
        }
        catch (Exception e) {
            throw new ServiceException("Can't be performed.");
        }
    }

    public Boolean exists(Long id) { return repository.exists(id); }

    public Long count() { return repository.count(); }

    public Page<M> listAllByPage(Pageable p) {
        return repository.findAll(p);
    }
}
