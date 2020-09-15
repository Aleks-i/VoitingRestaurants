package ru.votingrestaurants.topjava20.repository;

import ru.votingrestaurants.topjava20.model.Vote;
import java.time.LocalDate;
import java.util.List;

public interface VoteRepository {

    Vote save(Vote vote, int user_id, int admin_id);

    Vote getVote(int id, int user_id, LocalDate localDate);

    List<Vote> getAllVotesOfAdmin(int admin_id);

    List<Vote> getAll();
}
