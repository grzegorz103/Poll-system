package poll.sys.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
        @Override
        protected void configure ( HttpSecurity http ) throws Exception
        {
                http.csrf().disable()
                        .authorizeRequests()
                        .antMatchers( "/**" )
                        .permitAll()
                        .anyRequest()
                        .authenticated()
                        .and()
                        .httpBasic();
        }

        @Bean
        public BCryptPasswordEncoder encoder ()
        {
                return new BCryptPasswordEncoder();
        }
}
