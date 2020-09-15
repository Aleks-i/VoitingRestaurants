package ru.votingrestaurants.topjava20.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.votingrestaurants.topjava20.model.Dish;
import ru.votingrestaurants.topjava20.repository.DishRepository;

import java.time.LocalDate;
import java.util.List;
import static ru.votingrestaurants.topjava20.util.ValidationUtil.checkNotFoundWithId;

@Service
public class DishService {

    @Autowired
    private DishRepository dishRepository;

    public Dish create(Dish dish, int admin_id) {
        Assert.notNull(dish, "dinner must not be null");
        return dishRepository.save(dish, admin_id);
    }

    public void delete(int id, int admin_id) {
        checkNotFoundWithId(dishRepository.delete(id, admin_id), id);
    }

    public Dish getDish(int id, int admin_id) {
        return checkNotFoundWithId(dishRepository.getDish(id, admin_id), id);
    }

    public List<Dish> getAllForAdmin(int admin_id) {
        return dishRepository.getAllForAdmin(admin_id);
    }

}
