package mimuw.web;

import lombok.extern.slf4j.Slf4j;
import mimuw.CarLot;
import mimuw.RentedCar;
import mimuw.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static mimuw.RentedCar.removeRentedCar;

@Slf4j
@Controller
@RequestMapping("dashboard/orders")
@SessionAttributes({"user", "carLot"})
public class OrdersController {

    @ModelAttribute(name = "carLot")
    public CarLot carLot() {
        return new CarLot();
    }

    @GetMapping
    public String orders(User user, CarLot carLot) {
        if (user.getLogin() == null) {
            return "redirect:/login";
        }

        carLot.getUserCars(user);

        return "orders";
    }

    @PostMapping("/remove/{carId}")
    public String removeOrder(@PathVariable String carId) {

        removeRentedCar(Integer.parseInt(carId));

        return "redirect:/dashboard/orders";
    }

    @PostMapping("/extend/{carId}")
    public String extendOrder(@PathVariable String carId, User user, CarLot carLot) {

        RentedCar rentedCar = (RentedCar) carLot.findCarById(Integer.parseInt(carId));
        rentedCar.extendRentalPeriod(Integer.parseInt(carId), user);

        return "redirect:/dashboard/orders";
    }
}
