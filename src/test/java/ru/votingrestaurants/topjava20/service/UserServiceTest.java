package ru.votingrestaurants.topjava20.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.votingrestaurants.topjava20.model.User;
import ru.votingrestaurants.topjava20.util.exception.NotFoundException;
import static org.junit.Assert.assertThrows;
import static ru.votingrestaurants.topjava20.UserTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void create() {
        User created = userService.create(getNewUser());
        int newId =created.getId();
        User newUser = getNewUser();
        newUser.setId(newId);
        MEAL_MATCHER.assertMatch(created, newUser);
        MEAL_MATCHER.assertMatch(userService.getUser(newId), newUser);
    }

    @Test
    public void update() {
        User updatedUser = getUpdatedUser();
        userService.update(updatedUser);
        MEAL_MATCHER.assertMatch(userService.getUser(USER_ID), getUpdatedUser());
    }

    @Test
    public void delete() {
        userService.delete(USER_ID);
        assertThrows(NotFoundException.class, () -> userService.getUser(USER_ID));
    }

    @Test
    public void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> userService.delete(NOT_FOUND));
    }

    @Test
    public void get() {
        User user = userService.getUser(USER_ID);
        MEAL_MATCHER.assertMatch(user, USER);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> userService.getUser(NOT_FOUND));
    }
}