package ru.votingrestaurants.topjava20.repository;

import ru.votingrestaurants.topjava20.model.User;

public interface UserRepository {

    User save(User user);

    boolean delete(int id);

    User getUser(int id);
}