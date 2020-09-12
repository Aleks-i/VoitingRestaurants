package ru.votingrestaurants.topjava20.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.votingrestaurants.topjava20.model.Dish;
import ru.votingrestaurants.topjava20.repository.DishesRepository;
import java.util.List;
import static ru.votingrestaurants.topjava20.util.ValidationUtil.checkNotFoundWithId;

@Service
public class DishesService {
    private static final Logger LOG = LoggerFactory.getLogger(DishesService.class);

    @Autowired
    private DishesRepository dishesRepository;

    public Dish crete(Dish dish, int admin_id) {
        LOG.info("create dish {}", dish);
        Assert.notNull(dish, "dinner must not be null");
        return dishesRepository.save(dish, admin_id);
    }

    public void delete(int id, int admin_id) {
        LOG.info("delete id {}", id);
        checkNotFoundWithId(dishesRepository.delete(id, admin_id), id);
    }

    public Dish getDish(int id, int admin_id) {
        LOG.info("getDish {}", id);
        return checkNotFoundWithId(dishesRepository.getDish(id, admin_id), id);
    }

    public List<Dish> getAll(int admin_id) {
        LOG.info("getAll admin_id {}", admin_id);
        return dishesRepository.getAll(admin_id);
    }

}
