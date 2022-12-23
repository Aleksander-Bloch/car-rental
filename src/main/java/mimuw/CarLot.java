package mimuw;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CarLot {

    private List<Car> cars = new ArrayList<>();

    public void addCarToLot(Car car) {
        cars.add(car);
    }
}
