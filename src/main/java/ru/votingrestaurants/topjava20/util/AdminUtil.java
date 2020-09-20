package ru.votingrestaurants.topjava20.util;

import ru.votingrestaurants.topjava20.model.Admin;
import ru.votingrestaurants.topjava20.to.AdminTo;

public class AdminUtil {

    public static AdminTo asTo(Admin admin) {
        return new AdminTo(admin.getId(), admin.getName(), admin.getEmail(), admin.getPassword());
    }

}
