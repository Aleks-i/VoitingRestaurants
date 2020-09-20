package ru.votingrestaurants.topjava20.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.votingrestaurants.topjava20.AuthorizedAdmin;
import ru.votingrestaurants.topjava20.model.Admin;
import ru.votingrestaurants.topjava20.repository.AdminRepository;

import java.util.List;

import static ru.votingrestaurants.topjava20.util.ValidationUtil.checkNotFound;
import static ru.votingrestaurants.topjava20.util.ValidationUtil.checkNotFoundWithId;

@Service
public class AdminService implements UserDetailsService {

    @Autowired
    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public Admin getAdmin(int id) {
        return checkNotFoundWithId(adminRepository.get(id), id);
    }

    public List<Admin> getAll() {
        return adminRepository.getAll();
    }

    public Admin getByEmail(String email) {
        Assert.notNull(email, "email must not be null");
        return checkNotFound(adminRepository.getByEmail(email), "email" + email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Admin admin = adminRepository.getByEmail(email.toLowerCase());
        if (admin == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new AuthorizedAdmin(admin);
    }
}
