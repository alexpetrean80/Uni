package group2.cms.domain;

import lombok.*;

//@NoArgsConstructor
//@AllArgsConstructor
//@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
public class Chair extends PCMember {
    public Chair() {
    }

    public Chair(CMSUser user, String affiliation, String webpage) {
        super(user, affiliation, webpage);
        this.authority = Authority.Chair;
    }

    public Chair(CMSUser user, String affiliation) {
        super(user, affiliation);
        this.authority = Authority.Chair;
    }

    public Chair(Long id) {
        this.id = id;
    }
}
