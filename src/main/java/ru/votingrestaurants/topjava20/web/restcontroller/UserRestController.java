package ru.votingrestaurants.topjava20.web.restcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.votingrestaurants.topjava20.model.User;
import ru.votingrestaurants.topjava20.service.UserService;

import java.net.URI;

@RestController
@RequestMapping(value = UserRestController.REST_URL_USERS, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestController {
    private static final Logger LOG = LoggerFactory.getLogger(UserRestController.class);
    public static final String REST_URL_USERS = "/rest/users";

    @Autowired
    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> create(@RequestBody User user) {
        LOG.info("create {}", user);
//        checkNew(user);
        User createdUser = userService.create(user);
        URI ofNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL_USERS + "/{id}")
                .buildAndExpand(createdUser.getId()).toUri();
        return ResponseEntity.created(ofNewResource).body(createdUser);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody User user, @PathVariable int id) {
        LOG.info("update {} with id={}", user, id);
//        assureIdConsistent(user, id);
        userService.update(user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        LOG.info("delete {}",id);
        userService.delete(id);
    }

    @GetMapping("/{id}")
    public User get(@PathVariable int id) {
        LOG.info("get {}", id);
        return userService.getUser(id);
    }
}
