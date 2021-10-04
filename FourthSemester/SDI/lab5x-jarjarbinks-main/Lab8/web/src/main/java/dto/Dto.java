package dto;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Dto<ID extends Serializable> implements Serializable {
    public ID id;
}
