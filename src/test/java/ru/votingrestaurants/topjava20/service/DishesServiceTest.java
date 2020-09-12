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
public class DishesServiceTest {

    @Autowired
    private DishesService dishesService;

    @Test
    public void crete() {
        Dish created = dishesService.crete(getNewDish(), ADMIN_ID);
        int newId = created.getId();
        Dish newDish = getNewDish();
        newDish.setId(newId);
        MEAL_MATCHER.assertMatch(created, newDish);
    }

    @Test
    public void delete() {
        dishesService.delete(DISHES_ID,ADMIN_ID);
        assertThrows(NotFoundException.class, () -> dishesService.getDish(DISHES_ID, ADMIN_ID));
    }

    @Test
    public void deleteAdminNotFound() {
        assertThrows(NotFoundException.class, () -> dishesService.getDish(DISHES_ID, NOT_FOUND));
    }

    @Test
    public void deleteDishNotFound() {
        assertThrows(NotFoundException.class, () -> dishesService.getDish(NOT_FOUND, ADMIN_ID));
    }

    @Test
    public void getAll() {
        MEAL_MATCHER.assertMatch(dishesService.getAll(ADMIN_ID), DISHES);
    }

    @Test
    public void getAllAdminNotFound() {
        assertThrows(NotFoundException.class, () -> dishesService.getDish(NOT_FOUND, ADMIN_ID));
    }
}