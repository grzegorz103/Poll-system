package poll.sys.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import poll.sys.services.VoteService;

@RestController
@RequestMapping ("/poll")
public class VoteController
{
        private final VoteService voteService;

        @Autowired
        public VoteController ( VoteService voteService )
        {
                this.voteService = voteService;
        }

        @PostMapping ("/{id}")
        public void vote ( @PathVariable ("id") int id )
        {
                voteService.vote( ( long ) id );
        }
}
