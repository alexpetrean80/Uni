package group2.cms.service.DTO.Paper;

import group2.cms.domain.Paper;
import group2.cms.service.DTO.EntityDTO;
import lombok.*;

import java.util.List;

@Builder(toBuilder = true)
@EqualsAndHashCode
@ToString
@Getter
public class PaperDTO extends EntityDTO<Paper> {
    private Long id;
    private String title;
    private String paperAbstract;

    @Singular
    private List<Long> authorIDs;

    @Singular
    private List<String> keywords;
}
