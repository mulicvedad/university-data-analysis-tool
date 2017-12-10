package ba.unsa.etf.bp.udat.repositories;

import ba.unsa.etf.bp.udat.models.TimeDim;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;

@Repository
public interface TimeDimRepository extends PagingAndSortingRepository<TimeDim,Long> {
    Collection<TimeDim> findAllByFullDate(Date date);
    @Query("SELECT td FROM TimeDim td WHERE td.fullDate = :date")
    TimeDim findTimeDim(@Param("date") Date date);
}
