package group2.cms.service.DTO.CMSUser;

import group2.cms.domain.CMSUser;
import group2.cms.service.DTO.DTOConverter;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class CMSUserDTOConverter implements DTOConverter<CMSUser, CMSUserDTO> {
    @Override
    public CMSUser dtoToEntity(CMSUserDTO dto) {
        var user = new CMSUser(
                dto.getFullName(),
                dto.getEmail(),
                dto.getUsername(),
                dto.getPassword()
        );
        if(dto.getId() != null)
            user.setId(dto.getId());
        return user;
    }

    @Override
    public CMSUserDTO entityToDto(CMSUser user) {
        return CMSUserDTO.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }

    @Override
    public CMSUsersDTO entitiesToDTO(Collection<CMSUser> users) {
        var usersDTO = new CMSUsersDTO();

        users.stream()
                .map(this::entityToDto)
                .forEach(usersDTO::addDTO);

        return usersDTO;
    }
}
