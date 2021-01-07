package ru.votingrestaurants.topjava20.web.restcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.votingrestaurants.topjava20.model.Restaurant;
import ru.votingrestaurants.topjava20.service.RestaurantService;

import java.util.List;

@RestController
@RequestMapping(value = RestaurantRestController.REST_URL_RESTAURANTS, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantRestController {
    private static final Logger LOG = LoggerFactory.getLogger(RestaurantRestController.class);
    public static final String REST_URL_RESTAURANTS = "/rest/restaurants";

    @Autowired
    private final RestaurantService restaurantService;

    public RestaurantRestController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/{id}")
    public Restaurant getRestaurant(@PathVariable int id) {
        LOG.info("get admin with id{}", id);
        return restaurantService.getRestaurant(id);
    }

    @GetMapping
    public List<Restaurant> getAll() {
        LOG.info("getAllAdmins");
        return restaurantService.getAll();
    }
}
