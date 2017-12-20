package ba.unsa.etf.bp.udat.repositories;

import ba.unsa.etf.bp.udat.models.ImportTime;

import java.sql.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ImportTimeRepository extends PagingAndSortingRepository<ImportTime, Long> {

    @Query("SELECT it FROM ImportTime it WHERE it.tableName = :name")
    ImportTime findByTableName(@Param("name") String tableName);

}
