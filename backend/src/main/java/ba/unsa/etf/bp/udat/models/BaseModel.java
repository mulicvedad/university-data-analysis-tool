package ba.unsa.etf.bp.udat.models;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created by kalashnikov on 11.11.2017..
 */
@MappedSuperclass
public class BaseModel {
    private Long id;
    @Id
    @GeneratedValue
    @Column(name = "id")
    public Long getId()
    {
        return id;
    }
    public void setId(Long id)
    {
        this.id = id;
    }
}
