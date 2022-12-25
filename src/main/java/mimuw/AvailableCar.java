package mimuw;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AvailableCar extends Car {
    private String dayRate;
    private String weekRate;
    private String monthRate;
}
