package ba.unsa.etf.bp.udat.repositories;

import ba.unsa.etf.bp.udat.models.EnrollmentFact;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface EnrollmentFactRepository extends PagingAndSortingRepository<EnrollmentFact, Long>
{

    @Query("SELECT ef FROM EnrollmentFact ef")
    Collection<EnrollmentFact> findAll();

    @Query("SELECT SUM (ef.enrolledCount) FROM EnrollmentFact ef, AcademicYearDim ayd" +
            " WHERE ef.academicYearDim = ayd AND ayd.startYear = :ay")
    Integer filterByAcademicYear(@Param("ay") Integer ay);

    @Query("SELECT SUM (ef.enrolledCount) FROM EnrollmentFact ef, AcademicYearDim ayd" +
           " WHERE ef.academicYearDim = ayd AND ayd.startYear = :ay AND ef.isRepeating = 1")
    Integer filterByAcademicYearRepeatingStudents(@Param("ay") Integer ay);

    @Query("SELECT SUM(ef.enrolledCount) FROM EnrollmentFact ef, DepartmentDim dep " +
            "WHERE ef.departmentDim = dep AND dep.id =:dep")
    Integer filterByDepartment(@Param("dep") Long dep);

    @Query("SELECT SUM(ef.enrolledCount) FROM EnrollmentFact ef, AcademicYearDim ayd " +
            " WHERE ef.academicYearDim = ayd AND ayd.active = 1")
    Integer filterByActiveYear();

    @Query("SELECT SUM(ef.enrolledCount) FROM EnrollmentFact ef WHERE ef.budget = :budget")
    Integer filterByBudget(@Param("budget") Boolean budget);

    @Query("SELECT SUM(ef.enrolledCount) FROM EnrollmentFact ef WHERE ef.isRepeating = :isRepeating")
    Integer filterByRepeating(@Param("isRepeating") Boolean isRepeating);


    @Query("SELECT ef.academicYearDim, SUM(ef.enrolledCount) FROM EnrollmentFact ef" +
            " group by ef.academicYearDim order by ef.academicYearDim")
    List<Object[]> filterByAllYears();

    @Query("SELECT ef.departmentDim, SUM(ef.enrolledCount) FROM EnrollmentFact ef" +
            " group by ef.departmentDim order by ef.departmentDim")
    List<Object[]> filterByAllDepartments();

}
