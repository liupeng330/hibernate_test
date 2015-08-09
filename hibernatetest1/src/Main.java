import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.mapping.Collection;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.type.StandardBasicTypes;
import pengliu.me.test.AddressEntity;
import pengliu.me.test.PersonEntity;
import pengliu.me.test.HibernateUtil;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Main
{
    public static void main(final String[] args) throws Exception
    {
        deleteFrom();
    }

    private static void insertOneEntry()
    {
        final Session session = HibernateUtil.currentSession();
        Transaction tx = session.beginTransaction();
        try
        {
            //add related  entries
            PersonEntity person = new PersonEntity();
            person.setName("IIooliupen111");
            person.setAge(113);
            session.save(person);

            AddressEntity address = new AddressEntity();
            address.setDetail("111111detail from address");
            address.setPersonId(person.getId());
            session.save(address);
            tx.commit();
        }
        finally
        {
            HibernateUtil.closeSession();
        }
    }

    private static void scalarQuery()
    {
        Session session = HibernateUtil.currentSession();
        try
        {
            Transaction tx = session.beginTransaction();
            String sqlString = "select * from contact";
            List list = session.createSQLQuery(sqlString)
                    //Only print column 'name' and 'address'
                    .addScalar("name", StandardBasicTypes.STRING)
                    .addScalar("address", StandardBasicTypes.STRING)
                    .list();
            for(Object els: list)
            {
                Object[] row = (Object[])els;
                System.out.println(row[0] +"--"+ row[1]);
            }
            tx.commit();
        }
        finally
        {
            HibernateUtil.closeSession();
        }
    }

    private static void searchByRelatedTable()
    {
        final Session session = HibernateUtil.currentSession();
        try
        {
            List list = session.createSQLQuery("select * from person where name=:name")
                    .addEntity(PersonEntity.class)
                    .setString("name", "liuminghe")
                    .list();
            PersonEntity person = (PersonEntity)list.toArray()[0];
            List<AddressEntity> addresses = (List<AddressEntity>)person.getAddressesById();
            for(AddressEntity add: addresses)
            {
                System.out.println(add.getId()+ "--" + add.getDetail());
            }
        }
        finally
        {
            HibernateUtil.closeSession();
        }
    }

    private static void deleteFrom()
    {
        Session session = HibernateUtil.currentSession();
        try
        {
            Transaction tx = session.beginTransaction();
            int updatedCount = session.createSQLQuery("delete from person where id=:id")
                    .setInteger("id", 13)
                    .executeUpdate();
            tx.commit();
        }
        finally
        {
            HibernateUtil.closeSession();
        }
    }

//    private static void entityQuery()
//    {
//        Session session = HibernateUtil.currentSession();
//        try
//        {
//            Transaction tx = session.beginTransaction();
//            String sqlString = "select * from contact where contact_id=:id";
//            List list = session.createSQLQuery(sqlString)
//                    .addEntity(ContactEntity.class)
//                    .setInteger("id", 2)
//                    .list();
//
//            for(Object object: list)
//            {
//                ContactEntity contact = (ContactEntity)object;
//                System.out.println(contact.getName());
//            }
//            tx.commit();
//        }
//        finally
//        {
//            HibernateUtil.closeSession();
//        }
//    }
}
