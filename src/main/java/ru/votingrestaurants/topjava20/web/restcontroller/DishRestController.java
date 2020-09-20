package ru.votingrestaurants.topjava20.web.restcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.votingrestaurants.topjava20.View;
import ru.votingrestaurants.topjava20.model.Dish;
import ru.votingrestaurants.topjava20.service.DishService;
import ru.votingrestaurants.topjava20.web.SecurityUtil;

import java.net.URI;
import java.util.List;

import static ru.votingrestaurants.topjava20.util.ValidationUtil.checkNew;
import static ru.votingrestaurants.topjava20.web.SecurityUtil.authAdminId;

@RestController
@RequestMapping(value = DishRestController.REST_URL_DINNERS, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishRestController {
    private static final Logger LOG = LoggerFactory.getLogger(DishRestController.class);
    public static final String REST_URL_DINNERS = "/rest/admins/dishes";

    @Autowired
    private final DishService dishService;

    public DishRestController(DishService dishService) {
        this.dishService = dishService;
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> save(@Validated(View.Web.class)@RequestBody Dish dish) {
        int adminId = SecurityUtil.authAdminId();
        checkNew(dish);
        LOG.info("save {} for admin {}", dish, adminId);
        Dish createdDis = dishService.create(dish, adminId);
        URI ofNewRecourse = ServletUriComponentsBuilder.fromCurrentContextPath()
            .path(REST_URL_DINNERS + "/{id}")
            .buildAndExpand(createdDis.getId()).toUri();
        return ResponseEntity.created(ofNewRecourse).body(createdDis);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        int adminId = SecurityUtil.authAdminId();
        LOG.info("delete dish {} for admin {}", id, adminId);
        dishService.delete(id, adminId);
    }

    @GetMapping("/{admin_id}")
    public List<Dish> getAllForAdmin(@PathVariable int admin_id) {
        LOG.info("getAll {}", admin_id);
        return dishService.getAllForAdmin(admin_id);
    }
}
