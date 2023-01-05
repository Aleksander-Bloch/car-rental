package mimuw.web;

import lombok.extern.slf4j.Slf4j;
import mimuw.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("dashboard/cars")
@SessionAttributes({"user", "carLot", "filterData", "alertData"})
public class AvailableCarsController {

    @ModelAttribute(name = "carLot")
    public CarLot carLot() {
        return new CarLot();
    }

    @ModelAttribute(name = "filterData")
    public FilterData filterData() {
        return new FilterData();
    }

    @ModelAttribute(name = "alertData")
    public AlertData alertData() {
        return new AlertData();
    }

    @GetMapping
    public String availableCars(CarLot carLot, FilterData filter, AlertData alertData, User user) {
        carLot.getAvailableCars(filter, user.getStatus());
        // Clearing filters after each request.
        filter.clearFilters();
        alertData.clearAlert();

        return "cars";
    }

    @PostMapping
    public String applyFilter(FilterData filter) {
        // Just reloading the page with new filter.
        filter.printSelectedFilters();
        return "redirect:/dashboard/cars";
    }

    @PostMapping("/rent/day/{carId}")
    public String rentCarDay(@PathVariable String carId, User user, CarLot carLot, AlertData alertData) {

        // Car object with all necessary data. (id, name, price, etc.)
        AvailableCar carToRent = (AvailableCar) carLot.findCarById(Integer.parseInt(carId));

        // Renting car for 1 day.
        user.rentCar(carToRent, Pricing.DAY);
        alertData.setAlert(AlertType.RENT_DAY);

        return "redirect:/dashboard/cars";
    }

    @PostMapping("/rent/week/{carId}")
    public String rentCarWeek(@PathVariable String carId, User user, CarLot carLot, AlertData alertData) {

        // Car object with all necessary data. (id, name, price, etc.)
        AvailableCar carToRent = (AvailableCar) carLot.findCarById(Integer.parseInt(carId));

        // Renting car for 1 week.
        user.rentCar(carToRent, Pricing.WEEK);
        alertData.setAlert(AlertType.RENT_WEEK);

        return "redirect:/dashboard/cars";
    }

    @PostMapping("/rent/month/{carId}")
    public String rentCarMonth(@PathVariable String carId, User user, CarLot carLot, AlertData alertData) {

        // Car object with all necessary data. (id, name, price, etc.)
        AvailableCar carToRent = (AvailableCar) carLot.findCarById(Integer.parseInt(carId));

        // Renting car for 1 month.
        user.rentCar(carToRent, Pricing.MONTH);
        alertData.setAlert(AlertType.RENT_MONTH);

        return "redirect:/dashboard/cars";
    }
}
