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

/**
 * Created by Edin on 12.11.2017..
 */
@Entity
public class SemesterDim extends BaseModel {

    private int semesterId;
    private String title;
    private Date startDate;
    private Date endDate;
    private Collection<StudentFact> students;
    private Collection<ExamFact> exams;

    public SemesterDim(int semesterId, String title, Date startDate, Date endDate) {
        this.semesterId = semesterId;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public SemesterDim() {}

    @Basic
    @Column(name = "semester_id", nullable = false)
    @Size(max = 10)@NotNull
    public int getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(int semesterId) {
        this.semesterId = semesterId;
    }

    @Basic
    @Column(name = "title", nullable = false)
    @Size(min = 4, max = 255)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "start_date", nullable = false)
    @NotNull
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }


    @Basic
    @Column(name = "end_date", nullable = false)
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @OneToMany(mappedBy = "semester_dim")
    @JsonIgnore
    public Collection<StudentFact> getStudents() {
        return students;
    }

    public void setStudents(Collection<StudentFact> students) {
        this.students = students;
    }

    @OneToMany(mappedBy = "semester_dim")
    @JsonIgnore
    public Collection<ExamFact> getExams() {
        return exams;
    }

    public void setExams(Collection<ExamFact> exams) {
        this.exams = exams;
    }
}
