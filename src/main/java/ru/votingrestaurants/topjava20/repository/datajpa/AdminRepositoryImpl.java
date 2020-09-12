package ru.votingrestaurants.topjava20.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.votingrestaurants.topjava20.model.Admin;
import ru.votingrestaurants.topjava20.repository.AdminRepository;
import ru.votingrestaurants.topjava20.repository.proxyRepository.ProxyAdminRepository;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class AdminRepositoryImpl implements AdminRepository {
   private static final Sort SORT_NAME_EMAIL = Sort.by(Sort.Direction.ASC, "name", "email");

    @Autowired
    ProxyAdminRepository proxyAdminRepository;

    @Override
    @Transactional
    public Admin save(Admin admin) {
        return proxyAdminRepository.save(admin);
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return proxyAdminRepository.delete(id) != 0;
    }

    @Override
    public Admin get(int id) {
        return proxyAdminRepository.findById(id).orElse(null);
    }


    @Override
    public List<Admin> getAll() {
        return proxyAdminRepository.findAll(SORT_NAME_EMAIL);
    }
}
