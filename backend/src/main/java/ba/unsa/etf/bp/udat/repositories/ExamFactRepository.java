package ba.unsa.etf.bp.udat.repositories;

import ba.unsa.etf.bp.udat.models.ExamFact;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ExamFactRepository extends PagingAndSortingRepository<ExamFact, Long> {
    @Query("SELECT ex FROM ExamFact ex")
    Collection<ExamFact> findAll();

    @Query("SELECT ef.academicYearDim, AVG(ef.turnout), AVG(ef.averagePoints) FROM ExamFact ef" +
            " group by ef.academicYearDim order by ef.academicYearDim")
    List<Object[]> filterByAllYears();

    @Query("SELECT ef.departmentDim, AVG(ef.turnout), AVG(ef.averagePoints) FROM ExamFact ef" +
            " group by ef.departmentDim order by ef.departmentDim")
    List<Object[]> filterByAllDepartments();

    @Query("SELECT ef.courseDim, AVG(ef.turnout), AVG(ef.averagePoints) FROM ExamFact ef" +
            " group by ef.courseDim order by ef.courseDim")
    List<Object[]> filterByAllCourses();
}

