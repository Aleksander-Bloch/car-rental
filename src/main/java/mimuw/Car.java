package mimuw;

import lombok.Data;

@Data
public class Car {
    private int carId;
    private String brand;
    private String model;
    private int horsepower;
    private int year;
    private int mileage;
    private String gearbox;
    private String category;
}
