package group2.cms.service.DTO.Author;

import group2.cms.service.DTO.EntityDTO;
import group2.cms.domain.Author;
import lombok.*;

import java.io.Serializable;


@Builder(toBuilder = true)
@EqualsAndHashCode(callSuper = false)
@ToString
@Getter
public class AuthorDTO extends EntityDTO<Author> implements Serializable {
    private Long userID;
    private String fullName;
    private String username;
    private String email;
    private String password;
    private Long paperID;
}
