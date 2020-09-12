package ru.votingrestaurants.topjava20.repository;

import ru.votingrestaurants.topjava20.model.Admin;
import java.util.List;

public interface AdminRepository {

    Admin save(Admin admin);

    boolean delete(int id);

    Admin get(int id);

    List<Admin> getAll();
}
