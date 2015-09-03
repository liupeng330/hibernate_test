package model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by peng on 9/4/15.
 */
@Entity
@Table(name = "person", schema = "", catalog = "contactdb")
public class PersonEntity
{
    private Integer id;
    private String name;
    private Integer age;
    private Collection<AddressEntity> addressesById;

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
    @Column(name = "name")
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Basic
    @Column(name = "age")
    public Integer getAge()
    {
        return age;
    }

    public void setAge(Integer age)
    {
        this.age = age;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonEntity that = (PersonEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (age != null ? !age.equals(that.age) : that.age != null) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "personByPersonId")
    public Collection<AddressEntity> getAddressesById()
    {
        return addressesById;
    }

    public void setAddressesById(Collection<AddressEntity> addressesById)
    {
        this.addressesById = addressesById;
    }
}
