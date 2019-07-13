package poll.sys.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import poll.sys.dto.UserDTO;
import poll.sys.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Base64;

@RestController
@RequestMapping ("/api/users")
@CrossOrigin
public class UserController
{
        private final UserService userService;

        @Autowired
        public UserController ( UserService userService )
        {
                this.userService = userService;
        }

        @PostMapping ("/register")
        @PreAuthorize ("isAnonymous()")
        public void addUser ( @RequestBody @Valid UserDTO userDTO,
                              BindingResult bindingResult )
        {
                if ( bindingResult.hasErrors() )
                        return;
                userService.create( userDTO );
        }

        @RequestMapping ("/login")
        @PreAuthorize ("isAnonymous()")
        public boolean login ( @RequestBody UserDTO userDTO )
        {
                return userService.isLoginCorrect( userDTO.getUsername(), userDTO.getPassword() );
        }

        @RequestMapping ("/user")
        public Principal user ( HttpServletRequest request )
        {
                String authToken = request.getHeader( "Authorization" )
                        .substring( "Basic".length() ).trim();
                return () -> new String( Base64.getDecoder()
                        .decode( authToken ) ).split( ":" )[0];
        }

        @GetMapping ("/admin")
        @PreAuthorize ("isAuthenticated()")
        public Boolean hasAdminRole ( @RequestHeader ("Authorization") String auth )
        {
                return false;
                //      return userAuthentication.hasAdminRole( auth );
        }

        @PutMapping ("/{id}")
        @PreAuthorize ("isAuthenticated()")
        public UserDTO update ( @PathVariable ("id") Long id,
                                @RequestBody UserDTO userDTO )
        {
                return null;
                //  return userService.update( id, userDTO );
        }

        @DeleteMapping ("/{id}")
        @Secured ("ROLE_ADMIN")
        public void delete ( @PathVariable ("id") Long id )
        {
                //       userService.delete( id );
        }

}