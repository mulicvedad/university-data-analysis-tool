package ba.unsa.etf.bp.udat;

import ba.unsa.etf.bp.udat.models.*;
import ba.unsa.etf.bp.udat.services.*;

import java.sql.*;
import java.util.ArrayList;

public class RemoteDb {
    static String user = "BP06";
    static String password = "YwdkiMb5";
    static String api = "jdbc";
    static String dbms = "oracle";
    static String driver = "thin";
    static String hostname = "80.65.65.66";
    static String port = "1521";
    static String sid = "ETFLAB";
    static String driverDefinition = "oracle.jdbc.OracleDriver";
    static Connection conn = null;
    //static Statement statement = null;
    //static ResultSet resultSet = null;
    static String connectionString = null;
    static String defaultSchema = "BP07";
    // Dodatni podaci
    //protected BasicDataSource dataSource;
    //protected int dbConnectionsMinCount = 1; // minimalan broj konekcija na BP
    //protected int dbConnectionsMaxCount = 5; // maksimalan broj konekcija na BP
    //protected int dbConnectionMaxWait = -1; // Maksimalno vrijeme cekanja za konekciju
    /***
     * Da bi korisnik uspjesno pokrenuo oracle driver potrebno je da sa Oracle-ove stranice skine
     * ojdbc8.jar (korisnik instalira verziju koja odgovara njegovoj verziji Jave na računaru
     *
     * Zatim potrebno je dodati dati file kao modul kroz File->Project Structure->Modules->Dependencies->+
     */
    public RemoteDb() {
        connectionString = api + ":" + dbms + ":" + driver + ":@" + hostname + ":" + port + ":" + sid;
    }

    public void loadDriver() {
        try
        {
            Class.forName(driverDefinition);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void connect() {
        try
        {
            if(conn != null)
            {
                System.out.println("Connection is not closed");
                return;
            }
            conn = DriverManager.getConnection(connectionString,user,password);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    public void disconnect() {
        try
        {
            if(conn == null)
            {
                System.out.println("There is no connection");
            }
            conn.close();
        }
        catch (SQLException var2)
        {
            var2.printStackTrace();
        }
    }

    public void setSchema() throws SQLException {
        conn.createStatement().executeQuery("ALTER SESSION SET CURRENT_SCHEMA =" + defaultSchema);
    }

    public void populateSemesterDim(String query, SemesterDimService service)
    {
        try
        {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next())
            {
                service.save(new SemesterDim(rs.getInt(1), rs.getString(2)));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public void populateDepartmentDim(String query, DepartmentDimService service)
    {
        try
        {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
           // DepartmentDim dd = new DepartmentDim(0,"Unknown");
           // dd.setId((long) 0);
           // service.save(dd);
            while (rs.next())
            {
                service.save(new DepartmentDim(rs.getInt(1), rs.getString(2)));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public void populateCourseDim(String query, CourseDimService service)
    {
        try
        {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
         //   service.save(new CourseDim(0));
            while (rs.next())
            {
                service.save(new CourseDim(rs.getInt(1), rs.getString(2)));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public void populateAcademicYearDim(String query, AcademicYearDimService service)
    {
        try
        {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next())
            {
                service.save(new AcademicYearDim(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4)));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public void populateTimeDim(String query, TimeDimService service)
    {
        try
        {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next())
            {
                service.save(new TimeDim(rs.getDate(1),rs.getInt(2),rs.getInt(3),
                        rs.getInt(4),rs.getInt(5),
                        rs.getString(6),rs.getString(7)));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public void populateLecturerDim(String query, LecturerDimService service)
    {
        try
        {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next())
            {
                service.save(new LecturerDim(rs.getInt(1),rs.getString(2),rs.getString(3),
                        rs.getBoolean(4),rs.getBigDecimal(5),
                        rs.getString(6)));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public void populateExamFact(String query, ExamFactService service, AcademicYearDimService academicYearDimService,
                                 TimeDimService timeDimService, SemesterDimService semesterDimService,
                                 DepartmentDimService departmentDimService, CourseDimService courseDimService)
    {
        try
        {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next())
            {
                AcademicYearDim ayd = academicYearDimService.findAcademicYearDim(rs.getInt(1));
                TimeDim td = timeDimService.findTimeDim(rs.getDate(2));
                SemesterDim sd = semesterDimService.findSemesterDim(rs.getInt(3));
                DepartmentDim dd = departmentDimService.findDepartmentDim(rs.getInt(4));
                CourseDim cd = courseDimService.findCourseDim(rs.getInt(5));
                service.save(new ExamFact(ayd, td, sd, dd, cd, rs.getBigDecimal(6),rs.getInt(7)));

            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public void populateEnrollmentFact(String query, EnrollmentFactService service, DepartmentDimService departmentDimService,
                                       AcademicYearDimService academicYearDimService, SemesterDimService semesterDimService)
    {
        try
        {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next())
            {
                DepartmentDim dd = departmentDimService.findDepartmentDim(rs.getInt(1));
                AcademicYearDim ayd = academicYearDimService.findAcademicYearDim(rs.getInt(2));
                SemesterDim sd = semesterDimService.findSemesterDim(rs.getInt(3));
                service.save(new EnrollmentFact(ayd, dd, sd, rs.getBoolean(4), rs.getBoolean(5), rs.getInt(6)));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public void populateAttendanceFact(String query, AttendanceFactService service, CourseDimService courseDimService,
                                       DepartmentDimService departmentDimService, TimeDimService timeDimService,
                                       LecturerDimService lecturerDimService)
    {
        try
        {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next())
            {
                DepartmentDim dd = departmentDimService.findDepartmentDim(rs.getInt(1));
                CourseDim cd = courseDimService.findCourseDim(rs.getInt(2));
                TimeDim td = timeDimService.findTimeDim(rs.getDate(3));
                LecturerDim ld = lecturerDimService.findLecturerDim(rs.getInt(4));
                service.save(new AttendanceFact(cd, dd, td, ld, rs.getInt(5), rs.getBigDecimal(6)));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }



}