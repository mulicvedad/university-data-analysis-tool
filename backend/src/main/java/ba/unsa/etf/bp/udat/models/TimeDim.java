package ba.unsa.etf.bp.udat.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Date;

@Entity
public class TimeDim extends BaseModel{

    private Date full_date;
    private Integer year;
    private Integer monthOfYear;
    private Integer dayOfMonth;
    private Integer hour;
    private String month;
    private String dayOfWeek;
    private Collection<ExamFact> examFacts;
    private Collection<AttendanceFact> attendanceFacts;

    public TimeDim(Date full_date, Integer year, Integer monthOfYear, Integer dayOfMonth, Integer hour, String month, String dayOfWeek) {
        this.full_date = full_date;
        this.year = year;
        this.monthOfYear = monthOfYear;
        this.dayOfMonth = dayOfMonth;
        this.hour = hour;
        this.month = month;
        this.dayOfWeek = dayOfWeek;
    }
    public TimeDim() {}

    @Basic
    @Column(name = "full_date", nullable = false)
    @NotNull
    public Date getFull_date() {
        return full_date;
    }

    public void setFull_date(Date date) {
        this.full_date = full_date;
    }

    @Basic
    @Column(name = "year", nullable = false)
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Basic
    @Column(name = "month_of_year", nullable = false)
    public int getMonthOfYear() {
        return monthOfYear;
    }

    public void setMonthOfYear(int monthOfYear) {
        this.monthOfYear = monthOfYear;
    }

    @Basic
    @Column(name = "day_of_month", nullable = false)
    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    @Basic
    @Column(name = "month", nullable = false)
    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    @Basic
    @Column(name = "hour", nullable = false)
    public Integer getHour() { return hour; }

    public void setHour(Integer hour) { this.hour = hour; }
    @Basic
    @Column(name = "day_of_week", nullable = false)
    public String getDayOfWeek() { return dayOfWeek; }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    @OneToMany(mappedBy = "time_dim")
    @JsonIgnore
    public Collection<ExamFact> getExamFacts() {
        return examFacts;
    }

    public void setExamFacts(Collection<ExamFact> examFacts) {
        this.examFacts = examFacts;
    }

    @OneToMany(mappedBy = "time_dim")
    @JsonIgnore
    public Collection<AttendanceFact> getAttendanceFacts() {
        return attendanceFacts;
    }

    public void setAttendanceFacts(Collection<AttendanceFact> attendanceFacts) {
        this.attendanceFacts = attendanceFacts;
    }
}
