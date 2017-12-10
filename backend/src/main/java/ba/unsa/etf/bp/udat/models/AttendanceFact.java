package ba.unsa.etf.bp.udat.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
public class AttendanceFact extends BaseModel {

    private CourseDim courseDim;
    private DepartmentDim departmentDim;
    private TimeDim timeDim;
    private LecturerDim lecturerDim;
    private Integer attendance;
    private BigDecimal attendancePercentage;

    public AttendanceFact(CourseDim courseDim, DepartmentDim departmentDim, TimeDim timeDim, LecturerDim lecturerDim, Integer attendance, BigDecimal attendancePercentage) {
        this.courseDim = courseDim;
        this.departmentDim = departmentDim;
        this.timeDim = timeDim;
        this.lecturerDim = lecturerDim;
        this.attendance = attendance;
        this.attendancePercentage = attendancePercentage;
    }
    public AttendanceFact() {}


    @ManyToOne
    @JoinColumn(name = "course_dim_id", referencedColumnName = "id", nullable = false)
    public CourseDim getCourseDim() {
        return courseDim;
    }

    public void setCourseDim(CourseDim courseDim) {
        this.courseDim = courseDim;
    }

    @ManyToOne
    @JoinColumn(name = "department_dim_id", referencedColumnName = "id", nullable = false)
    public DepartmentDim getDepartmentDim() {
        return departmentDim;
    }

    public void setDepartmentDim(DepartmentDim departmentDim) {
        this.departmentDim = departmentDim;
    }

    @ManyToOne
    @JoinColumn(name = "time_dim_id", referencedColumnName = "id", nullable = false)
    public TimeDim getTimeDim() {
        return timeDim;
    }

    public void setTimeDim(TimeDim timeDim) {
        this.timeDim = timeDim;
    }

    @ManyToOne
    @JoinColumn(name = "lecturer_dim_id", referencedColumnName = "id", nullable = false)
    public LecturerDim getLecturerDim() {
        return lecturerDim;
    }

    public void setLecturerDim(LecturerDim lecturerDim) {
        this.lecturerDim = lecturerDim;
    }

    @Basic
    @Column(name = "attendance", nullable = false)
    public Integer getAttendance() {
        return attendance;
    }

    public void setAttendance(Integer attendance) {
        this.attendance = attendance;
    }

    @Basic
    @Column(name = "attendance_precentage", nullable = false)
    public BigDecimal getAttendancePercentage() {
        return attendancePercentage;
    }

    public void setAttendancePercentage(BigDecimal attendancePercentage) {
        this.attendancePercentage = attendancePercentage;
    }
}
