package group2.cms.domain;

import lombok.*;

import javax.persistence.Entity;

@Entity
@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class PCMember extends Author{
    private String affiliation;
    private String webPage;

    public PCMember(){}

    public PCMember(CMSUser user, String affiliation, String webpage){
        super(user);
        this.authority = Authority.PCMember;
        this.affiliation = affiliation;
        this.webPage = webpage;
    }

    public PCMember(CMSUser user, String affiliation){
        super(user);
        this.authority = Authority.PCMember;
        this.affiliation = affiliation;
    }

}
