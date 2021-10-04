package group2.cms.domain;

import lombok.*;

//@NoArgsConstructor
//@AllArgsConstructor
//@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
public class CoChair extends PCMember{
    public CoChair(){
    }

    public CoChair(CMSUser user, String affiliation, String webpage){
        super(user, affiliation, webpage);
        this.authority = Authority.CoChair;
    }

    public CoChair(CMSUser user, String affiliation){
        super(user, affiliation);
        this.authority = Authority.CoChair;
    }
}
