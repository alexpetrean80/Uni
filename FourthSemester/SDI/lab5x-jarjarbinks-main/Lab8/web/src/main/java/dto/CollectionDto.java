package dto;

import domain.baseEntities.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CollectionDto<ID extends Serializable, D extends Dto<ID>> {
    List<D> elements;
}
