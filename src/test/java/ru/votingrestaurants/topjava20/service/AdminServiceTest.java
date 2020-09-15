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
    public void getAmin() {
        Admin admin = adminService.getAdmin(ADMIN_ID_1);
        VOTE_MATCHER.assertMatch(admin, ADMIN_1);
    }

    @Test
    public void getAdminNotFound() {
        assertThrows(NotFoundException.class, () -> adminService.getAdmin(NOT_FOUND));
    }

    @Test
    public void getAll() {
        VOTE_MATCHER.assertMatch(adminService.getAll(), ADMINS);
    }
}