package poll.sys.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import poll.sys.models.Poll;
import poll.sys.services.PollService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping ("/api/poll")
@CrossOrigin
public class PollController
{
        private final PollService pollService;

        @Autowired
        public PollController ( PollService pollService )
        {
                this.pollService = pollService;
        }

        @GetMapping ("/{code}")
        public Poll getPollByCode ( @PathVariable String code )
        {
                return pollService.findOneByCode( code );
        }

        @GetMapping
        public Page<Poll> getPublicPolls ( Pageable pageable )
        {
                return pollService.findAllPublic( pageable );
        }


        @PreAuthorize ("isAuthenticated()")
        @GetMapping ("/my/all")
        public Page<Poll> getUserPolls ( Pageable pageable )
        {
                return pollService.findByUser( pageable );
        }


        @PostMapping
        public Poll save ( @RequestBody Poll poll )
        {
                return pollService.create( poll );
        }
}
