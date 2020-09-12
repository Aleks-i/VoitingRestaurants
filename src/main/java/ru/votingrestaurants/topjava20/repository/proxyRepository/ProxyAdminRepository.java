package ru.votingrestaurants.topjava20.repository.proxyRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.votingrestaurants.topjava20.model.Admin;

public interface ProxyAdminRepository extends JpaRepository<Admin, Integer> {

    @Transactional
    @Query("DELETE FROM Admin a WHERE a.id=:id")
    @Modifying
    int delete(@Param("id") int id);

}
