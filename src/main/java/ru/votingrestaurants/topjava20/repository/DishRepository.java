package ru.votingrestaurants.topjava20.repository;

import ru.votingrestaurants.topjava20.model.Dish;
import java.util.List;

public interface DishRepository {

    Dish save(Dish dish, int admin_id);

    boolean delete(int id, int admin_id);

    Dish getDish(int id, int admin_id);

    List<Dish> getAll(int admin_id);
}
