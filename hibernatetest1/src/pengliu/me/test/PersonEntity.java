package pengliu.me.test;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by peng on 8/9/15.
 */
@Entity
@Table(name = "person", schema = "", catalog = "contactdb")
public class PersonEntity
{
    private int id;
    private String name;
    private int age;
    private Collection<AddressEntity> addressesById;

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
    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonEntity that = (PersonEntity) o;

        if (id != that.id) return false;
        if (age != that.age) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + age;
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
