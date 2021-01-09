package ru.votingrestaurants.topjava20.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.votingrestaurants.topjava20.model.Dish;
import ru.votingrestaurants.topjava20.model.Vote;
import ru.votingrestaurants.topjava20.repository.DishRepository;

import java.util.List;

import static ru.votingrestaurants.topjava20.util.ValidationUtil.assureIdConsistent;
import static ru.votingrestaurants.topjava20.util.ValidationUtil.checkNotFoundWithId;

@Service
public class DishService {

    @Autowired
    private DishRepository dishRepository;

    public Dish create(Dish dish, int userId, int restaurantId) {
        Assert.notNull(dish, "dinner must not be null");
        return checkNotFoundWithId(dishRepository.save(dish, userId, restaurantId), dish.getId());
    }

    public void update(Dish dish, int userId, int restaurantId, int id) {
        Assert.notNull(dish, "user must be not null");
        assureIdConsistent(dish, id);
        checkNotFoundWithId(dishRepository.save(dish, userId, restaurantId), id);
    }

    public void delete(int id, int restaurantId) {
        checkNotFoundWithId(dishRepository.delete(id, restaurantId), id);
    }

    public Dish getDish(int id, int userId, int restaurantId) {
        return checkNotFoundWithId(dishRepository.getDish(id, userId, restaurantId), id);
    }

    public List<Dish> getAllForRestaurant(int restaurantId) {
        return dishRepository.getAllForRestaurant(restaurantId);
    }

}
