package dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ReceiptDto extends Dto<Long> {
    private Long purchaseID;
    double totalPrice;
}
