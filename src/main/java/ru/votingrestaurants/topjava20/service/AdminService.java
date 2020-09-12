package ru.votingrestaurants.topjava20.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.votingrestaurants.topjava20.model.Admin;
import ru.votingrestaurants.topjava20.repository.AdminRepository;
import java.util.List;
import static ru.votingrestaurants.topjava20.util.ValidationUtil.checkNotFoundWithId;

@Service
public class AdminService {
    private static final Logger LOG = LoggerFactory.getLogger(AdminService.class);

    @Autowired
    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }


    public Admin create(Admin admin) {
        LOG.info("create{}", admin);
        Assert.notNull(admin, "admin must be not null");
        return adminRepository.save(admin);
    }

    public void update(Admin admin) {
        LOG.info("update admin {}", admin.getId());
        Assert.notNull(admin, "admin must be not null");
        checkNotFoundWithId(adminRepository.save(admin), admin.getId());
    }

    public void delete(int id) {
        LOG.info("delete id {}", id);
        checkNotFoundWithId(adminRepository.delete(id), id);
    }

    public Admin get(int id) {
        LOG.info("get id" + id);
        return checkNotFoundWithId(adminRepository.get(id), id);
    }

    public List<Admin> getAll() {
        LOG.info("getAll admin");
        return adminRepository.getAll();
    }
}
