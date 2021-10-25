package group2.cms.service.DTO.Chair;

import group2.cms.domain.Chair;
import group2.cms.service.DTO.EntityDTO;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;


@Builder(toBuilder = true)
@EqualsAndHashCode(callSuper = false)
@ToString
@Getter
public class ChairDTO extends EntityDTO<Chair> {
    private Long userID;
    private String fullName;
    private String username;
    private String email;
    private String password;
    private Long paperID;
    private String affiliation;
    private String webPage;
}
