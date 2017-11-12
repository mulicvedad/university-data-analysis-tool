package ba.unsa.etf.bp.udat.models;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Edin on 12.11.2017..
 */
@Entity
public class StudentFact extends BaseModel{

    private int studentUserId;
    private String jmbg;
    private String firstName;
    private String lastName;
    private String email;
    private String adress;
    private String username;
    private int gender;
    private Date birthdate;
    private LabGroupDim labGroupDim;
    private DateDim enrollmentDate;
    private DateDim dissrollmentDate;
    private DepartmentDim departmentDim;
    private DateDim graduationDate;
    private SemesterDim semesterDim;
    private int studyYear;
    private boolean budget;
    private BigDecimal averageGrade;

    public StudentFact(int studentUserId, String jmbg, String firstName, String lastName,
                       String email, String adress, String username, int gender,
                       Date birthdate, LabGroupDim labGroupDim, DateDim enrollmentDate, DateDim dissrollmentDate,
                       DepartmentDim departmentDim, DateDim graduationDate, SemesterDim semesterDim,
                       int studyYear, boolean budget, BigDecimal averageGrade)
    {
        this.studentUserId = studentUserId;
        this.jmbg = jmbg;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.adress = adress;
        this.username = username;
        this.gender = gender;
        this.birthdate = birthdate;
        this.labGroupDim = labGroupDim;
        this.enrollmentDate = enrollmentDate;
        this.dissrollmentDate = dissrollmentDate;
        this.departmentDim = departmentDim;
        this.graduationDate = graduationDate;
        this.semesterDim = semesterDim;
        this.studyYear = studyYear;
        this.budget = budget;
        this.averageGrade = averageGrade;
    }

    public StudentFact() {}

    @Basic
    @Column(name = "student_user_id", nullable = false)
    @Size(min = 0, max = 10)
    public int getStudentUserId() {
        return studentUserId;
    }

    public void setStudentUserId(int studentUserId) {
        this.studentUserId = studentUserId;
    }

    @Basic
    @Column(name = "jmbg", nullable = false)
    @Size(min = 4, max = 50)@NotNull
    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    @Basic
    @Column(name = "first_name", nullable = false)
    @Size(min = 4, max = 50)@NotNull
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name", nullable = false)
    @Size(min = 4, max = 50)@NotNull
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "email", nullable = false)
    @Email @Size(max = 64)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "adress", nullable = false)
    @Size(min = 4, max = 128)
    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    @Basic
    @Column(name = "username", nullable = false)
    @Size(min = 4, max = 64)@NotNull
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
    @ManyToOne
    @JoinColumn(name = "lab_group_dim_id", referencedColumnName = "id", nullable = false)
    public LabGroupDim getLabGroupDim() {
        return labGroupDim;
    }

    public void setLabGroupDim(LabGroupDim labGroupDim) {
        this.labGroupDim = labGroupDim;
    }

    @ManyToOne
    @JoinColumn(name = "enrollment_date_dim_id", referencedColumnName = "id", nullable = false)
    public DateDim getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(DateDim enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    @ManyToOne
    @JoinColumn(name = "dissrollment_date_dim_id", referencedColumnName = "id", nullable = false)
    public DateDim getDissrollmentDate() {
        return dissrollmentDate;
    }

    public void setDissrollmentDate(DateDim dissrollmentDate) {
        this.dissrollmentDate = dissrollmentDate;
    }

    @ManyToOne
    @JoinColumn(name = "departmend_dim_id", referencedColumnName = "id", nullable = false)
    public DepartmentDim getDepartmentDim() {
        return departmentDim;
    }

    public void setDepartmentDim(DepartmentDim departmentDim) {
        this.departmentDim = departmentDim;
    }

    @ManyToOne
    @JoinColumn(name = "graduation_date_dim_id", referencedColumnName = "id", nullable = false)
    public DateDim getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(DateDim graduationDate) {
        this.graduationDate = graduationDate;
    }

    @ManyToOne
    @JoinColumn(name = "current_semester_dim_id", referencedColumnName = "id", nullable = false)
    public SemesterDim getSemesterDim() {
        return semesterDim;
    }

    public void setSemesterDim(SemesterDim semesterDim) {
        this.semesterDim = semesterDim;
    }

    @Basic
    @Column(name = "study_year", nullable = false)
    @Size(max = 10)@NotNull
    public int getStudyYear() {
        return studyYear;
    }

    public void setStudyYear(int studyYear) {
        this.studyYear = studyYear;
    }

    @Basic
    @Column(name = "budget", nullable = false)
    @NotNull
    public boolean isBudget() {
        return budget;
    }

    public void setBudget(boolean budget) {
        this.budget = budget;
    }

    @Basic
    @Column(name = "average_grade", nullable = false)
    @Min(0)
    @Max(10) @NotNull
    public BigDecimal getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(BigDecimal averageGrade) {
        this.averageGrade = averageGrade;
    }

    @Basic
    @Column(name = "birthdate", nullable = false)
    @Size(max = 50)@NotNull
    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
}
