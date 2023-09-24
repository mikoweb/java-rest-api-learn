package app.module.user.infrastructure.dao;

import app.module.user.infrastructure.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserDao {
    public static List<User> getUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "test", "test", "test"));

        return users;
    }
}
