package app.module.core.infrastrucure;

import app.module.user.infrastructure.entity.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

@ApplicationScoped
public class DbSession {
    private SessionFactory sessionFactory;

    public DbSession() {
        createSessionFactory();
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public CriteriaBuilder getCriteriaBuilder() {
        return getSession().getCriteriaBuilder();
    }

    public <T> Query<T> createQuery(Class<T> tClass) {
        getSession().beginTransaction();

        CriteriaBuilder cb = getCriteriaBuilder();
        CriteriaQuery<T> cr = cb.createQuery(tClass);
        Root<T> root = cr.from(tClass);
        cr.select(root);

        return getSession().createQuery(cr);
    }

    public void close() {
        getSession().close();
    }

    private void createSessionFactory() {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .buildSessionFactory();
    }
}
