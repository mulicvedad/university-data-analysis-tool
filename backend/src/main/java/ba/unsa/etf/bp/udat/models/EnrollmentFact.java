package ba.unsa.etf.bp.udat.models;
import org.hibernate.validator.constraints.Email;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;


@Entity
public class EnrollmentFact extends BaseModel{

    private AcademicYearDim academic_year_dim;
    private DepartmentDim department_dim;
    private SemesterDim semester_dim;
    private Boolean budget;
    private Boolean is_repeating;


    private Integer enrolled_count;

    public EnrollmentFact(AcademicYearDim academic_year_dim, DepartmentDim department_dim, SemesterDim semester_dim,
                          Boolean budget, Boolean is_repeating, Integer enrolled_count)
    {
        this.academic_year_dim = academic_year_dim;
        this.department_dim = department_dim;
        this.semester_dim = semester_dim;
        this.budget = budget;
        this.is_repeating = is_repeating;
        this.enrolled_count = enrolled_count;
    }

    public EnrollmentFact() {}


    @ManyToOne
    @JoinColumn(name = "academic_year_dim_id", referencedColumnName = "id", nullable = false)
    public AcademicYearDim getAcademic_year_dim() {return academic_year_dim;}

    public void setAcademic_year_dim(AcademicYearDim academic_year_dim) {this.academic_year_dim = academic_year_dim; }

    @ManyToOne
    @JoinColumn(name = "department_dim_id", referencedColumnName = "id", nullable = false)
    public DepartmentDim getDepartment_dim() {
        return department_dim;
    }

    public void setDepartment_dim(DepartmentDim department_dim) {
        this.department_dim = department_dim;
    }

    @ManyToOne
    @JoinColumn(name = "semester_dim_id", referencedColumnName = "id", nullable = false)
    public SemesterDim getSemester_dim() {
        return semester_dim;
    }

    public void setSemester_dim(SemesterDim semester_dim) {
        this.semester_dim = semester_dim;
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
    public Boolean getIs_repeating() {
        return is_repeating;
    }


    public void setIs_repeating(Boolean is_repeating) {
        this.is_repeating = is_repeating;
    }

    @Basic
    @Column(name = "enrolled_count", nullable = false)
    public Integer getEnrolled_count() {
        return enrolled_count;
    }

    public void setEnrolled_count(Integer enrolled_count) {
        this.enrolled_count = enrolled_count;
    }

}
