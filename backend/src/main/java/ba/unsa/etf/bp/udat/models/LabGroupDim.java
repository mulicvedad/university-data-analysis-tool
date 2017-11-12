package ba.unsa.etf.bp.udat.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

/**
 * Created by Edin on 12.11.2017..
 */
@Entity
public class LabGroupDim extends BaseModel{

    private int labGroupId;
    private String title;
    private String course;
    private String department;
    private int academicYear;
    private Collection<StudentFact> students;

    public LabGroupDim(int labGroupId, String title, String course, String department, int academicYear) {
        this.labGroupId = labGroupId;
        this.title = title;
        this.course = course;
        this.department = department;
        this.academicYear = academicYear;
    }

    public LabGroupDim() {}

    @Basic
    @Column(name = "lab_group_id", nullable = false)
    @Size(max = 10)@NotNull
    public int getLabGroupId() {
        return labGroupId;
    }


    public void setLabGroupId(int labGroupId) {
        this.labGroupId = labGroupId;
    }

    @Basic
    @Column(name = "title", nullable = false)
    @Size(min = 4, max = 255)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "course", nullable = false)
    @Size(min = 4, max = 255)@NotNull
    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Basic
    @Column(name = "department", nullable = false)
    @Size(min = 4, max = 255)@NotNull
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Basic
    @Column(name = "academic_year", nullable = false)
    @Size(min = 4, max = 255)
    public int getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(int academicYear) {
        this.academicYear = academicYear;
    }

    @OneToMany(mappedBy = "lab_group_dim")
    @JsonIgnore
    public Collection<StudentFact> getStudents() {
        return students;
    }

    public void setStudents(Collection<StudentFact> students) {
        this.students = students;
    }
}
