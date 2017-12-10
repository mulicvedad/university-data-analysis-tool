package ba.unsa.etf.bp.udat.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
public class AttendanceFact extends BaseModel {

    private CourseDim course_dim;
    private DepartmentDim department_dim;
    private TimeDim time_dim;
    private LecturerDim lecturer_dim;
    private Integer attendance;
    private BigDecimal attendance_percentage;

    public AttendanceFact(CourseDim course_dim, DepartmentDim department_dim, TimeDim time_dim, LecturerDim lecturer_dim, Integer attendance, BigDecimal attendance_percentage) {
        this.course_dim = course_dim;
        this.department_dim = department_dim;
        this.time_dim = time_dim;
        this.lecturer_dim = lecturer_dim;
        this.attendance = attendance;
        this.attendance_percentage = attendance_percentage;
    }
    public AttendanceFact() {}


    @ManyToOne
    @JoinColumn(name = "course_dim_id", referencedColumnName = "id", nullable = false)
    public CourseDim getCourse_dim() {
        return course_dim;
    }

    public void setCourse_dim(CourseDim course_dim) {
        this.course_dim = course_dim;
    }

    @ManyToOne
    @JoinColumn(name = "department_dim_id", referencedColumnName = "id", nullable = false)
    public DepartmentDim getDepartment_dim() {
        return department_dim;
    }

    public void setDepartment_dim(DepartmentDim department_dim) {
        this.department_dim = department_dim;
    }

    @ManyToOne
    @JoinColumn(name = "time_dim_id", referencedColumnName = "id", nullable = false)
    public TimeDim getTime_dim() {
        return time_dim;
    }

    public void setTime_dim(TimeDim time_dim) {
        this.time_dim = time_dim;
    }

    @ManyToOne
    @JoinColumn(name = "lecturer_dim_id", referencedColumnName = "id", nullable = false)
    public LecturerDim getLecturer_dim() {
        return lecturer_dim;
    }

    public void setLecturer_dim(LecturerDim lecturer_dim) {
        this.lecturer_dim = lecturer_dim;
    }

    @Basic
    @Column(name = "attendance", nullable = false)
    @Size(max = 10) @NotNull
    public Integer getAttendance() {
        return attendance;
    }

    public void setAttendance(Integer attendance) {
        this.attendance = attendance;
    }

    @Basic
    @Column(name = "attendance_percentage", nullable = false)
    public BigDecimal getAttendance_percentage() {
        return attendance_percentage;
    }

    public void setAttendance_percentage(BigDecimal attendance_percentage) {
        this.attendance_percentage = attendance_percentage;
    }
}
