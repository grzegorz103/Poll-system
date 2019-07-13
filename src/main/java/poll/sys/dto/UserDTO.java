package poll.sys.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.StringUtils;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO
{
        private Long ID;

        @NotNull
        @NotBlank
        @Length (min = 4, max = 30)
        private String username;

        @NotNull
        @NotBlank
        @Length (min = 4)
        private String password;

        @NotNull
        @NotBlank
        @Length (min = 4)
        private String confirmPassword;

        @Email
        @NotNull
        @NotBlank
        private String email;

        private Boolean locked;

        @AssertTrue
        public boolean isPasswordEqual ()
        {
                return password.equals( confirmPassword );
        }
}
