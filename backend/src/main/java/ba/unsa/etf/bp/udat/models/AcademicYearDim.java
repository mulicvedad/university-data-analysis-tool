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
    private Integer startYear;
    private Collection<EnrollmentFact> enrollmentFacts;

    public AcademicYearDim(Integer academicYearId, String title, Integer active, Integer startYear) {
        this.academicYearId = academicYearId;
        this.title = title;
        this.active = active;
        this.startYear = startYear;
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
    public Integer getStartYear() {
        return startYear;
    }

    public void setStartYear(Integer startYear) {
        this.startYear = startYear;
    }

    @OneToMany(mappedBy = "academicYearDim")
    @JsonIgnore
    public Collection<EnrollmentFact> getEnrollmentFacts() {
        return enrollmentFacts;
    }

    public void setEnrollmentFacts(Collection<EnrollmentFact> enrollmentFacts) {
        this.enrollmentFacts = enrollmentFacts;
    }

    @OneToMany(mappedBy = "academicYearDim")
    @JsonIgnore
    public Collection<ExamFact> getExamFacts() {
        return examFacts;
    }

    public void setExamFacts(Collection<ExamFact> examFacts) {
        this.examFacts = examFacts;
    }

    private Collection<ExamFact> examFacts;

}
