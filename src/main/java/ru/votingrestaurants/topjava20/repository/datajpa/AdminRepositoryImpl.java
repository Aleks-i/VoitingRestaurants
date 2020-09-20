package ru.votingrestaurants.topjava20.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.votingrestaurants.topjava20.model.Admin;
import ru.votingrestaurants.topjava20.repository.AdminRepository;
import ru.votingrestaurants.topjava20.repository.proxyRepository.ProxyAdminRepository;
import java.util.List;

@Repository
public class AdminRepositoryImpl implements AdminRepository {
   private static final Sort SORT_NAME_EMAIL = Sort.by(Sort.Direction.ASC, "name", "email");

    @Autowired
    ProxyAdminRepository proxyAdminRepository;

    @Override
    public Admin get(int id) {
        return proxyAdminRepository.findById(id).orElse(null);
    }

    @Override
    public List<Admin> getAll() {
        return proxyAdminRepository.findAll(SORT_NAME_EMAIL);
    }

    @Override
    public Admin getByEmail(String email) {
        return proxyAdminRepository.getByEmail(email);
    }
}
