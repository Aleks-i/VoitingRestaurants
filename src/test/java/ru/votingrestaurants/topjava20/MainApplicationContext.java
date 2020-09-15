package ru.votingrestaurants.topjava20;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.votingrestaurants.topjava20.model.Dish;
import ru.votingrestaurants.topjava20.model.User;
import ru.votingrestaurants.topjava20.model.Vote;
import ru.votingrestaurants.topjava20.service.DishService;
import ru.votingrestaurants.topjava20.service.UserService;
import ru.votingrestaurants.topjava20.service.VoteService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

public class MainApplicationContext {
    public static void main(String[] args) {

        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml",
                "spring/spring-db.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            DishService dishService = appCtx.getBean(DishService.class);
            UserService userService = appCtx.getBean(UserService.class);
            VoteService voteService = appCtx.getBean(VoteService.class);

            dishService.create(new Dish(null, "NewDish", 220.45,
                    LocalDate.now()), 100000);
            userService.create(new User(null, "NewUser", "newauser@mail.ru", "newpass"));
            voteService.save(new Vote(LocalDate.now(),
                    LocalTime.of(10, 30, 45), 100002), 100002, 100000);
        }
    }
}
