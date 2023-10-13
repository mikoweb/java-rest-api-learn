package app.module.user.application.controller;

import app.module.user.infrastructure.dao.UserDao;
import app.module.user.infrastructure.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("users")
public class UserController {
    @Inject
    private UserDao userDao;

    @Path("all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getUsers() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);

        return mapper.writeValueAsString(userDao.getUsers());
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("add")
    public User addUser(
        @FormParam("name") String name,
        @FormParam("password") String password,
        @FormParam("email") String email
    ) {
        User user = new User(name, password, email);
        userDao.addUser(user);

        return user;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("update/{id}")
    public String updateUserEmail(@PathParam("id") int id, @FormParam("email") String email) {
        userDao.updateUserEmail(id, email);

        return "email updated!";
    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Path("delete/{id}")
    public String deleteUser(@PathParam("id") int id) {
        userDao.deleteUser(id);

        return "user deleted!";
    }
}
