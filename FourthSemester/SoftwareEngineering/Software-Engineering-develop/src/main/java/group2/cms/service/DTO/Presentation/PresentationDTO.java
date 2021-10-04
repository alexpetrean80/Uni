package group2.cms.service.DTO.Presentation;


import group2.cms.domain.Paper;
import group2.cms.domain.Section;
import group2.cms.service.DTO.EntityDTO;
import group2.cms.domain.Presentation;
import lombok.*;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDate;


@Builder(toBuilder = true)
@EqualsAndHashCode(callSuper = false)
@ToString
@Getter
public class PresentationDTO extends EntityDTO<Presentation> implements Serializable {

    private Long id;
    private Paper paper;
    private Section section;
    private LocalDate startDate;
    private LocalDate endDate;
    private File presentation;
}
