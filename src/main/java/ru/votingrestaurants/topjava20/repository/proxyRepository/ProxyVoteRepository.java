package ru.votingrestaurants.topjava20.repository.proxyRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.votingrestaurants.topjava20.model.Vote;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface ProxyVoteRepository extends JpaRepository<Vote, Integer> {

    @Query("SELECT v FROM Vote v WHERE v.restaurant.id=:restaurant_id")
    List<Vote> getAllVotesOfRestaurant(@Param("restaurant_id") int restaurant_id);

    @Query("SELECT v FROM Vote v WHERE v.id=:id AND v.userId=:user_id AND v.localDate=:date")
    Vote getVote(@Param("id") int id, @Param("user_id") int user_id, @Param("date") LocalDate localDate);
}
