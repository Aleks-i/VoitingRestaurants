package ru.votingrestaurants.topjava20.repository;

import ru.votingrestaurants.topjava20.model.Restaurant;
import java.util.List;

public interface RestaurantRepository {

    Restaurant get(int id);

    List<Restaurant> getAll();

    Restaurant getByName(String name);
}
