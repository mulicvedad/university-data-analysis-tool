package ba.unsa.etf.bp.udat.models;

import java.sql.Timestamp;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name="import_time")
public class ImportTime extends BaseModel {

    String tableName;
    Timestamp timeOfImport;

    public ImportTime(String tableName, Timestamp timeOfImport)
    {
        this.tableName = tableName;
        this.timeOfImport = timeOfImport;
    }
    public ImportTime(){}

    @Basic
    @Column(name = "name_of_table")
    public String getTableName()
    {
        return this.tableName;
    }
    public void setTableName(String tableName)
    {
        this.tableName = tableName;
    }

    @Basic
    @Column(name = "time_of_import")
    public Timestamp getTimeOfImport()
    {
        return this.timeOfImport;
    }
    public void setTimeOfImport(Timestamp timeOfImport)
    {
        this.timeOfImport = timeOfImport;
    }
    

}