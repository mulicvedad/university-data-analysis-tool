package ba.unsa.etf.bp.udat.models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * Created by Edin on 12.11.2017..
 */
public class ExamFact extends BaseModel {

    private int examId;
    private DateDim scheduledDate;
    private SemesterDim semester;
    private DepartmentDim department;
    private CourseDim course;
    private BigDecimal averagePoints;
    private int turnout;

    public ExamFact(int examId, DateDim scheduledDate, SemesterDim semester,
                    DepartmentDim department, CourseDim course, BigDecimal averagePoints, int turnout) {
        this.examId = examId;
        this.scheduledDate = scheduledDate;
        this.semester = semester;
        this.department = department;
        this.course = course;
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
    public DateDim getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(DateDim scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    @ManyToOne
    @JoinColumn(name = "semester_dim_id", referencedColumnName = "id", nullable = false)
    public SemesterDim getSemester() {
        return semester;
    }

    public void setSemester(SemesterDim semester) {
        this.semester = semester;
    }

    @ManyToOne
    @JoinColumn(name = "department_dim_id", referencedColumnName = "id", nullable = false)
    public DepartmentDim getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentDim department) {
        this.department = department;
    }

    @ManyToOne
    @JoinColumn(name = "course_dim_id", referencedColumnName = "id", nullable = false)
    public CourseDim getCourse() {
        return course;
    }

    public void setCourse(CourseDim course) {
        this.course = course;
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
