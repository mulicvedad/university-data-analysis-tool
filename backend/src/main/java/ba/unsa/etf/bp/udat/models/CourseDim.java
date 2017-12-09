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
public class CourseDim extends BaseModel{

    private int courseId;
    private String title;
    private Collection<ExamFact> examFacts;
    private Collection<AttendanceFact> attendanceFacts;

    public CourseDim(int courseId, String title) {
        this.courseId = courseId;
        this.title = title;
    }
    public CourseDim() {}

    @Basic
    @Column(name = "course_id", nullable = false)
   // @Size(max = 10) @NotNull
    public int getCourseId() {
        return courseId;
    }


    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Basic
    @Column(name = "title", nullable = false)
   // @Size(min = 4, max = 255) @NotNull
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @OneToMany(mappedBy = "course_dim")
    @JsonIgnore
    public Collection<ExamFact> getExamFacts() {
        return examFacts;
    }

    public void setExamFacts(Collection<ExamFact> exams) {
        this.examFacts = examFacts;
    }

    @OneToMany(mappedBy = "course_dim")
    @JsonIgnore
    public Collection<AttendanceFact> getAttendanceFacts() {
        return attendanceFacts;
    }

    public void setAttendanceFacts(Collection<AttendanceFact> attendanceFacts) {
        this.attendanceFacts = attendanceFacts;
    }
}
