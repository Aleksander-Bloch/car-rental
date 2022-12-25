package mimuw.web;

import lombok.extern.slf4j.Slf4j;
import mimuw.CarLot;
import mimuw.FilterData;
import mimuw.User;
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
}
