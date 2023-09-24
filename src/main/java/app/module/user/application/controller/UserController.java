package app.module.user.application.controller;

import app.module.user.infrastructure.dao.UserDao;
import app.module.user.infrastructure.entity.User;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("users")
public class UserController {
    @Path("all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getUsers() {
        return UserDao.getUsers();
    }
}
