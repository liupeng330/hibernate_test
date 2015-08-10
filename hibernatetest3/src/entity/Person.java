package entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="person_inf")
public class Person
{
    @Id
    @Column(name="person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic
    @Column(name = "person_name")
    private String name;

    @Basic
    @Column(name = "person_age")
    private Integer age;

    @ManyToMany(targetEntity = Address.class)
    @JoinTable(
            name="person_address",
            joinColumns = @JoinColumn(name="person_id",referencedColumnName = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id", referencedColumnName = "address_id"))
    private Set<Address> addresses = new HashSet<>();

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Integer getAge()
    {
        return age;
    }

    public void setAge(Integer age)
    {
        this.age = age;
    }

    public Set<Address> getAddresses()
    {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses)
    {
        this.addresses = addresses;
    }
}
