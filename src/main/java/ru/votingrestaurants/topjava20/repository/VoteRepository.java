package ru.votingrestaurants.topjava20.repository;

import ru.votingrestaurants.topjava20.model.Vote;
import java.time.LocalDate;
import java.util.List;

public interface VoteRepository {

    Vote save(Vote vote, int userId, int restaurantId);

    Vote getVote(int id, int userId, LocalDate localDate);

    List<Vote> getAllVotesOfRestaurant(int restaurantId);

    List<Vote> getAll();
}
