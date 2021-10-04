package group2.cms.service.DTO.Conference;

import group2.cms.domain.Conference;
import group2.cms.service.DTO.EntityDTO;
import lombok.*;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
@Builder(toBuilder = true)
public class ConferenceDTO extends EntityDTO<Conference> {
    private Long id;
    private String name;
    private String edition;
    private LocalDate startDate;
    private LocalDate endDate;
}
