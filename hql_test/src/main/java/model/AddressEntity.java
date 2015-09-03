package model;

import javax.persistence.*;

/**
 * Created by peng on 9/4/15.
 */
@Entity
@Table(name = "address", schema = "", catalog = "contactdb")
public class AddressEntity
{
    private Integer id;
    private String detail;
    private PersonEntity personByPersonId;

    @Id
    @Column(name = "id")
    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    @Basic
    @Column(name = "detail")
    public String getDetail()
    {
        return detail;
    }

    public void setDetail(String detail)
    {
        this.detail = detail;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AddressEntity that = (AddressEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (detail != null ? !detail.equals(that.detail) : that.detail != null) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (detail != null ? detail.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "personId", referencedColumnName = "id", nullable = false)
    public PersonEntity getPersonByPersonId()
    {
        return personByPersonId;
    }

    public void setPersonByPersonId(PersonEntity personByPersonId)
    {
        this.personByPersonId = personByPersonId;
    }
}
