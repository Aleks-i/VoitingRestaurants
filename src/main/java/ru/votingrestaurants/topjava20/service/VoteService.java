package ru.votingrestaurants.topjava20.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.votingrestaurants.topjava20.model.Vote;
import ru.votingrestaurants.topjava20.repository.VoteRepository;
import java.time.LocalDate;
import java.util.List;
import static ru.votingrestaurants.topjava20.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;

    public Vote save(Vote vote, int userId, int restaurantId) {
        Assert.notNull(vote, "vote must not be null");
        return checkNotFoundWithId(voteRepository.save(vote, userId, restaurantId), userId);
    }

    public void update(Vote vote, int userId, int restaurantId) {
        Assert.notNull(vote, "user must be not null");
        checkNotFoundWithId(voteRepository.save(vote, userId, restaurantId), vote.id());
    }

    public Vote getVote(int id, int userId, LocalDate localDate) {
        return checkNotFoundWithId(voteRepository.getVote(id, userId, localDate), id);
    }

    public List<Vote> getAllVotesOfAdmin(int restaurantId) {
        return voteRepository.getAllVotesOfRestaurant(restaurantId);
    }

    public List<Vote> getAll() {
        return voteRepository.getAll();
    }
}
