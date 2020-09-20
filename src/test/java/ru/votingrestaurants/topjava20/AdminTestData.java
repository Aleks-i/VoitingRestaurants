package ru.votingrestaurants.topjava20;

import ru.votingrestaurants.topjava20.model.Admin;
import ru.votingrestaurants.topjava20.model.Role;

import java.util.List;

public class AdminTestData {
    public static TestMatcher<Admin> VOTE_MATCHER = TestMatcher.usingFieldsWithIgnoringAssertions(Admin.class, "voteList", "lunchMenu", "password");

    public static final int START_SEQ = 100000;
    public static final int NOT_FOUND = 10;
    public static final int ADMIN_ID_1 = START_SEQ;
    public static final int ADMIN_ID_2 = START_SEQ + 1;
    public static final String EMAIL = "admin0@yandex.ru";
    public static final String EMAIL_NOT_FOUND = "notfound@yandex.ru";

    public static final Admin ADMIN_1 = new Admin(ADMIN_ID_1, "Admin0", "admin0@yandex.ru", "password0", Role.ADMIN);
    public static final Admin ADMIN_2 = new Admin(ADMIN_ID_2, "Admin1", "admin1@gmail.com", "password1", Role.ADMIN);

    public static final List<Admin> ADMINS = List.of(ADMIN_1, ADMIN_2);
}
