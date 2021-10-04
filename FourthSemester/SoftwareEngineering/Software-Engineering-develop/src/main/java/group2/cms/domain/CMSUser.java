package group2.cms.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
//@NoArgsConstructor
//@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
//@ToString(callSuper = true)
public class CMSUser extends BaseEntity<Long>{
    protected String fullName;
    protected String email;
    protected String username;
    protected String password;
    protected Authority authority = Authority.Default;

    public CMSUser(){}

    public CMSUser(String fullName, String email, String username, String password) {
        this.fullName = fullName;
        this.email = email;
        this.username = username;
        this.password = password;
    }
}
