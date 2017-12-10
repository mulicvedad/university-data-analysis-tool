package ba.unsa.etf.bp.udat.repositories;

import ba.unsa.etf.bp.udat.models.DepartmentDim;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface DepartmentDimRepository extends PagingAndSortingRepository<DepartmentDim, Long> {
    Collection<DepartmentDim> findAllByDepartmentId(int id);
}
