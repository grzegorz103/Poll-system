package poll.sys.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import poll.sys.dto.UserDTO;
import poll.sys.models.User;

public interface UserService extends UserDetailsService
{
        User create ( UserDTO userDTO );

        boolean isLoginCorrect ( String login, String password );
}
