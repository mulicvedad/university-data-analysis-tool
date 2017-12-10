package ba.unsa.etf.bp.udat.repositories;

import ba.unsa.etf.bp.udat.models.AttendanceFact;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface AttendanceFactRepository extends PagingAndSortingRepository<AttendanceFact, Long> {
    @Query("SELECT af FROM AttendanceFact af")
    Collection<AttendanceFact> findAll();

}
