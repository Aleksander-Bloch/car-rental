package mimuw;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import mimuw.data.DatabaseManager;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

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

    private void getUserData(ResultSet userInfo, Connection connection) throws SQLException {
        // User's client data.
        pesel = userInfo.getString("pesel");
        amountSpent = userInfo.getInt("amount_spent");
        userRole = userInfo.getString("user_role");
        status = userInfo.getString("status");

        // Querying user's personal data.
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Person WHERE pesel = ?");
        statement.setString(1, pesel);

        // User's personal data.
        ResultSet nameAndSurname = statement.executeQuery();
        if (!nameAndSurname.next()) return;
        name = nameAndSurname.getString("name");
        surname = nameAndSurname.getString("surname");
    }

    // Validate user's login and password and fill rest of the information.
    // Returns true if given data matches some record in the database, false otherwise.
    public boolean retrieveUserDataIfValid() {
        System.out.println("User " + login + " is trying to log in. Querying for his data...");
        var connection = DatabaseManager.getInstance().connection();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Users WHERE login = ? AND password = ?");
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet userInfo = statement.executeQuery();

            // Retrieve user data.
            if (userInfo.next()) {
                System.out.println("User found. Redirecting to dashboard.");
                getUserData(userInfo, connection);
                return true;
            } else {
                System.out.println("User not found. Redirecting to login page.");
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("Failed to create statement.");
            throw new RuntimeException(ex);
        }
    }

    public void rentCar(AvailableCar car, Pricing days) {
        int amount = switch (days) {
            case DAY -> Integer.parseInt(car.getDayRate());
            case WEEK -> Integer.parseInt(car.getWeekRate());
            case MONTH -> Integer.parseInt(car.getMonthRate());
        };
        // Updating user's info to properly display amount spent in dashboard.
        amountSpent += amount;
        // TODO: Update user status if needed.

        var connection = DatabaseManager.getInstance().connection();
        try {
            // Updating user's amount spent.
            PreparedStatement statement = connection.prepareStatement("UPDATE Users SET amount_spent = ? WHERE login = ?");
            statement.setInt(1, amountSpent + amount);
            statement.setString(2, login);
            statement.executeUpdate();

            // Inserting new rent into database.
            String sql = "INSERT INTO Rental VALUES(?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, login);
            statement.setInt(2, car.getCarId());

            // Getting current date.
            Date today = new Date(System.currentTimeMillis());
            Date returnDay = new Date(today.getTime() + days.getDays() * 24 * 60 * 60 * 1000);

            statement.setDate(3, today);
            statement.setDate(4, returnDay);

            // Executing update on database.
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Failed to create statement.");
            throw new RuntimeException(e);
        }
    }
}
