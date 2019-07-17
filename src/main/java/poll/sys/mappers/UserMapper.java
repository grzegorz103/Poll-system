package poll.sys.mappers;

import org.mapstruct.Mapper;
import poll.sys.dto.UserDTO;
import poll.sys.models.User;

@Mapper(componentModel = "spring")
public interface UserMapper
{
        User userDTOtoUser ( UserDTO userDTO );

        UserDTO userToDTO ( User user );
}
