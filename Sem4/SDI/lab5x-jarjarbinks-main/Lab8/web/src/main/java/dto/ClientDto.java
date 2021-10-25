package dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ClientDto extends Dto<Long> {
    private String name;
    private String address;
    private String phoneNumber;
}
