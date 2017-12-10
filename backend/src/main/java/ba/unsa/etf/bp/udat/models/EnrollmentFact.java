package ba.unsa.etf.bp.udat.models;
import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
public class EnrollmentFact extends BaseModel{

    private AcademicYearDim academicYearDim;
    private DepartmentDim departmentDim;
    private SemesterDim semesterDim;
    private Boolean budget;
    private Boolean isRepeating;
    private Integer enrolledCount;

    public EnrollmentFact(AcademicYearDim academicYearDim, DepartmentDim departmentDim, SemesterDim semesterDim,
                          Boolean budget, Boolean isRepeating, Integer enrolledCount)
    {
        this.academicYearDim = academicYearDim;
        this.departmentDim = departmentDim;
        this.semesterDim = semesterDim;
        this.budget = budget;
        this.isRepeating = isRepeating;
        this.enrolledCount = enrolledCount;
    }

    public EnrollmentFact() {}


    @ManyToOne
    @JoinColumn(name = "academic_year_dim_id", referencedColumnName = "id", nullable = false)
    public AcademicYearDim getAcademicYearDim() {return academicYearDim;}

    public void setAcademicYearDim(AcademicYearDim academicYearDim) {this.academicYearDim = academicYearDim; }

    @ManyToOne
    @JoinColumn(name = "department_dim_id", referencedColumnName = "id", nullable = false)
    public DepartmentDim getDepartmentDim() {
        return departmentDim;
    }

    public void setDepartmentDim(DepartmentDim departmentDim) {
        this.departmentDim = departmentDim;
    }

    @ManyToOne
    @JoinColumn(name = "semester_dim_id", referencedColumnName = "id", nullable = false)
    public SemesterDim getSemesterDim() {
        return semesterDim;
    }

    public void setSemesterDim(SemesterDim semesterDim) {
        this.semesterDim = semesterDim;
    }

    @Basic
    @Column(name = "budget", nullable = false)
    @NotNull
    public Boolean isBudget() {
        return budget;
    }

    public void setBudget(Boolean budget) {
        this.budget = budget;
    }

    @Basic
    @Column(name = "is_repeating", nullable = false)
    public Boolean getIsRepeating() {
        return isRepeating;
    }


    public void setIsRepeating(Boolean isRepeating) {
        this.isRepeating = isRepeating;
    }

    @Basic
    @Column(name = "enrolled_count", nullable = false)
    public Integer getEnrolledCount() {
        return enrolledCount;
    }

    public void setEnrolledCount(Integer enrolledCount) {
        this.enrolledCount = enrolledCount;
    }

}
