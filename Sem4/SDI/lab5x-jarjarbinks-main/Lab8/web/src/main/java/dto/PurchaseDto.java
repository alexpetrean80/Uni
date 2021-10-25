package dto;

import domain.Keys.ClientDroidPrimaryKey;
import domain.baseEntities.Client;
import domain.baseEntities.Droid;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PurchaseDto extends Dto<ClientDroidPrimaryKey> {
    private Client client;
    private Droid droid;
    private double totalPrice;
}
