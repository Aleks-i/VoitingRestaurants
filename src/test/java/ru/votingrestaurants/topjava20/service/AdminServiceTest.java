package ru.votingrestaurants.topjava20.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.votingrestaurants.topjava20.model.Admin;
import ru.votingrestaurants.topjava20.util.exception.NotFoundException;
import static org.junit.Assert.*;
import static ru.votingrestaurants.topjava20.AdminTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class AdminServiceTest {

    @Autowired
    protected AdminService adminService;

    @Test
    public void create() {
        Admin created = adminService.create(getNewAdmin());
        int newId =created.getId();
        Admin newAdmin = getNewAdmin();
        newAdmin.setId(newId);
        MEAL_MATCHER.assertMatch(created, newAdmin);
        MEAL_MATCHER.assertMatch(adminService.get(newId), newAdmin);
    }

    @Test
    public void update() {
        Admin updatedAdmin = getUpdatedAdmin();
        adminService.update(updatedAdmin);
        MEAL_MATCHER.assertMatch(adminService.get(ADMIN_ID_1), getUpdatedAdmin());
    }

    @Test
    public void delete() {
        adminService.delete(ADMIN_ID_1);
        assertThrows(NotFoundException.class, () -> adminService.get(ADMIN_ID_1));
    }

    @Test
    public void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> adminService.delete(NOT_FOUND));
    }

    @Test
    public void get() {
        Admin admin = adminService.get(ADMIN_ID_1);
        MEAL_MATCHER.assertMatch(admin, ADMIN_1);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> adminService.get(NOT_FOUND));
    }

    @Test
    public void getAll() {
        MEAL_MATCHER.assertMatch(adminService.getAll(), ADMINS);
    }
}