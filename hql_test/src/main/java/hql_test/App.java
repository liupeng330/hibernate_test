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

            List books = session.createQuery("from BookEntity as b").list();
            for(Object book: books)
            {
                BookEntity b = (BookEntity)book;
                System.out.println(b.getId());
                System.out.println(b.getName());
                System.out.println(b.getPrice());
            }

            transaction.commit();
        }
        finally
        {
            session.close();
        }
    }
}

