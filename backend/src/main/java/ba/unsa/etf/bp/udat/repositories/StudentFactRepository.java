package ba.unsa.etf.bp.udat.repositories;

import ba.unsa.etf.bp.udat.models.StudentFact;
import ba.unsa.etf.bp.udat.models.DateDim;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

/**
 * Created by Edin on 12.11.2017..
 */
public interface StudentFactRepository  extends PagingAndSortingRepository<StudentFact, Long>
{
    @Query("Select s from StudentFact s where s.enrollmentDate.date = s.semester_dim.startDate")
    Collection<StudentFact> filterByFreshmen();

    @Query("SELECT  COUNT (DISTINCT s.id) FROM StudentFact s WHERE " +
            "(s.enrollmentDate.year = :year_value)")
    int filterByAcademicYear(@Param("year_value") int year_value);
    @Query("SELECT  COUNT (DISTINCT s.id) FROM StudentFact s WHERE " +
            "(s.enrollmentDate.year <= :year_value) AND " +
            "(s.dissrollmentDate IS NULL OR s.dissrollmentDate.year >= :year_value)")
    int filterByAcademicYearAllStudents(@Param("year_value") int year_value);

}
