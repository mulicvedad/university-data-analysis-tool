package ba.unsa.etf.bp.udat.repositories;

import ba.unsa.etf.bp.udat.models.LecturerDim;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface LecturerDimRepository extends PagingAndSortingRepository<LecturerDim,Long> {
    @Query("select l from LecturerDim l where l.lecturerZamgerUserId = :id")
    Collection<LecturerDim> findLecturerDimsByLecturerZamgerUserId(@Param("id") Integer id );
    @Query("SELECT ld FROM LecturerDim ld WHERE ld.lecturerZamgerUserId = :id")
    LecturerDim findLecturerDim(@Param("id") Integer id);

}
