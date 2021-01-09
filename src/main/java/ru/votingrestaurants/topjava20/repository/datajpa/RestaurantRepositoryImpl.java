package ru.votingrestaurants.topjava20.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.votingrestaurants.topjava20.model.Restaurant;
import ru.votingrestaurants.topjava20.repository.RestaurantRepository;
import ru.votingrestaurants.topjava20.repository.proxyRepository.ProxyRestaurantRepository;

import java.util.List;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepository {
    private static final Sort SORT_NAME_ID = Sort.by(Sort.Direction.ASC, "name", "id");

    @Autowired
    ProxyRestaurantRepository proxyRestaurantRepository;

    @Override
    public Restaurant get(int id) {
        return proxyRestaurantRepository.findById(id).orElse(null);
    }

    @Override
    public List<Restaurant> getAll() {
        return proxyRestaurantRepository.findAll(SORT_NAME_ID);
    }

    @Override
    public Restaurant getByName(String name) {
        return proxyRestaurantRepository.findByName(name);
    }
}
