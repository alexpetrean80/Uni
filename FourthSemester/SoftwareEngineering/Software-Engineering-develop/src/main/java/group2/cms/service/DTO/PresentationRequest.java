package group2.cms.service.DTO;

import group2.cms.domain.Paper;
import group2.cms.domain.Section;
import lombok.*;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class PresentationRequest implements Serializable {
    private Long presentationID;
    private Paper paper;
    private Section section;
    private LocalDate startDate;
    private LocalDate endDate;
    private File presentation;
}
