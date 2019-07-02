package poll.sys.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import poll.sys.models.Poll;
import poll.sys.services.PollService;

import java.util.List;

@RestController
@RequestMapping ("/poll")
@CrossOrigin
public class PollController
{
        private final PollService pollService;

        @Autowired
        public PollController ( PollService pollService )
        {
                this.pollService = pollService;
        }

        @GetMapping ("/{id}")
        public Poll getPoll ( @PathVariable long id )
        {
                return pollService.findOne( id );
        }

        @GetMapping
        public List<Poll> getPolls ()
        {
                return pollService.findAll();
        }

        @PostMapping
        public Poll save ( @RequestBody Poll poll )
        {
                return pollService.create( poll );
        }
}
