package ba.unsa.etf.bp.udat.repositories;

import ba.unsa.etf.bp.udat.models.ExamFact;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ExamFactRepository extends PagingAndSortingRepository<ExamFact, Long> {
    @Query("SELECT ex FROM ExamFact ex")
    Collection<ExamFact> findAll();
}
