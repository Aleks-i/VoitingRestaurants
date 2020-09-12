package ru.votingrestaurants.topjava20;

import ru.votingrestaurants.topjava20.model.User;

public class UserTestData {
    public static TestMatcher<User> MEAL_MATCHER = TestMatcher.usingFieldsWithIgnoringAssertions(User.class, "");

    public static final int START_SEQ = 100002;
    public static final int NOT_FOUND = 10;
    public static final int USER_ID = START_SEQ;

    public static final User USER = new User(USER_ID, "User0", "user0@yandex.ru", "password0");

    public static User getNewUser() {
        return new User(null, "NewUser", "newauser@mail.ru", "newpass");
    }

    public static User getUpdatedUser() {
        User updatedUser = new User(USER);
        updatedUser.setName("Updated");
        updatedUser.setEmail("updated@mail.ru");
        return updatedUser;
    }
}
