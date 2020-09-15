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

    public Vote save(Vote vote, int user_id, int admin_id) {
        Assert.notNull(vote, "vote must not be null");
        return checkNotFoundWithId(voteRepository.save(vote, user_id, admin_id), user_id);
    }

    public void update(Vote vote, int user_id, int admin_id) {
        Assert.notNull(vote, "user must be not null");
        checkNotFoundWithId(voteRepository.save(vote, user_id, admin_id), vote.id());
    }

    public Vote getVote(int id, int user_id, LocalDate localDate) {
        return checkNotFoundWithId(voteRepository.getVote(id, user_id, localDate), id);
    }

    public List<Vote> getAllVotesOfAdmin(int admin_id) {
        return voteRepository.getAllVotesOfAdmin(admin_id);
    }

    public List<Vote> getAll() {
        return voteRepository.getAll();
    }
}
