package poll.sys.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import poll.sys.models.User;
import poll.sys.models.UserRole;
import poll.sys.repositories.UserRepository;
import poll.sys.repositories.UserRoleRepository;

import java.util.Collections;

@Component
public class RepositoryInitializer
{
        private final UserRoleRepository userRoleRepository;

        private final UserRepository userRepository;

        private final BCryptPasswordEncoder encoder;

        @Autowired
        public RepositoryInitializer ( UserRoleRepository userRoleRepository,
                                       UserRepository userRepository,
                                       BCryptPasswordEncoder encoder )
        {
                this.userRoleRepository = userRoleRepository;
                this.userRepository = userRepository;
                this.encoder = encoder;
        }

        @Bean
        public InitializingBean intializeRepositories ()
        {
                return () -> {
                        UserRole adminRole;
                        UserRole userRole;
                        if ( userRoleRepository.findAll().isEmpty() )
                        {
                                adminRole = new UserRole();
                                adminRole.setUserType( UserRole.UserType.ROLE_ADMIN );
                                userRoleRepository.save( adminRole );

                                userRole = new UserRole();
                                userRole.setUserType( UserRole.UserType.ROLE_USER );
                                userRoleRepository.save( userRole );
                        }

                        if ( userRepository.findAll().isEmpty() )
                        {
                                userRepository.save(
                                        User.builder()
                                                .credentials( false )
                                                .email( "admin@admin.pl" )
                                                .username( "admin" )
                                                .password( encoder.encode( "admin" ) )
                                                .expired( false )
                                                .userRoles( Collections.singleton( userRoleRepository.findUserRoleByUserType( UserRole.UserType.ROLE_ADMIN ) ) )
                                                .locked( false )
                                                .enabled( true )
                                                .build()
                                );

                                userRepository.save(
                                        User.builder()
                                                .credentials( false )
                                                .email( "test@test.pl" )
                                                .username( "test" )
                                                .password( encoder.encode( "test" ) )
                                                .expired( false )
                                                .userRoles( Collections.singleton( userRoleRepository.findUserRoleByUserType( UserRole.UserType.ROLE_USER ) ) )
                                                .locked( false )
                                                .enabled( true )
                                                .build()
                                );
                        }
                };
        }
}
