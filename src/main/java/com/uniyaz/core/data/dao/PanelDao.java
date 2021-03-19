package com.uniyaz.core.data.dao;

import com.uniyaz.core.data.utils.HibernateUtil;
import com.uniyaz.core.domain.Panel;
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
public class PanelDao {
    public void savePanel(Panel panel) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(panel);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Panel> findAllHql() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            String hql =
                    "Select     panel " +
                            "From       Panel panel ";
            Query query = session.createQuery(hql);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deletePanel(Panel panel) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(panel);
            transaction.commit();
        } catch (EcmaError e) {
            e.printStackTrace();
        }
    }

    public List<Panel> findByAnketId(Long anketId) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            String hql =
                    "Select     panel " +
                            "From       Panel panel " +
                            "where      panel.anket.id = :anketId ";
            Query query = session.createQuery(hql);
            query.setParameter("anketId", anketId);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

