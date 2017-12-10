package ba.unsa.etf.bp.udat.repositories;

import ba.unsa.etf.bp.udat.models.SemesterDim;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface SemesterDimRepository  extends PagingAndSortingRepository<SemesterDim, Long>
{
    Collection<SemesterDim> findAllBySemesterId(int id);
}
