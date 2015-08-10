import entity.Address;
import entity.Person;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.Map;

/**
 * Created by peng on 8/10/15.
 */
public class Main
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
            Address ad1 = new Address();
            ad1.setAddressDetail("test for address 1");

            Person p1 = new Person();
            p1.setName("liupeng");
            p1.setAge(31);

            ad1.getPersons().add(p1);
            session.save(ad1);
            session.save(p1);

            transaction.commit();
        }
        finally
        {
            session.close();
        }
    }
}
