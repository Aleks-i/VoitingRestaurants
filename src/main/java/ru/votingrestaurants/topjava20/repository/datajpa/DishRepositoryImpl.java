package ru.votingrestaurants.topjava20.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.votingrestaurants.topjava20.model.Dish;
import ru.votingrestaurants.topjava20.repository.DishRepository;
import ru.votingrestaurants.topjava20.repository.proxyRepository.ProxyAdminRepository;
import ru.votingrestaurants.topjava20.repository.proxyRepository.ProxyDishRepository;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public class DishRepositoryImpl implements DishRepository {

    @Autowired
    private ProxyDishRepository proxyDishRepository;

    @Autowired
    private ProxyAdminRepository adminRepository;

    @Override
    public Dish save(Dish dinner, int admin_id) {
        if (!dinner.isNew() && getDish(dinner.getId(), admin_id) == null) {
            return null;
        }
        dinner.setAdmin(adminRepository.getOne(admin_id));
        return proxyDishRepository.save(dinner);
    }

    @Override
    @Transactional
    public boolean delete(int id, int admin_id) {
        return proxyDishRepository.delete(id, admin_id) != 0;
    }

    public Dish getDish(int id, int admin_id) {
        return proxyDishRepository.findById(id)
                .filter(dish -> dish.getAdmin().getId() == admin_id)
                .orElse(null);
    }

    @Override
    public List<Dish> getAll(int admin_id) {
        return proxyDishRepository.getAll(admin_id);
    }
}
