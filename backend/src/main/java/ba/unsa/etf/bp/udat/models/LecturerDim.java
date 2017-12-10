package ba.unsa.etf.bp.udat.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.Collection;


@Entity
public class LecturerDim extends BaseModel{

    private Integer lecturerZamgerUserId;
    private String firstName;
    private String lastName;
    private Boolean isStudent;
    private BigDecimal salary;
    private String gender;
    private Collection<AttendanceFact> attendance_facts;

    public LecturerDim(Integer lecturerZamgerUserId, String firstName, String lastName,
                       Boolean isStudent, BigDecimal salary, String gender) {
        this.lecturerZamgerUserId = lecturerZamgerUserId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isStudent = isStudent;
        this.salary = salary;
        this.gender = gender;
    }
    public LecturerDim() {}

    @Basic
    @Column(name = "lecturer_zamger_user_id", nullable = false)
  //  @Size(max = 10)@NotNull
    public Integer getLecturerZamgerUserId() {
        return lecturerZamgerUserId;
    }

    public void setLecturerZamgerUserId(Integer lecturerZamgerUserId) {
        this.lecturerZamgerUserId = lecturerZamgerUserId;
    }

    @Basic
    @Column(name = "first_name", nullable = false)
  //  @Size(min = 2, max = 50)@NotNull
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name", nullable = false)
   // @Size(min = 2, max = 50)@NotNull
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "is_student", nullable = false)
   // @NotNull
    public Boolean getIsStudent() {
        return isStudent;
    }

    public void setIsStudent(Boolean isStudent) {
        this.isStudent = isStudent;
    }

    @Basic
    @Column(name = "salary",precision = 19, scale = 2)
    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Basic
    @Column(name = "gender", nullable = false)
   // @NotNull
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @OneToMany(mappedBy = "lecturerDim")
    @JsonIgnore
    public Collection<AttendanceFact> getAttendanceFacts() {
        return attendance_facts;
    }

    public void setAttendanceFacts(Collection<AttendanceFact> attendanceFacts) {
        this.attendance_facts = attendance_facts;
    }
}
