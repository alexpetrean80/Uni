package group2.cms.service.DTO.Auth;

import group2.cms.domain.Authority;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthenticatedUserDTO {
    private String userName;
    private Authority authority;
}
