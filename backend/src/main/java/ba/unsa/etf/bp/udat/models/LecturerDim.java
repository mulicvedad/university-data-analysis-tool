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

/**
 * Created by Edin on 12.11.2017..
 */
@Entity
public class LecturerDim extends BaseModel{

    private int lecturerZamgerUserId;
    private String firstName;
    private String lastName;
    private boolean isStudent;
    private BigDecimal salary;
    private int gender;
    private Collection<ClassFact> classes;

    public LecturerDim(int lecturerZamgerUserId, String firstName, String lastName,
                       boolean isStudent, BigDecimal salary, int gender) {
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
    @Size(max = 10)@NotNull
    public int getLecturerZamgerUserId() {
        return lecturerZamgerUserId;
    }

    public void setLecturerZamgerUserId(int lecturerZamgerUserId) {
        this.lecturerZamgerUserId = lecturerZamgerUserId;
    }

    @Basic
    @Column(name = "first_name", nullable = false)
    @Size(min = 2, max = 50)@NotNull
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name", nullable = false)
    @Size(min = 2, max = 50)@NotNull
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "is_student", nullable = false)
    @NotNull
    public boolean isStudent() {
        return isStudent;
    }

    public void setStudent(boolean student) {
        isStudent = student;
    }


    @Basic
    @Column(name = "salary", nullable = false)
    @Min(0)
    @Max((long)99999999.99)
    @NotNull
    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Basic
    @Column(name = "gender", nullable = false)
    @Size(max = 10)@NotNull
    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    @OneToMany(mappedBy = "lecturer_dim")
    @JsonIgnore
    public Collection<ClassFact> getClasses() {
        return classes;
    }

    public void setClasses(Collection<ClassFact> classes) {
        this.classes = classes;
    }
}
