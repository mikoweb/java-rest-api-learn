package app.module.user.infrastructure.dao;

import app.module.core.infrastrucure.DbSession;
import app.module.user.infrastructure.entity.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class UserDao {
    @Inject
    private DbSession dbSession;

    public List<User> getUsers() {
        List<User> users = dbSession.createQuery(User.class).getResultList();
        dbSession.close();

        return users;
    }

    public void addUser(User user) {
        save(user, true, true);
    }

    public void updateUserEmail(int id, String email) {
        dbSession.beginTransaction();
        User user = findUser(id);

        if (user != null) {
            user.setEmail(email);
            save(user, false, false);
        }

        dbSession.close();
    }

    public void deleteUser(int id) {
        dbSession.beginTransaction();
        User user = findUser(id);

        if (user != null) {
            dbSession.getSession().remove(user);
            dbSession.flush();
        }

        dbSession.close();
    }

    public User findUser(int id) {
        return dbSession.getSession().find(User.class, id);
    }

    private void save(User user, boolean begin, boolean close) {
        if (begin) {
            dbSession.beginTransaction();
        }

        dbSession.persist(user);
        dbSession.flush();

        if (close) {
            dbSession.close();
        }
    }
}
