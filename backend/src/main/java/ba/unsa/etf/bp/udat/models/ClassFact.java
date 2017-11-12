package ba.unsa.etf.bp.udat.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Edin on 12.11.2017..
 */
@Entity
public class ClassFact extends BaseModel {

    private int classId;
    private CourseDim course_dim;
    private DepartmentDim department_dim;
    private DateDim date_dim;
    private LecturerDim lecturer_dim;
    private int attendance;

    public ClassFact(int classId, CourseDim course_dim, DepartmentDim department_dim, DateDim date, LecturerDim lecturer_dim, int attendance) {
        this.classId = classId;
        this.course_dim = course_dim;
        this.department_dim = department_dim;
        this.date_dim = date;
        this.lecturer_dim = lecturer_dim;
        this.attendance = attendance;
    }
    public ClassFact() {}

    @Basic
    @Column(name = "class_id", nullable = false)
    @Size(max = 10) @NotNull
    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }


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
    @JoinColumn(name = "date_dim_id", referencedColumnName = "id", nullable = false)
    public DateDim getDate_dim() {
        return date_dim;
    }

    public void setDate_dim(DateDim date_dim) {
        this.date_dim = date_dim;
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
    public int getAttendance() {
        return attendance;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }
}
