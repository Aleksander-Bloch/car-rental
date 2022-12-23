package mimuw;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RentedCar extends Car {
    private String startDate;
    private String endDate;
}
