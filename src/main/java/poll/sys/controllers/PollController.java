package poll.sys.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import poll.sys.dto.PollDTO;
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
        public PollDTO getPollByCode ( @PathVariable String code )
        {
                return pollService.findOneByCode( code );
        }

        @GetMapping
        public Page<PollDTO> getPublicPolls ( Pageable pageable )
        {
                return pollService.findAllPublic( pageable );
        }


        @PreAuthorize ("isAuthenticated()")
        @GetMapping ("/my/all")
        public Page<PollDTO> getUserPolls ( Pageable pageable )
        {
                return pollService.findByUser( pageable );
        }


        @PostMapping
        public PollDTO save ( @RequestBody PollDTO poll )
        {
                return pollService.create( poll );
        }
}
