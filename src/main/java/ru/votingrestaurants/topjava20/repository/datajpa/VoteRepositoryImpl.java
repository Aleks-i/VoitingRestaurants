package ru.votingrestaurants.topjava20.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.votingrestaurants.topjava20.model.Vote;
import ru.votingrestaurants.topjava20.repository.VoteRepository;
import ru.votingrestaurants.topjava20.repository.proxyRepository.ProxyAdminRepository;
import ru.votingrestaurants.topjava20.repository.proxyRepository.ProxyUserRepository;
import ru.votingrestaurants.topjava20.repository.proxyRepository.ProxyVoteRepository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class VoteRepositoryImpl implements VoteRepository {

    @Autowired
    private ProxyVoteRepository proxyVoteRepository;

    @Autowired
    private ProxyAdminRepository adminRepository;

    @Autowired
    private ProxyUserRepository proxyUserRepository;

    @Override
    public Vote save(Vote vote, int user_id, int admin_id) {
        LocalDate localDateToday = LocalDate.now();
        if (!vote.isNew() && (getVote(vote.getId(), user_id, vote.getLocalDate()) == null)
                || !vote.getLocalDate().equals(localDateToday)) {
            return null;
        }

        if (!vote.isNew() && vote.getLocalTime().isAfter(LocalTime.of(11, 00, 00))) {
            return null;
        }

        if (vote.getUser_id() != user_id || vote.getAdmin().getId() != admin_id) {
            return null;
        }

        vote.setAdmin(adminRepository.getOne(admin_id));
        vote.setUser_id(proxyUserRepository.getOne(user_id).getId());
        return proxyVoteRepository.save(vote);
    }

    @Override
    public Vote getVote(int id, int user_id, LocalDate localDate) {
        return proxyVoteRepository.getVote(id, user_id, localDate);
    }

    @Override
    public List<Vote> getAllVotesOfAdmin(int admin_id) {
        return proxyVoteRepository.getAllVotesOfAdmin(admin_id);
    }

    @Override
    public List<Vote> getAll() {
        return proxyVoteRepository.findAll();
    }
}
