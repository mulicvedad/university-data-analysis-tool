package ba.unsa.etf.bp.udat.repositories;

import ba.unsa.etf.bp.udat.models.CourseDim;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CourseDimRepository extends PagingAndSortingRepository<CourseDim, Long> {
    Collection<CourseDim> findAllByCourseId(int id);
    @Query("SELECT cd FROM CourseDim cd WHERE cd.courseId = :id")
    CourseDim findCourseDim(@Param("id") Integer id);

}
