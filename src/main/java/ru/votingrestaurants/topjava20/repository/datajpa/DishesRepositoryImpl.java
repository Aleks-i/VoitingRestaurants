package ru.votingrestaurants.topjava20.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.votingrestaurants.topjava20.model.Dish;
import ru.votingrestaurants.topjava20.repository.DishesRepository;
import ru.votingrestaurants.topjava20.repository.proxyRepository.ProxyAdminRepository;
import ru.votingrestaurants.topjava20.repository.proxyRepository.ProxyDishesRepository;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public class DishesRepositoryImpl implements DishesRepository {

    @Autowired
    private ProxyDishesRepository proxyDishesRepository;

    @Autowired
    private ProxyAdminRepository adminRepository;

    @Override
    public Dish save(Dish dinner, int admin_id) {
        if (!dinner.isNew() && getDish(dinner.getId(), admin_id) == null) {
            return null;
        }
        dinner.setAdmin(adminRepository.getOne(admin_id));
        return proxyDishesRepository.save(dinner);
    }

    @Override
    @Transactional
    public boolean delete(int id, int admin_id) {
        return proxyDishesRepository.delete(id, admin_id) != 0;
    }

    public Dish getDish(int id, int admin_id) {
        return proxyDishesRepository.findById(id)
                .filter(dinner -> dinner.getAdmin().getId() == admin_id)
                .orElse(null);
    }

    @Override
    public List<Dish> getAll(int admin_id) {
        return proxyDishesRepository.getAll(admin_id);
    }
}
