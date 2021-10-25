package group2.cms.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

//@NoArgsConstructor
//@AllArgsConstructor
//@ToString(callSuper = true)
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
public class Listener extends CMSUser{

    @ManyToMany
    @JoinTable(name = "listener_sections", joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "section_id",referencedColumnName = "id"))
    private List<Section> sections;

    public Listener(){}

    public Listener(CMSUser user){
        super(user.getFullName(),
                user.getEmail(),
                user.getUsername(),
                user.getPassword());
        this.sections = new ArrayList<>();
        this.authority = Authority.Listener;
    }

    public void addSection(Section newSection){
        this.sections.add(newSection);
    }
}
