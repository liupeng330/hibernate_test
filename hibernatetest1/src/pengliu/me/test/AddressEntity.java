package pengliu.me.test;

import javax.persistence.*;

/**
 * Created by peng on 8/9/15.
 */
@Entity
@Table(name = "address", schema = "", catalog = "contactdb")
public class AddressEntity
{
    private int id;
    private String detail;
    private int personId;
    private PersonEntity personByPersonId;

    @Id
    @Column(name = "id")
    @GeneratedValue
    public int getId()
    {
        return id;
    }

    public void setId(int id)
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

    @Basic
    @Column(name = "personId")
    public int getPersonId()
    {
        return personId;
    }

    public void setPersonId(int personId)
    {
        this.personId = personId;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AddressEntity that = (AddressEntity) o;

        if (id != that.id) return false;
        if (personId != that.personId) return false;
        if (detail != null ? !detail.equals(that.detail) : that.detail != null) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = id;
        result = 31 * result + (detail != null ? detail.hashCode() : 0);
        result = 31 * result + personId;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "personId", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public PersonEntity getPersonByPersonId()
    {
        return personByPersonId;
    }

    public void setPersonByPersonId(PersonEntity personByPersonId)
    {
        this.personByPersonId = personByPersonId;
    }
}
