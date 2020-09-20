package ru.votingrestaurants.topjava20.web.restcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.votingrestaurants.topjava20.AuthorizedUser;
import ru.votingrestaurants.topjava20.model.User;
import ru.votingrestaurants.topjava20.service.UserService;
import ru.votingrestaurants.topjava20.to.UserTo;
import ru.votingrestaurants.topjava20.util.UserUtil;

import javax.validation.Valid;
import java.net.URI;

import static ru.votingrestaurants.topjava20.util.ValidationUtil.assureIdConsistent;
import static ru.votingrestaurants.topjava20.util.ValidationUtil.checkNew;
import static ru.votingrestaurants.topjava20.web.SecurityUtil.authUserId;

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

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<User> createUser(@Valid @RequestBody UserTo userTo) {
        User createdUser = create(userTo);
        URI ofNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL_USERS).build().toUri();
        return ResponseEntity.created(ofNewResource).body(createdUser);
    }

    public User create(UserTo userTo) {
        LOG.info("create from to {}", userTo);
        return create(UserUtil.createNewFromTo(userTo));
    }

    public User create(User user) {
        LOG.info("create {}", user);
        checkNew(user);
        return userService.create(user);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody User user, @AuthenticationPrincipal AuthorizedUser authUser) {
        LOG.info("update {} with id={}", user, authUser.getId());
        assureIdConsistent(user, authUser.getId());
        userService.update(user);
    }

    @DeleteMapping("/profile/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@AuthenticationPrincipal AuthorizedUser authUser) {
        LOG.info("delete {}", authUser.getId());
        userService.delete(authUser.getId());
    }
}
