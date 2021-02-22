package com.spil.web.business.util;

import com.spil.web.dto.UserDTO;
import com.spil.web.entity.User;
import org.mapstruct.factory.Mappers;

import java.util.List;

public interface EntityDTOMapper {

    EntityDTOMapper instance = Mappers.getMapper(EntityDTOMapper.class);

    User getUser(UserDTO dto);

    UserDTO getUserDTO(User user);

    List<UserDTO> getUserDTOs(List<User> users);

}
