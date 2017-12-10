package ba.unsa.etf.bp.udat.repositories;

import ba.unsa.etf.bp.udat.models.EnrollmentFact;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface EnrollmentFactRepository extends PagingAndSortingRepository<EnrollmentFact, Long>
{
    /* Potrebno ispraviti queryije
    @Query("Select s from EnrollmentFact s where s.enrollmentDate.date = s.semester_dim.startDate")
    Collection<EnrollmentFact> filterByFreshmen();

    @Query("SELECT  COUNT (DISTINCT s.id) FROM EnrollmentFact s WHERE " +
            "(s.enrollmentDate.year = :year_value)")
    int filterByAcademicYear(@Param("year_value") int year_value);
    @Query("SELECT  COUNT (DISTINCT s.id) FROM EnrollmentFact s WHERE " +
            "(s.enrollmentDate.year <= :year_value) AND " +
            "(s.dissrollmentDate IS NULL OR s.dissrollmentDate.year >= :year_value)")
    int filterByAcademicYearAllStudents(@Param("year_value") int year_value);
    */
    @Query("SELECT ef FROM EnrollmentFact ef")
    Collection<EnrollmentFact> findAll();

    @Query("SELECT SUM (ef.enrolledCount) FROM EnrollmentFact ef, AcademicYearDim ayd" +
            " WHERE ef.academicYearDim = ayd AND ayd.startYear = :ay")
    Integer filterByAcademicYear(@Param("ay") Integer ay);

    @Query("SELECT SUM(ef.enrolledCount) FROM EnrollmentFact ef, DepartmentDim dep " +
            "WHERE ef.departmentDim = dep AND dep.title =:dep")
    Integer filterByDepartment(@Param("dep") String dep);

    @Query("SELECT SUM(ef.enrolledCount) FROM EnrollmentFact ef, AcademicYearDim ayd " +
            " WHERE ef.academicYearDim = ayd AND ayd.active = 1")
    Integer filterByActiveYear();

}
