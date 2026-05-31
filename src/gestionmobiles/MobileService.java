package gestionmobiles;

import classes.javabeans.Mobile;
import fichiershibernate.NewHibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class MobileService {

    private static Session session;

    public static void openSession() {
        SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
    }

    public static void closeSession() {
        session.close();
    }

    public static void create(Mobile p) {
        Transaction tx = session.beginTransaction();
        session.save(p);
        tx.commit();
    }

    public static Mobile loadMobile(int id) {
        Transaction tx = session.beginTransaction();
        Mobile p = (Mobile) session.get(Mobile.class, id);
        tx.commit();
        return p;
    }

    public static void update(Mobile p) {
        Transaction tx = session.beginTransaction();
        session.update(p);
        tx.commit();
    }

    public static void delete(Mobile p) {
        Transaction tx = session.beginTransaction();
        session.delete(p);
        tx.commit();
    }

    public static List<Mobile> listMobile() {
        Transaction tx = session.beginTransaction();
          session.clear();
        List<Mobile> list = session.createQuery("from Mobile").list();
        tx.commit();
        return list;
    }
}