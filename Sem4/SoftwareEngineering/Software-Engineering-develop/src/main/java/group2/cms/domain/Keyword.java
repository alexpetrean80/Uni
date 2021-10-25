package group2.cms.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = {"keyword"})
)
public class Keyword extends BaseEntity<Long>{
    private String keyword;

    @ManyToMany(targetEntity = Paper.class)
    private Set<Paper> paperSet;

    public Keyword(String keyword) {
        this.keyword = keyword;
    }
}
