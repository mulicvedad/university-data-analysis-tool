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
    public CourseDim getCourse_dim() {
        return courseDim;
    }

    public void setCourse_dim(CourseDim courseDim) {
        this.courseDim = courseDim;
    }

    @ManyToOne
    @JoinColumn(name = "department_dim_id", referencedColumnName = "id", nullable = false)
    public DepartmentDim getDepartment_dim() {
        return departmentDim;
    }

    public void setDepartment_dim(DepartmentDim departmentDim) {
        this.departmentDim = departmentDim;
    }

    @ManyToOne
    @JoinColumn(name = "time_dim_id", referencedColumnName = "id", nullable = false)
    public TimeDim getTime_dim() {
        return timeDim;
    }

    public void setTime_dim(TimeDim timeDim) {
        this.timeDim = timeDim;
    }

    @ManyToOne
    @JoinColumn(name = "lecturer_dim_id", referencedColumnName = "id", nullable = false)
    public LecturerDim getLecturer_dim() {
        return lecturerDim;
    }

    public void setLecturer_dim(LecturerDim lecturerDim) {
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
    public BigDecimal getAttendance_percentage() {
        return attendancePercentage;
    }

    public void setAttendance_percentage(BigDecimal attendancePercentage) {
        this.attendancePercentage = attendancePercentage;
    }
}
