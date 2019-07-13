package poll.sys.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table (name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails
{
        @Id
        @GeneratedValue (strategy = GenerationType.AUTO)
        private Long id;

        @Column (name = "username")
        private String username;

        @Column (name = "password")
        private String password;

        @Column (name = "email")
        private String email;

        @Column (name = "expired")
        private boolean expired;

        @Column (name = "locked")
        private boolean locked;

        @Column (name = "credentials")
        private boolean credentials;

        @Column (name = "enabled")
        private boolean enabled;

        @ManyToMany (fetch = FetchType.EAGER)
        @JoinTable (name = "users_roles",
                joinColumns = @JoinColumn (name = "user_id"),
                inverseJoinColumns = @JoinColumn (name = "role_id"))
        private Set<UserRole> userRoles;

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities ()
        {
                return userRoles.stream()
                        .map( e -> new SimpleGrantedAuthority( e.getUserType().name() ) )
                        .collect( Collectors.toSet() );
        }

        @Override
        public String getPassword ()
        {
                return password;
        }

        @Override
        public String getUsername ()
        {
                return username;
        }

        @Override
        public boolean isAccountNonExpired ()
        {
                return !expired;
        }

        @Override
        public boolean isAccountNonLocked ()
        {
                return !locked;
        }

        @Override
        public boolean isCredentialsNonExpired ()
        {
                return !credentials;
        }

        @Override
        public boolean isEnabled ()
        {
                return enabled;
        }
}
