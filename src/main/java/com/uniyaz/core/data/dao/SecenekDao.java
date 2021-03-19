package com.uniyaz.core.data.dao;

import com.uniyaz.core.data.utils.HibernateUtil;
import com.uniyaz.core.domain.Secenek;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.mozilla.javascript.EcmaError;

import java.util.List;

/**
 * @author HAKAN DERELİ
 * @since 5.xxx.x
 */
public class SecenekDao {
    public void saveSecenek(Secenek secenek) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(secenek);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Secenek> findAllHql() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            String hql =
                    "Select     secenek " +
                            "From       Secenek secenek ";
            Query query = session.createQuery(hql);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteSecenek(Secenek secenek) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(secenek);
            transaction.commit();
        } catch (EcmaError e) {
            e.printStackTrace();
        }
    }

    public List<Secenek> findBySoruId(Long soruId) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            String hql =
                    "Select     secenek " +
                            "From       Secenek secenek " +
                            "where      secenek.soru.id = :soruId ";
            Query query = session.createQuery(hql);
            query.setParameter("soruId", soruId);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

