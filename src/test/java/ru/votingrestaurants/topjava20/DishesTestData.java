package ru.votingrestaurants.topjava20;

import ru.votingrestaurants.topjava20.model.Dish;
import java.util.List;

public class DishesTestData {
    public static TestMatcher<Dish> DISH_MATCHER = TestMatcher.usingFieldsWithIgnoringAssertions(Dish.class, "restaurant");

    public static final int START_SEQ = 100000;
    public static final int NOT_FOUND = 10;
    public static final int DISHES_ID = START_SEQ + 9;
    public static final int RESTAURANT_ID_1 = START_SEQ + 7;

    public static final Dish DISH1 = new Dish(DISHES_ID, "пирог", 150.56);
    public static final Dish DISH2 = new Dish(DISHES_ID + 1, "десерт", 200.13);
    public static final Dish DISH3 = new Dish(DISHES_ID + 2, "чай", 100.05);

    public static final List<Dish> DISHES_FOR_RESTAURANT_1 = List.of(DISH1, DISH2,  DISH3);

    public static Dish getNewDish() {
        return new Dish(null, "NewDish", 220.45);
    }
}
