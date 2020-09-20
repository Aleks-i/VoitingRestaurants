package ru.votingrestaurants.topjava20;

import ru.votingrestaurants.topjava20.model.Admin;
import ru.votingrestaurants.topjava20.to.AdminTo;
import ru.votingrestaurants.topjava20.util.AdminUtil;

public class AuthorizedAdmin extends org.springframework.security.core.userdetails.User {
    private static final long serialVersionUID = 2L;

    private final AdminTo adminTo;

    public AuthorizedAdmin(Admin admin) {
        super(admin.getEmail(), admin.getPassword(), admin.isEnabled(), true, true, true, admin.getRoles());
        this.adminTo = AdminUtil.asTo(admin);
    }

    public int getId() {
        return adminTo.id();
    }

    public AdminTo getAdminTo() {
        return adminTo;
    }

    @Override
    public String toString() {
        return adminTo.toString();
    }
}
