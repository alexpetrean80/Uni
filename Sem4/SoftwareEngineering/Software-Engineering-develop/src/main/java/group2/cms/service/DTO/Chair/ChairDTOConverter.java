package group2.cms.service.DTO.Chair;

import group2.cms.domain.Author;
import group2.cms.domain.Chair;
import group2.cms.domain.PCMember;
import group2.cms.domain.CMSUser;
import group2.cms.domain.Paper;
import group2.cms.service.DTO.Author.AuthorDTO;
import group2.cms.service.DTO.Author.AuthorsDTO;
import group2.cms.service.DTO.DTOConverter;
import group2.cms.service.DTO.EntitiesDTO;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class ChairDTOConverter implements DTOConverter<Chair, ChairDTO> {


    @Override
    public Chair dtoToEntity(ChairDTO dto) {
        var user = new CMSUser(
                dto.getFullName(),
                dto.getEmail(),
                dto.getUsername(),
                dto.getPassword()
        );
        if(dto.getUserID() != null)
            user.setId(dto.getUserID());

        return new Chair(user, dto.getAffiliation(),dto.getWebPage());
    }

    @Override
    public ChairDTO entityToDto(Chair entity) {
        return ChairDTO.builder()
                .userID(entity.getId())
                .fullName(entity.getFullName())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .paperID(entity.getPaper().getId())
                .affiliation(entity.getAffiliation())
                .webPage(entity.getWebPage())
                .build();
    }

    @Override
    public ChairsDTO entitiesToDTO(Collection<Chair> chairs) {
        var chairsDTO = new ChairsDTO();
        chairs.stream()
                .map(this::entityToDto)
                .forEach(chairsDTO::addDTO);
        return chairsDTO;
    }
}
