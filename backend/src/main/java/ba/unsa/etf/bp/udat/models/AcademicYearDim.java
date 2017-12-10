package ba.unsa.etf.bp.udat.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
public class AcademicYearDim extends BaseModel{
    private Integer academicYearId;
    private String title;
    private Integer active;
    private Integer start_year;
    private Collection<EnrollmentFact> enrollmentFacts;

    public AcademicYearDim(Integer academicYearId, String title, Integer active, Integer start_year) {
        this.academicYearId = academicYearId;
        this.title = title;
        this.active = active;
        this.start_year = start_year;
    }
    public AcademicYearDim() {}

    @Basic
    @Column(name = "academic_year_id", nullable = false)
    public Integer getAcademicYearId() {
        return academicYearId;
    }

    public void setAcademicYearId(Integer academicYearId) {
        this.academicYearId = academicYearId;
    }

    @Basic
    @Column(name = "title", nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "active", nullable = false)
    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    @Basic
    @Column(name = "start_year", nullable = false)
    public Integer getStart_year() {
        return start_year;
    }

    public void setStart_year(Integer start_year) {
        this.start_year = start_year;
    }

    @OneToMany(mappedBy = "academic_year_dim")
    @JsonIgnore
    public Collection<EnrollmentFact> getEnrollmentFacts() {
        return enrollmentFacts;
    }

    public void setEnrollmentFacts(Collection<EnrollmentFact> enrollmentFacts) {
        this.enrollmentFacts = enrollmentFacts;
    }

    @OneToMany(mappedBy = "academic_year_dim")
    @JsonIgnore
    public Collection<ExamFact> getExamFacts() {
        return examFacts;
    }

    public void setExamFacts(Collection<ExamFact> examFacts) {
        this.examFacts = examFacts;
    }

    private Collection<ExamFact> examFacts;

}
