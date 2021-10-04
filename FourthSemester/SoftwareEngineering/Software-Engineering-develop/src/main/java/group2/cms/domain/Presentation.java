package group2.cms.domain;

import lombok.*;

import javax.persistence.*;
import java.io.File;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Presentation extends BaseEntity<Serializable> {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @PrimaryKeyJoinColumn
    private Paper paper;

    @ManyToOne
    @PrimaryKeyJoinColumn
    private Section section;

    private LocalDate startDate;
    private LocalDate endDate;

    private File presentation;

    public Presentation(Paper paper, Section section, LocalDate startDate, LocalDate endDate, File presentation) {
        //work in progress, not sure if needed
        this.paper=paper;
        this.section=section;
        this.startDate=startDate;
        this.endDate=endDate;
        this.presentation=presentation;
    }
}
