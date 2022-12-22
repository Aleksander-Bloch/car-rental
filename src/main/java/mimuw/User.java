package mimuw;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class User {

    @NotNull
    @Size(min = 1, max = 15, message = "Username must be between 1 and 15 characters")
    private String login;

    @NotNull
    @Size(min = 1, max = 15, message = "Password must be between 1 and 15 characters")

    private String password;

    private String pesel;

    private String name;

    private String surname;

    private int amountSpent;

    private String userRole;

    private String status;
}
