package ba.unsa.etf.bp.udat.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Date;

/**
 * Created by Edin on 12.11.2017..
 */
public class DateDim extends BaseModel{

    private Date date;
    private int year;
    private int monthOfYear;
    private int dayOfMonth;
    private int month;
    private int dayOfWeek;
    private Collection<ExamFact> examFacts;
    private Collection<ClassFact> classFacts;
    private Collection<StudentFact> enrolledStudents;
    private Collection<StudentFact> dissrolledStudents;
    private Collection<StudentFact> graduatedStudents;

    public DateDim(Date date, int year, int monthOfYear, int dayOfMonth, int month, int dayOfWeek) {
        this.date = date;
        this.year = year;
        this.monthOfYear = monthOfYear;
        this.dayOfMonth = dayOfMonth;
        this.month = month;
        this.dayOfWeek = dayOfWeek;
    }
    public DateDim() {}

    @Basic
    @Column(name = "date", nullable = false)
    @NotNull
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "year", nullable = false)
    @Size(max = 10)@NotNull
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Basic
    @Column(name = "month_of_year", nullable = false)
    @Size(max = 10)@NotNull
    public int getMonthOfYear() {
        return monthOfYear;
    }

    public void setMonthOfYear(int monthOfYear) {
        this.monthOfYear = monthOfYear;
    }

    @Basic
    @Column(name = "day_of_month", nullable = false)
    @Size(max = 10)@NotNull
    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    @Basic
    @Column(name = "month", nullable = false)
    @Size(max = 10)@NotNull
    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    @Basic
    @Column(name = "day_of_week", nullable = false)
    @Size(max = 10)@NotNull
    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    @OneToMany(mappedBy = "date_dim")
    @JsonIgnore
    public Collection<ExamFact> getExamFacts() {
        return examFacts;
    }

    public void setExamFacts(Collection<ExamFact> examFacts) {
        this.examFacts = examFacts;
    }

    @OneToMany(mappedBy = "date_dim")
    @JsonIgnore
    public Collection<ClassFact> getClassFacts() {
        return classFacts;
    }

    public void setClassFacts(Collection<ClassFact> classFacts) {
        this.classFacts = classFacts;
    }

    // Moguci bugovi
    @OneToMany(mappedBy = "enrollmentDate")
    @JsonIgnore
    public Collection<StudentFact> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void setEnrolledStudents(Collection<StudentFact> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }

    @OneToMany(mappedBy = "dissrollmentDate")
    @JsonIgnore
    public Collection<StudentFact> getDissrolledStudents() {
        return dissrolledStudents;
    }

    public void setDissrolledStudents(Collection<StudentFact> dissrolledStudents) {
        this.dissrolledStudents = dissrolledStudents;
    }

    @OneToMany(mappedBy = "graduationDate")
    @JsonIgnore
    public Collection<StudentFact> getGraduatedStudents() {
        return graduatedStudents;
    }

    public void setGraduatedStudents(Collection<StudentFact> graduatedStudents) {
        this.graduatedStudents = graduatedStudents;
    }
}
