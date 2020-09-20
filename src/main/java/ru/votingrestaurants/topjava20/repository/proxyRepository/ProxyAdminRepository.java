package ru.votingrestaurants.topjava20.repository.proxyRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.votingrestaurants.topjava20.model.Admin;

@Transactional(readOnly = true)
public interface ProxyAdminRepository extends JpaRepository<Admin, Integer> {
    Admin getByEmail(String email);
}
