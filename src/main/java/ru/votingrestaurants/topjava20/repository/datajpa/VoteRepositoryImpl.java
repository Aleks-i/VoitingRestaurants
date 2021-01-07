package ru.votingrestaurants.topjava20.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.votingrestaurants.topjava20.model.Vote;
import ru.votingrestaurants.topjava20.repository.VoteRepository;
import ru.votingrestaurants.topjava20.repository.proxyRepository.ProxyRestaurantRepository;
import ru.votingrestaurants.topjava20.repository.proxyRepository.ProxyUserRepository;
import ru.votingrestaurants.topjava20.repository.proxyRepository.ProxyVoteRepository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public class VoteRepositoryImpl implements VoteRepository {

    @Autowired
    private ProxyVoteRepository proxyVoteRepository;

    @Autowired
    private ProxyUserRepository proxyUserRepository;

    @Autowired
    private ProxyRestaurantRepository proxyRestaurantRepository;

    @Override
    @Transactional
    public Vote save(Vote vote, int userId, int restaurantId) {
        LocalDate localDateToday = LocalDate.now();
        if (!vote.isNew() && (getVote(vote.getId(), userId, vote.getLocalDate()) == null)
                || !vote.getLocalDate().equals(localDateToday)) {
            return null;
        }

        if (!vote.isNew() && vote.getLocalTime().isAfter(LocalTime.of(11, 00, 00))) {
            return null;
        }

        vote.setRestaurant(proxyRestaurantRepository.getOne(restaurantId));
        vote.setUserId(proxyUserRepository.getOne(userId).id());
        return proxyVoteRepository.save(vote);
    }

    @Override
    public Vote getVote(int id, int userId, LocalDate localDate) {
        return proxyVoteRepository.getVote(id, userId, localDate);
    }

    @Override
    public List<Vote> getAllVotesOfRestaurant(int restaurantId) {
        return proxyVoteRepository.getAllVotesOfRestaurant(restaurantId);
    }

    @Override
    public List<Vote> getAll() {
        return proxyVoteRepository.findAll();
    }
}
