package ru.votingrestaurants.topjava20.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.votingrestaurants.topjava20.model.Dish;
import ru.votingrestaurants.topjava20.repository.DishRepository;
import ru.votingrestaurants.topjava20.repository.proxyRepository.ProxyRestaurantRepository;
import ru.votingrestaurants.topjava20.repository.proxyRepository.ProxyDishRepository;

import java.util.List;

@Repository
public class DishRepositoryImpl implements DishRepository {

    @Autowired
    private ProxyDishRepository proxyDishRepository;

    @Autowired
    private ProxyRestaurantRepository restaurantRepository;

    @Override
    public Dish save(Dish dish, int restaurantId) {
        if (!dish.isNew() && getDish(dish.getId(), restaurantId) == null) {
            return null;
        }
        dish.setRestaurant(restaurantRepository.getOne(restaurantId));
        return proxyDishRepository.save(dish);
    }

    @Override
    public boolean delete(int id, int restaurantId) {
        return proxyDishRepository.delete(id, restaurantId) != 0;
    }

    public Dish getDish(int id, int restaurantId) {
        return proxyDishRepository.findById(id)
                .filter(dish -> dish.getRestaurant().getId().equals(restaurantId))
                .orElse(null);
    }

    @Override
    public List<Dish> getAllForRestaurant(int restaurantId) {
        return proxyDishRepository.getAll(restaurantId);
    }
}
