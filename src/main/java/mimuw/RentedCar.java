package mimuw;

import lombok.Data;
import lombok.EqualsAndHashCode;
import mimuw.data.DatabaseManager;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@EqualsAndHashCode(callSuper = true)
@Data
public class RentedCar extends Car {
    private String startDate;
    private String endDate;

    public static void removeRentedCar(int carId) {
        System.out.println("Removing rented car with id: " + carId + " from database...");
        var connection = DatabaseManager.getInstance().connection();
        try {
            String sql = "DELETE FROM rental WHERE car_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, carId);
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Failed to create statement.");
            throw new RuntimeException(ex);
        }
    }

    public static void extendRentalPeriod(int carId) {
        System.out.println("Extending rental period of car with id: " + carId + " by one day");
        var connection = DatabaseManager.getInstance().connection();
        try {
            // TODO: Update money spent by user.
            String sql = "UPDATE rental SET end_date = end_date + 1 WHERE car_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, carId);
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Failed to create statement.");
            throw new RuntimeException(ex);
        }
    }
}
