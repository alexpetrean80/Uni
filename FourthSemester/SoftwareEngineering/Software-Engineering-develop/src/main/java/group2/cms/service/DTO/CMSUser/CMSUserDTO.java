package group2.cms.service.DTO.CMSUser;


import group2.cms.domain.CMSUser;
import group2.cms.service.DTO.EntityDTO;
import lombok.*;

@EqualsAndHashCode
@ToString
@Builder(toBuilder = true)
@Getter
public class CMSUserDTO extends EntityDTO<CMSUser> {
    private Long id;
    private String fullName;
    private String username;
    private String email;
    private String password;
}
