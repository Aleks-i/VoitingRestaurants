package ru.votingrestaurants.topjava20;

import ru.votingrestaurants.topjava20.model.Admin;
import ru.votingrestaurants.topjava20.model.Dish;
import java.time.LocalDate;
import java.util.List;

public class DishesTestData {
    public static TestMatcher<Dish> VOTE_MATCHER = TestMatcher.usingFieldsWithIgnoringAssertions(Dish.class, "admin");

    public static final int START_SEQ = 100007;
    public static final int NOT_FOUND = 10;
    public static final int DISHES_ID = START_SEQ;
    public static final int ADMIN_ID = 100000;


    public static final Dish DISH1 = new Dish(DISHES_ID, "пирог", 150.56, LocalDate.now());
    public static final Dish DISH2 = new Dish(DISHES_ID + 1, "десерт", 200.13, LocalDate.now());
    public static final Dish DISH3 = new Dish(DISHES_ID + 2, "чай", 100.05, LocalDate.now());


    public static final List<Dish> DISHES = List.of(DISH1, DISH2,  DISH3);

    public static Dish getNewDish() {
        return new Dish(null, "NewDish", 220.45, LocalDate.now());
    }
}
