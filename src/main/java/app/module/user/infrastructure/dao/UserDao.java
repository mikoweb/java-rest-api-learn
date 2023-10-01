package app.module.user.infrastructure.dao;

import app.module.user.infrastructure.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao {
    private static final List<User> users = new ArrayList<>();

    public static List<User> getUsers() {
        return users;
    }

    public static void addUser(User user) {
        users.add(user);
    }

    public static void updateUserEmail(int id, String email) {
        Optional<User> userValue = findUserById(id);
        userValue.ifPresent(user -> user.setEmail(email));
    }

    public static void deleteUser(int id) {
        Optional<User> userValue = findUserById(id);
        userValue.ifPresent(users::remove);
    }

    private static Optional<User> findUserById(int id) {
        return users.stream().filter(user -> user.getId() == id).findFirst();
    }
}
