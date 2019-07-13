package poll.sys.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.TimeZone;

@RestController
@RequestMapping ("/api/time")
@CrossOrigin
public class TimeController
{
        @GetMapping
        public LocalDateTime getTimeZone ()
        {
                return LocalDateTime.now();
        }
}
