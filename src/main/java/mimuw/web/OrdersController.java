package mimuw.web;

import lombok.extern.slf4j.Slf4j;
import mimuw.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static mimuw.RentedCar.removeRentedCar;

@Slf4j
@Controller
@RequestMapping("dashboard/orders")
@SessionAttributes({"user", "carLot", "alertData"})
public class OrdersController {

    @ModelAttribute(name = "carLot")
    public CarLot carLot() {
        return new CarLot();
    }

    @ModelAttribute(name = "alertData")
    public AlertData alertData() {
        return new AlertData();
    }

    @GetMapping
    public String orders(User user, CarLot carLot, AlertData alertData) {
        carLot.getUserCars(user);
        alertData.clearAlert();

        return "orders";
    }

    @PostMapping("/remove/{carId}")
    public String removeOrder(@PathVariable String carId, AlertData alertData) {

        removeRentedCar(Integer.parseInt(carId));
        alertData.setAlert(AlertType.REMOVE);

        return "redirect:/dashboard/orders";
    }

    @PostMapping("/extend/{carId}")
    public String extendOrder(@PathVariable String carId, User user, CarLot carLot, AlertData alertData) {

        RentedCar rentedCar = (RentedCar) carLot.findCarById(Integer.parseInt(carId));
        rentedCar.extendRentalPeriod(Integer.parseInt(carId), user);
        alertData.setAlert(AlertType.EXTEND);

        return "redirect:/dashboard/orders";
    }
}
