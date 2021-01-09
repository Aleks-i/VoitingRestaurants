package ru.votingrestaurants.topjava20.repository;

import ru.votingrestaurants.topjava20.model.Dish;

import java.util.List;

public interface DishRepository {

    Dish save(Dish dish, int restaurantId);

    boolean delete(int id, int restaurantId);

    Dish getDish(int id, int restaurantId);

    List<Dish> getAllForRestaurant(int restaurantId);
}
