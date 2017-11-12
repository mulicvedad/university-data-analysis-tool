package ba.unsa.etf.bp.udat.models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Edin on 12.11.2017..
 */
public class ClassFact extends BaseModel {

    private int classId;
    private CourseDim course;
    private DepartmentDim department;
    private DateDim date;
    private LecturerDim lecturer;
    private int attendance;

    public ClassFact(int classId, CourseDim course, DepartmentDim department, DateDim date, LecturerDim lecturer, int attendance) {
        this.classId = classId;
        this.course = course;
        this.department = department;
        this.date = date;
        this.lecturer = lecturer;
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
    public CourseDim getCourse() {
        return course;
    }

    public void setCourse(CourseDim course) {
        this.course = course;
    }

    @ManyToOne
    @JoinColumn(name = "department_dim_id", referencedColumnName = "id", nullable = false)
    public DepartmentDim getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentDim department) {
        this.department = department;
    }

    @ManyToOne
    @JoinColumn(name = "date_dim_id", referencedColumnName = "id", nullable = false)
    public DateDim getDate() {
        return date;
    }

    public void setDate(DateDim date) {
        this.date = date;
    }

    @ManyToOne
    @JoinColumn(name = "lecturer_dim_id", referencedColumnName = "id", nullable = false)
    public LecturerDim getLecturer() {
        return lecturer;
    }

    public void setLecturer(LecturerDim lecturer) {
        this.lecturer = lecturer;
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
