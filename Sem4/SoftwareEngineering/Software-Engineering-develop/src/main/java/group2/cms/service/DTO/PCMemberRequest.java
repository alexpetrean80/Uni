package group2.cms.service.DTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class PCMemberRequest {
    private Long userID;
    private String affiliation;
    private String website;
}
