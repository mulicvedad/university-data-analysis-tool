package ba.unsa.etf.bp.udat.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Date;

@Entity
public class SemesterDim extends BaseModel {

    private Integer semesterId;
    private String title;
    private Collection<EnrollmentFact> enrollmentFacts;
    private Collection<ExamFact> examFacts;

    public SemesterDim(Integer semesterId, String title) {
        this.semesterId = semesterId;
        this.title = title;
    }
    public SemesterDim() {}

    @Basic
    @Column(name = "semester_id", nullable = false)
    public Integer getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(Integer semesterId) {
        this.semesterId = semesterId;
    }

    @Basic
    @Column(name = "title", nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @OneToMany(mappedBy = "semesterDim")
    @JsonIgnore
    public Collection<EnrollmentFact> getEnrollmentFacts() {
        return enrollmentFacts;
    }

    public void setEnrollmentFacts(Collection<EnrollmentFact> students) {
        this.enrollmentFacts = enrollmentFacts;
    }

    @OneToMany(mappedBy = "semesterDim")
    @JsonIgnore
    public Collection<ExamFact> getExamFacts() {
        return examFacts;
    }

    public void setExamFacts(Collection<ExamFact> exams) {
        this.examFacts = examFacts;
    }
}
