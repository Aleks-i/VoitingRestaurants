package ru.votingrestaurants.topjava20;

import ru.votingrestaurants.topjava20.model.Admin;
import java.util.List;

public class AdminTestData {
    public static TestMatcher<Admin> MEAL_MATCHER = TestMatcher.usingFieldsWithIgnoringAssertions(Admin.class, "lunchMenu", "voteList");

    public static final int START_SEQ = 100000;
    public static final int NOT_FOUND = 10;
    public static final int ADMIN_ID_1 = START_SEQ;
    public static final int ADMIN_ID_2 = START_SEQ + 1;

    public static final Admin ADMIN_1 = new Admin(ADMIN_ID_1, "Admin0", "admin0@yandex.ru", "password0");
    public static final Admin ADMIN_2 = new Admin(ADMIN_ID_2, "Admin1", "admin1@gmail.com", "password1");

    public static final List<Admin> ADMINS = List.of(ADMIN_1, ADMIN_2);

    public static Admin getNewAdmin() {
        return new Admin(null, "NewAdmin", "newadmin@mail.ru", "newpass");
    }

    public static Admin getUpdatedAdmin() {
        Admin updatedAdmin = new Admin(ADMIN_1);
        updatedAdmin.setName("Updated");
        updatedAdmin.setEmail("updated@mail.ru");
        return updatedAdmin;
    }
}
