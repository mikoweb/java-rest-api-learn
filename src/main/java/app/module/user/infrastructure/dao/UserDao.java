package app.module.user.infrastructure.dao;

import app.module.core.infrastrucure.DbSession;
import app.module.user.infrastructure.entity.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UserDao {
    @Inject
    private DbSession dbSession;

    private static final List<User> users = new ArrayList<>();

    public List<User> getUsers() {
        List<User> users = dbSession.createQuery(User.class).getResultList();
        dbSession.close();

        return users;
    }

    public void addUser(User user) {
        save(user);
    }

    public void updateUserEmail(int id, String email) {
        Optional<User> userValue = findUserById(id);

        if (userValue.isPresent()) {
            User user = userValue.get();
            user.setEmail(email);
            save(user);
        }
    }

    public void deleteUser(int id) {
        Optional<User> userValue = findUserById(id);
        userValue.ifPresent(users::remove);
    }

    private Optional<User> findUserById(int id) {
        return users.stream().filter(user -> user.getId() == id).findFirst();
    }

    private void save(User user) {
        dbSession.beginTransaction();
        dbSession.persist(user);
        dbSession.flush();
        dbSession.close();
    }
}
