package ba.unsa.etf.bp.udat.repositories;

import ba.unsa.etf.bp.udat.models.AttendanceFact;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

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

    @Query("SELECT af.departmentDim, AVG(af.attendance), AVG(af.attendancePercentage) FROM AttendanceFact af" +
            " group by af.departmentDim")
    List<Object[]> filterByAllDepartments();

    @Query("SELECT af.courseDim, AVG(af.attendance), AVG(af.attendancePercentage) FROM AttendanceFact af" +
            " group by af.courseDim")
    List<Object[]> filterByAllCourses();

    @Query("SELECT af.lecturerDim, AVG(af.attendance), AVG(af.attendancePercentage) FROM AttendanceFact af" +
            " group by af.lecturerDim")
    List<Object[]> filterByAllLecturers();
}

