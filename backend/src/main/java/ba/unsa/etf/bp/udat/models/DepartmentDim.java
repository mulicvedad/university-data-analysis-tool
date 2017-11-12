package ba.unsa.etf.bp.udat.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Date;

/**
 * Created by Edin on 12.11.2017..
 */
public class DepartmentDim extends BaseModel{

    private int departmentId;
    private String title;
    private Date startDate;
    private Date endDate;
    private Collection<StudentFact> students;
    private Collection<ClassFact> classes;
    private Collection<ExamFact> exams;

    public DepartmentDim(int departmentId, String title, Date startDate, Date endDate) {
        this.departmentId = departmentId;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public DepartmentDim() {}

    @Basic
    @Column(name = "department_id", nullable = false)
    @Size(max = 10) @NotNull
    public int getDepartmentId() {
        return departmentId;
    }


    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
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
    @Column(name = "start_date", nullable = false)
    @NotNull
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "end_date", nullable = false)
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @OneToMany(mappedBy = "department_dim")
    @JsonIgnore
    public Collection<StudentFact> getStudents() {
        return students;
    }

    public void setStudents(Collection<StudentFact> students) {
        this.students = students;
    }

    @OneToMany(mappedBy = "department_dim")
    @JsonIgnore
    public Collection<ClassFact> getClasses() {
        return classes;
    }

    public void setClasses(Collection<ClassFact> classes) {
        this.classes = classes;
    }

    @OneToMany(mappedBy = "department_dim")
    @JsonIgnore
    public Collection<ExamFact> getExams() {
        return exams;
    }

    public void setExams(Collection<ExamFact> exams) {
        this.exams = exams;
    }
}
