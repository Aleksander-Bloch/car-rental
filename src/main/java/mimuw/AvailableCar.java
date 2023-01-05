package mimuw;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AvailableCar extends Car {
    private int dayRate;
    private int weekRate;
    private int monthRate;
}
