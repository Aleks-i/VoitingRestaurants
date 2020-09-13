package ru.votingrestaurants.topjava20.web.restcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.votingrestaurants.topjava20.model.Dish;
import ru.votingrestaurants.topjava20.service.DishService;

import java.net.URI;
import java.util.List;

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

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> save(@RequestBody Dish dinner) {
        LOG.info("save {}", dinner);
//        checkNew(dinner);
        Dish createdDinner = dishService.crete(dinner, authAdminId());
        URI ofNewRecourse = ServletUriComponentsBuilder.fromCurrentContextPath()
            .path(REST_URL_DINNERS + "/{id}")
            .buildAndExpand(createdDinner.getId()).toUri();
        return ResponseEntity.created(ofNewRecourse).body(createdDinner);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        LOG.info("delete {}", id);
        dishService.delete(id, authAdminId());
    }

    @GetMapping("/{admin_id}")
    public List<Dish> getAll(@PathVariable int admin_id) {
        LOG.info("getAll {}", admin_id);
        return dishService.getAll(admin_id);
    }
}
