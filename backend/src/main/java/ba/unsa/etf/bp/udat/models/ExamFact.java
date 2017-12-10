package ba.unsa.etf.bp.udat.models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class ExamFact extends BaseModel {

    private AcademicYearDim academicYearDim;
    private TimeDim timeDim;
    private SemesterDim semesterDim;
    private DepartmentDim departmentDim;
    private CourseDim courseDim;
    private BigDecimal averagePoints;
    private Integer turnout;

    public ExamFact(AcademicYearDim academicYearDim, TimeDim timeDim, SemesterDim semesterDim,
                    DepartmentDim departmentDim, CourseDim courseDim, BigDecimal averagePoints, Integer turnout) {
        this.academicYearDim = academicYearDim;
        this.timeDim = timeDim;
        this.semesterDim = semesterDim;
        this.departmentDim = departmentDim;
        this.courseDim = courseDim;
        this.averagePoints = averagePoints;
        this.turnout = turnout;
    }
    public ExamFact() {}


    @ManyToOne
    @JoinColumn(name = "academic_year_dim_id", referencedColumnName = "id", nullable = false)
    public AcademicYearDim getAcademicYearDim() {return academicYearDim;}

    public void  setAcademicYearDim(AcademicYearDim academicYearDim) {this.academicYearDim = academicYearDim;}

    @ManyToOne
    @JoinColumn(name = "scheduled_date_dim_id", referencedColumnName = "id", nullable = false)
    public TimeDim getTimeDim() {
        return timeDim;
    }

    public void setTimeDim(TimeDim timeDim) {
        this.timeDim = timeDim;
    }

    @ManyToOne
    @JoinColumn(name = "semester_dim_id", referencedColumnName = "id", nullable = false)
    public SemesterDim getSemesterDim() {
        return semesterDim;
    }

    public void setSemesterDim(SemesterDim semesterDim) {
        this.semesterDim = semesterDim;
    }

    @ManyToOne
    @JoinColumn(name = "department_dim_id", referencedColumnName = "id", nullable = false)
    public DepartmentDim getDepartmentDim() {
        return departmentDim;
    }

    public void setDepartmentDim(DepartmentDim departmentDim) {
        this.departmentDim = departmentDim;
    }

    @ManyToOne
    @JoinColumn(name = "course_dim_id", referencedColumnName = "id", nullable = false)
    public CourseDim getCourseDim() {
        return courseDim;
    }

    public void setCourseDim(CourseDim courseDim) {
        this.courseDim = courseDim;
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
