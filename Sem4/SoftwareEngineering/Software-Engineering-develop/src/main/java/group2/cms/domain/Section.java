package group2.cms.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
//@ToString
public class Section extends BaseEntity<Serializable> {

    @Id
    @GeneratedValue
    private Long id;

    private String room;
    private Integer capacity;
    private LocalDate startDate;
    private LocalDate endDate;
    private String theme;

    @ManyToOne
    @PrimaryKeyJoinColumn
    private PCMember sessionChair;
}
