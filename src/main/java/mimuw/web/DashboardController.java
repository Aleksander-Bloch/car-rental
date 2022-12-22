package mimuw.web;

import lombok.extern.slf4j.Slf4j;
import mimuw.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Slf4j
@Controller
@RequestMapping("/dashboard")
@SessionAttributes("user")
public class DashboardController {

    @GetMapping
    public String dashboard(User user) {
        if (user.getLogin() == null) {
            return "redirect:/login";
        }
        return "dashboard";
    }

}
