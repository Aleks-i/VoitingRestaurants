package ru.votingrestaurants.topjava20.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.votingrestaurants.topjava20.model.Vote;
import java.time.LocalDate;
import static ru.votingrestaurants.topjava20.VoteTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class VoteServiceTest {

    @Autowired
    private VoteService voteService;

    @Test
    public void save() {
        Vote created = voteService.save(getNewVoteToday(), USER_ID, ADMIN_ID);
        int newId = created.getId();
        Vote newVote = getNewVoteToday();
        newVote.setId(newId);
//        Vote getVote = voteService.getVote(newId, USER_ID, LocalDate.now());
        MEAL_MATCHER.assertMatch(created, newVote);
//        MEAL_MATCHER.assertMatch(getVote, newVote);
    }

    @Test
    public void getVote() {
        Vote vote = voteService.getVote(VOTE_ID, USER_ID, LocalDate.of(2020,8, 25));
        MEAL_MATCHER.assertMatch(vote, VOTE1);
    }

    @Test
    public void getAllVotesOfAdmin() {
        MEAL_MATCHER.assertMatch(voteService.getAllVotesOfAdmin(ADMIN_ID), VOTES_FOR_ADMIN);
    }

    @Test
    public void getAll() {
        MEAL_MATCHER.assertMatch(voteService.getAll(), ALL_VOTES);
    }
}