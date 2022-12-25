package mimuw.web;

import lombok.extern.slf4j.Slf4j;
import mimuw.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("dashboard/cars")
@SessionAttributes({"user", "carLot", "filterData"})
public class AvailableCarsController {

    @ModelAttribute(name = "carLot")
    public CarLot carLot() {
        return new CarLot();
    }

    @ModelAttribute(name = "filterData")
    public FilterData filterData() {
        return new FilterData();
    }

    @GetMapping
    public String availableCars(User user, CarLot carLot, FilterData filter) {
        if (user.getLogin() == null) {
            return "redirect:/login";
        }

        carLot.getAvailableCars(filter);
        // Clearing filters after each request.
        filter.clearFilters();

        return "cars";
    }

    @PostMapping
    public String applyFilter(FilterData filter) {
        // Just reloading the page with new filter.
        filter.printSelectedFilters();
        return "redirect:/dashboard/cars";
    }

    @PostMapping("/rent/day/{carId}")
    public String rentCarDay(@PathVariable String carId, User user, CarLot carLot) {

        // Car object with all necessary data. (id, name, price, etc.)
        AvailableCar carToRent = (AvailableCar) carLot.findCarById(Integer.parseInt(carId));

        // Renting car for 1 day.
        user.rentCar(carToRent, Pricing.DAY);

        return "redirect:/dashboard/cars";
    }

    @PostMapping("/rent/week/{carId}")
    public String rentCarWeek(@PathVariable String carId, User user, CarLot carLot) {

        // Car object with all necessary data. (id, name, price, etc.)
        AvailableCar carToRent = (AvailableCar) carLot.findCarById(Integer.parseInt(carId));

        // Renting car for 1 week.
        user.rentCar(carToRent, Pricing.WEEK);

        return "redirect:/dashboard/cars";
    }

    @PostMapping("/rent/month/{carId}")
    public String rentCarMonth(@PathVariable String carId, User user, CarLot carLot) {

        // Car object with all necessary data. (id, name, price, etc.)
        AvailableCar carToRent = (AvailableCar) carLot.findCarById(Integer.parseInt(carId));

        // Renting car for 1 month.
        user.rentCar(carToRent, Pricing.MONTH);

        return "redirect:/dashboard/cars";
    }
}
