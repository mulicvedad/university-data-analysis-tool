package ba.unsa.etf.bp.udat.repositories;

import ba.unsa.etf.bp.udat.models.AcademicYearDim;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface AcademicYearDimRepository extends PagingAndSortingRepository<AcademicYearDim,Long>{

    Collection<AcademicYearDim> findAllByAcademicYearId(int id);

    @Query("SELECT ayd FROM AcademicYearDim ayd WHERE ayd.academicYearId = :id")
    AcademicYearDim findAcademicYearDim(@Param("id") Integer id);

}
