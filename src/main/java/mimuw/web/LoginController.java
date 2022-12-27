package mimuw.web;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import mimuw.AlertData;
import mimuw.AlertType;
import mimuw.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.validation.Errors;

@Slf4j
@Controller
@RequestMapping("/login")
@SessionAttributes("user")
public class LoginController {

    @GetMapping
    public String login(AlertData alertData) {
        alertData.clearAlert();
        return "login";
    }

    @ModelAttribute(name = "user")
    public User user() {
        return new User();
    }

    @ModelAttribute(name = "alertData")
    public AlertData alertData() {
        return new AlertData();
    }

    @PostMapping
    public String processLogin(@Valid User user, Errors errors) {
        if (errors.hasErrors() || !user.retrieveUserDataIfValid()) {
            return "redirect:/login/failed";
        }

        return "redirect:/dashboard";
    }

    @GetMapping("/logout")
    public String processLogout(AlertData alertData) {
        alertData.setAlert(AlertType.LOGOUT);
        return "redirect:/login";
    }

    @GetMapping("/failed")
    public String processFailedLogin(AlertData alertData) {
        alertData.setAlert(AlertType.FAILED);
        return "redirect:/login";
    }
}
