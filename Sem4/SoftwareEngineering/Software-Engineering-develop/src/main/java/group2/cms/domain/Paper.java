package group2.cms.domain;


import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = {"title"})
)
//@ToString
public class Paper extends BaseEntity<Long>{

    private String title;

    private String paperAbstract;
    @ManyToMany
    @JoinTable(name = "paperAuthors", joinColumns = @JoinColumn(name = "authorId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "paperId", referencedColumnName = "id"))
    private List<Author> authorList;

    @ManyToMany(targetEntity = Keyword.class)
    private Set<Keyword> keywordSet;

    public Paper(String title, String paperAbstract, List<Author> authorList, Set<Keyword> keywordSet) {
        this.title = title;
        this.paperAbstract = paperAbstract;
        this.authorList = authorList;
        this.keywordSet = keywordSet;
    }

    @OneToMany
    @PrimaryKeyJoinColumn
    private Set<PaperFile> files;

//private List<String> keywords; TODO: investigate how to store keywords
}
