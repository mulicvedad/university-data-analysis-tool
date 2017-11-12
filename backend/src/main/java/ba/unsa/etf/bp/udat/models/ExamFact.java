package ba.unsa.etf.bp.udat.models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * Created by Edin on 12.11.2017..
 */
@Entity
public class ExamFact extends BaseModel {

    private int examId;
    private DateDim date_dim;
    private SemesterDim semester_dim;
    private DepartmentDim department_dim;
    private CourseDim course_dim;
    private BigDecimal averagePoints;
    private int turnout;

    public ExamFact(int examId, DateDim date_dim, SemesterDim semester_dim,
                    DepartmentDim department_dim, CourseDim course_dim, BigDecimal averagePoints, int turnout) {
        this.examId = examId;
        this.date_dim = date_dim;
        this.semester_dim = semester_dim;
        this.department_dim = department_dim;
        this.course_dim = course_dim;
        this.averagePoints = averagePoints;
        this.turnout = turnout;
    }
    public ExamFact() {}

    @Basic
    @Column(name = "exam_id", nullable = false)
    @Size(max = 10) @NotNull
    public int getExamId() {
        return examId;
    }


    public void setExamId(int examId) {
        this.examId = examId;
    }

    @ManyToOne
    @JoinColumn(name = "scheduled_date_dim_id", referencedColumnName = "id", nullable = false)
    public DateDim getDate_dim() {
        return date_dim;
    }

    public void setDate_dim(DateDim date_dim) {
        this.date_dim = date_dim;
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
    @Size(max = 10) @NotNull
    public int getTurnout() {
        return turnout;
    }

    public void setTurnout(int turnout) {
        this.turnout = turnout;
    }
}
