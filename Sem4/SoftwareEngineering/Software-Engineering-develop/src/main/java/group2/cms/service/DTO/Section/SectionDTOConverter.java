package group2.cms.service.DTO.Section;

import group2.cms.domain.Chair;
import group2.cms.domain.Section;
import group2.cms.service.DTO.DTOConverter;
import group2.cms.service.DTO.EntitiesDTO;

import java.util.Collection;

public class SectionDTOConverter implements DTOConverter<Section, SectionDTO> {
    @Override
    public Section dtoToEntity(SectionDTO dto) {
//        return new Section(dto.getId(),
//                dto.getRoom(),
//                dto.getCapacity(),
//                dto.getStartDate(),
//                dto.getEndDate(),
//                dto.getTheme());
        return new Section(dto.getId(),
                dto.getRoom(),
                dto.getCapacity(),
                dto.getStartDate(),
                dto.getEndDate(),
                dto.getTheme(),
                new Chair(dto.getId()));
    }

    @Override
    public SectionDTO entityToDto(Section entity) {
        return SectionDTO.builder()
                .id(entity.getId())
                .room(entity.getRoom())
                .capacity(entity.getCapacity())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .theme(entity.getTheme())
                .pcMemberId(entity.getSessionChair().getId())
                .build();
    }

    @Override
    public SectionsDTO entitiesToDTO(Collection<Section> sections) {
        var sectionsDTO = new SectionsDTO();

        sections.stream()
                .map(this::entityToDto)
                .forEach(sectionsDTO::addDTO);

        return sectionsDTO;
    }
}
