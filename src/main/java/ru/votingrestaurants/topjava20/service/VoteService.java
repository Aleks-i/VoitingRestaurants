package ru.votingrestaurants.topjava20.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOG = LoggerFactory.getLogger(VoteService.class);

    @Autowired
    private VoteRepository voteRepository;

    public Vote save(Vote vote, int user_id, int admin_id) {
        LOG.info("save Vote {}", vote);
        Assert.notNull(vote, "vote must not be null");
        return checkNotFoundWithId(voteRepository.save(vote, user_id, admin_id), user_id);
    }

    public Vote getVote(int id, int user_id, LocalDate localDate) {
        LOG.info("getVote id {}", id);
        return checkNotFoundWithId(voteRepository.getVote(id, user_id, localDate), id);
    }

    public List<Vote> getAllVotesOfAdmin(int admin_id) {
        LOG.info("getAllVotesOfAdmin admin_id {}", admin_id);
        return voteRepository.getAllVotesOfAdmin(admin_id);
    }

    public List<Vote> getAll() {
        LOG.info("getAll vote");
        return voteRepository.getAll();
    }
}
