package group2.cms.service.DTO;

import group2.cms.domain.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@EqualsAndHashCode
@ToString
public abstract class EntityDTO<T extends BaseEntity<?>> implements Serializable { }
