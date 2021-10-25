package group2.cms.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
// @ToString
public class Conference extends BaseEntity<Long>{

    private String name;
    private String edition;
    private LocalDate startDate;
    private LocalDate endDate;

    //private List<Date> deadlines; TODO: figure out how to represent this
}
