package dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DroidDto extends Dto<Long>{
    private double powerUsage;
    private double price;
    private int batteryTime;
    private String model;
    private boolean driver;
}
