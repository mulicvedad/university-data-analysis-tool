package ba.unsa.etf.bp.udat.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

/**
 * Created by Edin on 12.11.2017..
 */
@Entity
public class CourseDim extends BaseModel{

    private int courseId;
    private String title;
    private String department;
    private int studyYear;
    private Collection<ExamFact> exams;
    private Collection<ClassFact> classFacts;

    public CourseDim(int courseId, String title, String department, int studyYear) {
        this.courseId = courseId;
        this.title = title;
        this.department = department;
        this.studyYear = studyYear;
    }
    public CourseDim() {}

    @Basic
    @Column(name = "course_id", nullable = false)
    @Size(max = 10) @NotNull
    public int getCourseId() {
        return courseId;
    }


    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Basic
    @Column(name = "title", nullable = false)
    @Size(min = 4, max = 255) @NotNull
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "department", nullable = false)
    @Size(min = 4, max = 255) @NotNull
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Basic
    @Column(name = "study_year", nullable = false)
    @Size(max = 10) @NotNull
    public int getStudyYear() {
        return studyYear;
    }

    public void setStudyYear(int studyYear) {
        this.studyYear = studyYear;
    }

    @OneToMany(mappedBy = "course_dim")
    @JsonIgnore
    public Collection<ExamFact> getExams() {
        return exams;
    }

    public void setExams(Collection<ExamFact> exams) {
        this.exams = exams;
    }

    @OneToMany(mappedBy = "course_dim")
    @JsonIgnore
    public Collection<ClassFact> getClassFacts() {
        return classFacts;
    }

    public void setClassFacts(Collection<ClassFact> classFacts) {
        this.classFacts = classFacts;
    }
}
