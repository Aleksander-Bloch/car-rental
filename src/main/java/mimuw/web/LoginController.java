package mimuw.web;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
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
    public String login(User user) {
        if (user.getLogin() != null) {
            return "redirect:/dashboard";
        }
        return "login";
    }

    @ModelAttribute(name = "user")
    public User user() {
        return new User();
    }

    @PostMapping
    public String processLogin(@Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            return "login";
        }

        if (!user.retrieveUserDataIfValid()) {
            return "login";
        }

        return "redirect:/dashboard";
    }
}
