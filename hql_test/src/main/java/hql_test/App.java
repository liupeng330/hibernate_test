package hql_test;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import model.*;

import java.util.List;


/**
 * Created by peng on 8/10/15.
 */
public class App
{
    private static final SessionFactory ourSessionFactory;
    private static final ServiceRegistry serviceRegistry;

    static
    {
        try
        {
            Configuration configuration = new Configuration();
            configuration.configure();

            serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            ourSessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex)
        {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException
    {
        return ourSessionFactory.openSession();
    }

    public static void main(final String[] args) throws Exception
    {
        final Session session = getSession();
        try
        {
            Transaction transaction = session.beginTransaction();

//            List addresses = session.createQuery(
//                    "select distinct a from AddressEntity as a where a.personByPersonId.age > :age")
//                    .setInteger("age", 100)
//                    .list();
//            for(Object address: addresses)
//            {
//                AddressEntity addre = (AddressEntity)address;
//                System.out.println(
//                        addre.getId() + ", " +
//                        addre.getDetail() + "," +
//                        addre.getPersonByPersonId().getName());
//            }

//            List addresses = session.createQuery(
//                    "select distinct a from AddressEntity as a inner join a.personByPersonId as p where p.age > :age")
//                    .setInteger("age", 100)
//                    .list();
//            for(Object address: addresses)
//            {
//                AddressEntity addre = (AddressEntity)address;
//                System.out.println(
//                        addre.getId() + ", " +
//                        addre.getDetail() + "," +
//                        addre.getPersonByPersonId().getName());
//            }

//            List persons = session.createQuery(
//                    "from PersonEntity as p where p.addressesById.size > 2").list();
//            for(Object p: persons)
//            {
//                PersonEntity personEntity = (PersonEntity)p;
//                System.out.println(personEntity.getName());
//                System.out.println(personEntity.getAge());
//            }

            List addressAndPersons = session.createQuery(
                    "from AddressEntity as a inner join a.personByPersonId as p where p.age > :age")
                    .setInteger("age", 100)
                    .list();
            for(Object obj: addressAndPersons)
            {
                Object[] objs = (Object[])obj;
                AddressEntity addre = (AddressEntity)objs[0];
                System.out.println("--Getting address--");
                System.out.println(
                        addre.getId() + ", " +
                        addre.getDetail() + "," +
                        addre.getPersonByPersonId().getName());

                PersonEntity person = (PersonEntity)objs[1];
                System.out.println("--Getting person--");
                System.out.println(
                        person.getId() + ", " +
                        person.getName() + ", " +
                        person.getAge());
            }

            transaction.commit();
        }
        finally
        {
            session.close();
        }
    }
}

