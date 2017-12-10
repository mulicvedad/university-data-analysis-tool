package ba.unsa.etf.bp.udat.repositories;

import ba.unsa.etf.bp.udat.models.SemesterDim;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface SemesterDimRepository  extends PagingAndSortingRepository<SemesterDim, Long>
{
    Collection<SemesterDim> findAllBySemesterId(int id);
    @Query("SELECT sd FROM SemesterDim sd WHERE sd.semesterId = :id")
    SemesterDim findSemesterDim(@Param("id") Integer id);
}
