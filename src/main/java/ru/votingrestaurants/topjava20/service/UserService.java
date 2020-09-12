package ru.votingrestaurants.topjava20.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.votingrestaurants.topjava20.model.User;
import ru.votingrestaurants.topjava20.repository.UserRepository;
import static ru.votingrestaurants.topjava20.util.ValidationUtil.checkNotFoundWithId;

@Service
public class UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User user) {
        LOG.info("reate user {}", user);
        Assert.notNull(user, "user must be not null");
        return userRepository.save(user);
    }

    public void update(User user) {
        LOG.info("update user {}", user);
        Assert.notNull(user, "user must be not null");
        checkNotFoundWithId(userRepository.save(user), user.getId());
    }

    public void delete(int id) {
        LOG.info("delete id {}", id);
        checkNotFoundWithId(userRepository.delete(id), id);
    }

    public User getUser(int id) {
        LOG.info("getUser id {}",id);
        return checkNotFoundWithId(userRepository.getUser(id), id);
    }
}
