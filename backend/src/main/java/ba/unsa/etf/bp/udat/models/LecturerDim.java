package ba.unsa.etf.bp.udat.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Collection;


@Entity
public class LecturerDim extends BaseModel{

    private Integer lecturer_zamger_user_id;
    private String first_name;
    private String last_name;
    private boolean is_student;
    private BigDecimal salary;
    private int gender;
    private Collection<AttendanceFact> attendance_facts;

    public LecturerDim(Integer lecturer_zamger_user_id, String first_name, String last_name,
                       boolean is_student, BigDecimal salary, int gender) {
        this.lecturer_zamger_user_id = lecturer_zamger_user_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.is_student = is_student;
        this.salary = salary;
        this.gender = gender;
    }
    public LecturerDim() {}

    @Basic
    @Column(name = "lecturer_zamger_user_id", nullable = false)
  //  @Size(max = 10)@NotNull
    public int getLecturer_zamger_user_id() {
        return lecturer_zamger_user_id;
    }

    public void setLecturer_zamger_user_id(int lecturer_zamger_user_id) {
        this.lecturer_zamger_user_id = lecturer_zamger_user_id;
    }

    @Basic
    @Column(name = "first_name", nullable = false)
  //  @Size(min = 2, max = 50)@NotNull
    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    @Basic
    @Column(name = "last_name", nullable = false)
   // @Size(min = 2, max = 50)@NotNull
    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    @Basic
    @Column(name = "is_student", nullable = false)
   // @NotNull
    public boolean getIs_student() {
        return is_student;
    }

    public void setIs_student(boolean is_student) {
        this.is_student = is_student;
    }


    @Basic
    @Column(name = "salary", nullable = false)
    @Min(0)
    @Max((long)99999999.99)
  //  @NotNull
    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Basic
    @Column(name = "gender", nullable = false)
    @Size(max = 10)
   // @NotNull
    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    @OneToMany(mappedBy = "lecturer_dim")
    @JsonIgnore
    public Collection<AttendanceFact> getAttendanceFacts() {
        return attendance_facts;
    }

    public void setAttendanceFacts(Collection<AttendanceFact> attendanceFacts) {
        this.attendance_facts = attendance_facts;
    }
}
