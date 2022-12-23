package mimuw.web;

import lombok.extern.slf4j.Slf4j;
import mimuw.CarLot;
import mimuw.User;
import mimuw.data.DatabaseManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Slf4j
@Controller
@RequestMapping("/dashboard")
@SessionAttributes({"user", "carLot"})
public class DashboardController {

    @ModelAttribute(name = "carLot")
    public CarLot carLot() {
        return new CarLot();
    }

    @GetMapping
    public String dashboard(User user, CarLot carLot) {
        if (user.getLogin() == null) {
            return "redirect:/login";
        }

        DatabaseManager db = DatabaseManager.getInstance();
        db.populateCarLot(carLot);

        return "dashboard";
    }

}
