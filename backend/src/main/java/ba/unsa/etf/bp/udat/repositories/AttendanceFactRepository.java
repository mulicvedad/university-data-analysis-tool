package ba.unsa.etf.bp.udat.repositories;

import ba.unsa.etf.bp.udat.models.AttendanceFact;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import java.math.BigDecimal;
import java.util.Collection;

@Repository
public interface AttendanceFactRepository extends PagingAndSortingRepository<AttendanceFact, Long> {
    @Query("SELECT af FROM AttendanceFact af")
    Collection<AttendanceFact> findAll();
    @Query("SELECT af.attendancePercentage FROM AttendanceFact af,DepartmentDim depd, CourseDim cod, LecturerDim ld " +
           " WHERE ((af.departmentDim = depd AND depd.id = :dep) OR (:dep = -1)) AND " +
           " ((af.courseDim = cod AND cod.id = :course) OR (:course = -1)) AND " +
           "((af.lecturerDim = ld AND ld.id = :lecturer) OR (:lecturer = -1))")
    BigDecimal attendanceByDepartmentCourseLecturer(@Param("dep")
    Long dep, @Param("course") Long course, @Param("lecturer") Long lecturer);
}

