package ru.votingrestaurants.topjava20;

import ru.votingrestaurants.topjava20.model.Admin;
import ru.votingrestaurants.topjava20.model.Role;
import ru.votingrestaurants.topjava20.model.Vote;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class VoteTestData {
    public static TestMatcher<Vote> VOTE_MATCHER = TestMatcher.usingFieldsWithIgnoringAssertions(Vote.class, "admin");

    public static final int START_SEQ = 100000;
    public static final int VOTE_ID = START_SEQ + 13;
    public static final int USER_ID = START_SEQ + 2;
    public static final int ADMIN_ID = START_SEQ;
    public static final int ADMIN_ID_1 = START_SEQ + 1;

    public static final Admin ADMIN = new Admin(ADMIN_ID, "Admin0", "admin0@yandex.ru", "password0", Role.ADMIN);
    public static final Admin ADMIN1 = new Admin(ADMIN_ID_1, "Admin1", "admin1@yandex.ru", "password1", Role.ADMIN);

    public static final Vote VOTE1 = new Vote(VOTE_ID, LocalDate.of(2020,8, 25)
            , LocalTime.of(10, 00, 00), USER_ID);

    public static final Vote VOTE2 = new Vote(VOTE_ID + 1, LocalDate.of(2020,8, 25)
            , LocalTime.of(13, 00, 00), USER_ID + 1);

    public static final Vote VOTE3 = new Vote(VOTE_ID + 2, LocalDate.of(2020,8, 25)
            , LocalTime.of(10, 00, 00), USER_ID + 2);

    public static final Vote VOTE4 = new Vote(VOTE_ID + 3, LocalDate.of(2020,8, 25)
            , LocalTime.of(10, 00, 00), USER_ID + 3);

    public static final Vote VOTE5 = new Vote(VOTE_ID + 4, LocalDate.of(2020,8, 25)
            , LocalTime.of(10, 00, 00), USER_ID + 4);

    public static final List<Vote> VOTES_FOR_ADMIN = List.of(VOTE1, VOTE2, VOTE3);

    public static final List<Vote> ALL_VOTES = List.of(VOTE1, VOTE2, VOTE3, VOTE4, VOTE5);

    public static Vote getNewVoteToday() {
        Vote newVote = new Vote(null, LocalDate.now(),
                LocalTime.of(10, 30, 45), USER_ID);
        newVote.setAdmin(ADMIN);
        return newVote;
    }

    public static Vote getNewVoteAfterEleven() {
        Vote newVote = new Vote(VOTE_ID, LocalDate.now(),
                LocalTime.of(10, 30, 45), USER_ID);
        newVote.setAdmin(ADMIN1);
        return newVote;
    }

}
