package ru.votingrestaurants.topjava20.web.restcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.votingrestaurants.topjava20.View;
import ru.votingrestaurants.topjava20.model.Vote;
import ru.votingrestaurants.topjava20.service.VoteService;
import ru.votingrestaurants.topjava20.web.SecurityUtil;

import java.net.URI;
import java.util.List;

import static ru.votingrestaurants.topjava20.util.ValidationUtil.checkNew;
import static ru.votingrestaurants.topjava20.web.SecurityUtil.authUserId;

@RestController
@RequestMapping(value = VoteRestController.REST_URL_VOTES, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteRestController {
    private static final Logger LOG = LoggerFactory.getLogger(VoteRestController.class);
    public static final String REST_URL_VOTES = "/rest/admins/votes";

    @Autowired
    private VoteService voteService;

    @PostMapping(value = "/create/{admin_id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> create(@Validated(View.Web.class) @RequestBody Vote vote, @PathVariable int admin_id) {
        int userId = SecurityUtil.authUserId();
        checkNew(vote);
        LOG.info("create vote {} for user {}", vote, userId);
        Vote createdVote = voteService.save(vote, userId, admin_id);
        URI ofNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL_VOTES + "/{id}")
                .buildAndExpand(createdVote.getId()).toUri();
        return ResponseEntity.created(ofNewResource).body(createdVote);
    }

    @GetMapping("/{admin_id}")
    List<Vote> getAllVotesOfAdmin(@PathVariable int admin_id) {
        LOG.info("getAllVotesOfAdmin admin_id {}",admin_id);
        return voteService.getAllVotesOfAdmin(admin_id);
    }

    @GetMapping
    public List<Vote> getAll() {
        LOG.info("getAllVoteOfAdmins");
        return voteService.getAll();
    }
}
