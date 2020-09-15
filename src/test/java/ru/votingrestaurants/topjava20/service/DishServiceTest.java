package ru.votingrestaurants.topjava20.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.votingrestaurants.topjava20.model.Dish;
import ru.votingrestaurants.topjava20.util.exception.NotFoundException;
import static org.junit.Assert.assertThrows;
import static ru.votingrestaurants.topjava20.DishesTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class DishServiceTest {

    @Autowired
    private DishService dishService;

    @Test
    public void create() {
        Dish created = dishService.create(getNewDish(), ADMIN_ID);
        int newId = created.getId();
        Dish newDish = getNewDish();
        newDish.setId(newId);
        VOTE_MATCHER.assertMatch(created, newDish);
        VOTE_MATCHER.assertMatch(dishService.getDish(newId, ADMIN_ID), newDish);
    }

    @Test
    public void delete() {
        dishService.delete(DISHES_ID,ADMIN_ID);
        assertThrows(NotFoundException.class, () -> dishService.getDish(DISHES_ID, ADMIN_ID));
    }

    @Test
    public void deleteAdminNotFound() {
        assertThrows(NotFoundException.class, () -> dishService.getDish(DISHES_ID, NOT_FOUND));
    }

    @Test
    public void deleteDishNotFound() {
        assertThrows(NotFoundException.class, () -> dishService.getDish(NOT_FOUND, ADMIN_ID));
    }

    @Test
    public void getDish() {
        Dish actual = dishService.getDish(DISHES_ID, 100000);
        VOTE_MATCHER.assertMatch(actual, DISH1);
    }

    @Test
    public void getAll() {
        VOTE_MATCHER.assertMatch(dishService.getAllForAdmin(ADMIN_ID), DISHES);
    }

    @Test
    public void getAllAdminNotFound() {
        assertThrows(NotFoundException.class, () -> dishService.getDish(NOT_FOUND, ADMIN_ID));
    }
}