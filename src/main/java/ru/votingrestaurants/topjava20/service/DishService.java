package ru.votingrestaurants.topjava20.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.votingrestaurants.topjava20.model.Dish;
import ru.votingrestaurants.topjava20.repository.DishRepository;

import java.util.List;

import static ru.votingrestaurants.topjava20.util.ValidationUtil.checkNotFoundWithId;

@Service
public class DishService {

    @Autowired
    private DishRepository dishRepository;

    public Dish create(Dish dish, int restaurantId) {
        Assert.notNull(dish, "dinner must not be null");
        return dishRepository.save(dish, restaurantId);
    }

    public void delete(int id, int restaurantId) {
        checkNotFoundWithId(dishRepository.delete(id, restaurantId), id);
    }

    public Dish getDish(int id, int restaurantId) {
        return checkNotFoundWithId(dishRepository.getDish(id, restaurantId), id);
    }

    public List<Dish> getAllForRestaurant(int restaurantId) {
        return dishRepository.getAllForRestaurant(restaurantId);
    }

}
