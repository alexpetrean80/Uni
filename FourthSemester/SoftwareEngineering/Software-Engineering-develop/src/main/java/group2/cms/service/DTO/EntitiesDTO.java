package group2.cms.service.DTO;

import group2.cms.domain.BaseEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;


public abstract class EntitiesDTO<E extends BaseEntity<?>, T extends EntityDTO<E>> implements Serializable {
    private Collection<T> dtos = new ArrayList<>();

    public void addDTO(T dto){
        this.dtos.add(dto);
    }
    public void addDTOs(T... dtos){
        this.dtos.addAll(Arrays.asList(dtos));
    }

    public Iterable<T> getDTOs(){
        return this.dtos;
    }

}
