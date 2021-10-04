package group2.cms.service.DTO.Section;

import group2.cms.domain.Section;
import group2.cms.service.DTO.EntityDTO;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Builder(toBuilder = true)
@EqualsAndHashCode(callSuper = false)
@ToString
@Getter
public class SectionDTO extends EntityDTO<Section> {
    private Long id;
    private String room;
    private Integer capacity;
    private LocalDate startDate;
    private LocalDate endDate;
    private String theme;
    private Long pcMemberId;
}
