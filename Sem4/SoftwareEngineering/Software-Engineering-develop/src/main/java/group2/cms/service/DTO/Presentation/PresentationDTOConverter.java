package group2.cms.service.DTO.Presentation;

import group2.cms.domain.Presentation;
import group2.cms.service.DTO.DTOConverter;
import group2.cms.service.DTO.EntitiesDTO;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class PresentationDTOConverter implements DTOConverter<Presentation, PresentationDTO> {

    @Override
    public Presentation dtoToEntity(PresentationDTO dto) {
        var presentation = new Presentation(
                dto.getPaper(),
                dto.getSection(),
                dto.getStartDate(),
                dto.getEndDate(),
                dto.getPresentation()
        );
        presentation.setId(dto.getId());
        return presentation;
    }

    @Override
    public PresentationDTO entityToDto(Presentation presentation) {
        return PresentationDTO.builder()
                .id(presentation.getId())
                .paper(presentation.getPaper())
                .section(presentation.getSection())
                .startDate(presentation.getStartDate())
                .endDate(presentation.getEndDate())
                .presentation(presentation.getPresentation())
                .build();
    }

    @Override
    public EntitiesDTO<Presentation, PresentationDTO> entitiesToDTO(Collection<Presentation> entities) {
        return null;
    }
}
