package ru.votingrestaurants.topjava20.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.votingrestaurants.topjava20.model.Admin;
import ru.votingrestaurants.topjava20.repository.AdminRepository;
import java.util.List;
import static ru.votingrestaurants.topjava20.util.ValidationUtil.checkNotFoundWithId;

@Service
public class AdminService {

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
}
