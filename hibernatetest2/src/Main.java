import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import pengliu.me.hibernatetest.AddressEntity;
import pengliu.me.hibernatetest.PersonEntity;

import java.util.Map;

/**
 * Created by peng on 8/9/15.
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
            Transaction t = session.beginTransaction();
            PersonEntity p1 = new PersonEntity();
            p1.setName("liupeng test 2");
            p1.setAge(98);
            session.save(p1);

            AddressEntity ad1 = new AddressEntity();
            ad1.setPersonByPersonId(p1);
            ad1.setDetail("test detail 123123");
            session.save(ad1);

            t.commit();
        }
        finally
        {
            session.close();
        }
    }
}
