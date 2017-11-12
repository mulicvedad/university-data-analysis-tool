package ba.unsa.etf.bp.udat.repositories;

import ba.unsa.etf.bp.udat.models.StudentFact;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;

/**
 * Created by kalashnikov on 12.11.2017..
 */
public interface StudentFactRepository  extends PagingAndSortingRepository<StudentFact, Long>
{
    @Query("Select s from StudentFact s where s.enrollmentDate.date = s.semester_dim.startDate")
    Collection<StudentFact> filterByFreshmen();
}
