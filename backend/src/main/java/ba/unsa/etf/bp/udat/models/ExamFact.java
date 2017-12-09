package ba.unsa.etf.bp.udat.models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class ExamFact extends BaseModel {

    private AcademicYearDim academic_year_dim;
    private TimeDim time_dim;
    private SemesterDim semester_dim;
    private DepartmentDim department_dim;
    private CourseDim course_dim;
    private BigDecimal averagePoints;
    private Integer turnout;

    public ExamFact(AcademicYearDim academic_year_dim, TimeDim time_dim, SemesterDim semester_dim,
                    DepartmentDim department_dim, CourseDim course_dim, BigDecimal averagePoints, Integer turnout) {
        this.academic_year_dim = academic_year_dim;
        this.time_dim = time_dim;
        this.semester_dim = semester_dim;
        this.department_dim = department_dim;
        this.course_dim = course_dim;
        this.averagePoints = averagePoints;
        this.turnout = turnout;
    }
    public ExamFact() {}


    @ManyToOne
    @JoinColumn(name = "academic_year_dim_id", referencedColumnName = "id", nullable = false)
    public AcademicYearDim getAcademic_year_dim() {return academic_year_dim;}

    public void  setAcademic_year_dim(AcademicYearDim academic_year_dim) {this.academic_year_dim = academic_year_dim;}

    @ManyToOne
    @JoinColumn(name = "scheduled_date_dim_id", referencedColumnName = "id", nullable = false)
    public TimeDim getTime_dim() {
        return time_dim;
    }

    public void setTime_dim(TimeDim time_dim) {
        this.time_dim = time_dim;
    }

    @ManyToOne
    @JoinColumn(name = "semester_dim_id", referencedColumnName = "id", nullable = false)
    public SemesterDim getSemester_dim() {
        return semester_dim;
    }

    public void setSemester_dim(SemesterDim semester_dim) {
        this.semester_dim = semester_dim;
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
    @JoinColumn(name = "course_dim_id", referencedColumnName = "id", nullable = false)
    public CourseDim getCourse_dim() {
        return course_dim;
    }

    public void setCourse_dim(CourseDim course_dim) {
        this.course_dim = course_dim;
    }

    @Basic
    @Column(name = "average_points", nullable = false)
    @Min(0)
    @Max(100) @NotNull
    public BigDecimal getAveragePoints() {
        return averagePoints;
    }

    public void setAveragePoints(BigDecimal averagePoints) {
        this.averagePoints = averagePoints;
    }

    @Basic
    @Column(name = "turnout", nullable = false)
    public Integer getTurnout() {
        return turnout;
    }

    public void setTurnout(Integer turnout) {
        this.turnout = turnout;
    }
}
