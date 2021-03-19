package com.uniyaz.core.data.dao;

import com.uniyaz.core.data.utils.HibernateUtil;
import com.uniyaz.core.domain.Soru;
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
public class SoruDao {
    public void saveSoru(Soru soru) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(soru);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Soru> findAllHql() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            String hql =
                    "Select     soru " +
                            "From       Soru soru ";
            Query query = session.createQuery(hql);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteSoru(Soru soru) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(soru);
            transaction.commit();
        } catch (EcmaError e) {
            e.printStackTrace();
        }
    }

    public List<Soru> findByPanelId(Long panelId) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            String hql =
                    "Select     soru " +
                            "From       Soru soru " +
                            "where      soru.panel.id = :panelId ";
            Query query = session.createQuery(hql);
            query.setParameter("panelId", panelId);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

