package poll.sys.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import poll.sys.services.VoteService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping ("/poll")
@CrossOrigin
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
                System.out.println("POST");
                voteService.vote( ( long ) id );
        }

        @PutMapping
        public void voteAll( @RequestBody List<Long> votes, HttpServletRequest request ){
                System.out.println("PUT ");
                voteService.voteAll(votes, request.getRemoteAddr());
        }
}
