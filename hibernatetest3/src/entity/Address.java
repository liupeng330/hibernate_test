package entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by peng on 8/10/15.
 */
@Entity
@Table(name="address_inf")
public class Address
{
    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int addressId;

    @Basic
    @Column(name = "address_detail")
    private String addressDetail;

    @ManyToMany(targetEntity = Person.class)
    @JoinTable(
            name="person_address",
            joinColumns = @JoinColumn(name = "address_id", referencedColumnName = "address_id"),
            inverseJoinColumns = @JoinColumn(name="person_id",referencedColumnName = "person_id"))
    private Set<Person> persons = new HashSet<>();

    public int getAddressId()
    {
        return addressId;
    }

    public void setAddressId(int addressId)
    {
        this.addressId = addressId;
    }

    public String getAddressDetail()
    {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail)
    {
        this.addressDetail = addressDetail;
    }

    public Set<Person> getPersons()
    {
        return persons;
    }

    public void setPersons(Set<Person> persons)
    {
        this.persons = persons;
    }
}
