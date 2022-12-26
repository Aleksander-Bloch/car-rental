package mimuw.web;

import lombok.extern.slf4j.Slf4j;
import mimuw.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

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

    @PostMapping("/logout")
    public String logout(SessionStatus sessionStatus) {
        System.out.println("Logging out...");
        sessionStatus.setComplete();
        return "redirect:/login";
    }
}
