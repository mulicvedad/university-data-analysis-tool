package ba.unsa.etf.bp.udat.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Date;


@Entity
public class DepartmentDim extends BaseModel{

    private Integer departmentId;
    private String title;
    private Collection<EnrollmentFact> enrollmentFacts;
    private Collection<AttendanceFact> attendanceFacts;
    private Collection<ExamFact> examFacts;
    
    public DepartmentDim(Integer departmentId, String title) {
        this.departmentId = departmentId;
        this.title = title;
    }

    public DepartmentDim() {}

    @Basic
    @Column(name = "department_id", nullable = false)
    public Integer getDepartmentId() {
        return departmentId;
    }


    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    @Basic
    @Column(name = "title", nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @OneToMany(mappedBy = "departmentDim")
    @JsonIgnore
    public Collection<EnrollmentFact> getEnrollmentFacts() {
        return enrollmentFacts;
    }

    public void setEnrollmentFacts(Collection<EnrollmentFact> students) {
        this.enrollmentFacts = enrollmentFacts;
    }

    @OneToMany(mappedBy = "departmentDim")
    @JsonIgnore
    public Collection<AttendanceFact> getAttendanceFacts() {
        return attendanceFacts;
    }

    public void setAttendanceFacts(Collection<AttendanceFact> classes) {
        this.attendanceFacts = attendanceFacts;
    }

    @OneToMany(mappedBy = "departmentDim")
    @JsonIgnore
    public Collection<ExamFact> getExamFacts() {
        return examFacts;
    }

    public void setExamFacts(Collection<ExamFact> exams) {
        this.examFacts = examFacts;
    }

    @Override
    public String toString() {
        return this.getTitle();
    }
}
