package mimuw;

import lombok.Data;
import mimuw.data.DatabaseManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Data
public class CarLot {
    private List<Car> cars = new ArrayList<>();

    private String getDate(String date) {
        return date.split(" ")[0];
    }

    public void getUserCars(User user) {
        // Clear car lot on reload.
        cars.clear();
        System.out.println("User " + user.getLogin() + " is redirecting to orders page. Querying for his cars...");
        var connection = DatabaseManager.getInstance().connection();
        try {
            // Retrieving user's cars from the database.
            String sql = "SELECT * FROM rental A LEFT JOIN car B ON A.car_id = B.car_id WHERE login = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getLogin());

            // Execute the query and get the result set.
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                var car = new RentedCar();
                // Car data.
                downloadCar(resultSet, car);

                // Rental data.
                car.setStartDate(getDate(resultSet.getString("start_date")));
                car.setEndDate(getDate(resultSet.getString("end_date")));

                cars.add(car);
            }
            System.out.println("Query executed successfully. Found " + cars.size() + " cars.");
        } catch (SQLException ex) {
            System.out.println("Failed to create statement.");
            throw new RuntimeException(ex);
        }
    }

    public void getAvailableCars(FilterData filter) {
        // Clear car lot on reload.
        cars.clear();
        System.out.println("Querying for all available cars...");
        var connection = DatabaseManager.getInstance().connection();
        try {
            // Retrieving user's cars from the database.
            String sql = "SELECT * FROM car WHERE car_id NOT IN (SELECT car_id FROM rental)";
            PreparedStatement statement = connection.prepareStatement(sql);

            // Execute the query and get the result set.
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                var car = new Car();
                // Car data.
                downloadCar(resultSet, car);
                cars.add(car);
            }
            System.out.println("Query executed successfully. Found " + cars.size() + " cars.");
        } catch (SQLException ex) {
            System.out.println("Failed to create statement.");
            throw new RuntimeException(ex);
        }
    }

    private void downloadCar(ResultSet resultSet, Car car) throws SQLException {
        car.setCarId(resultSet.getInt("car_id"));
        car.setBrand(resultSet.getString("brand"));
        car.setModel(resultSet.getString("model"));
        car.setHorsepower(resultSet.getInt("horsepower"));
        car.setYear(resultSet.getInt("year"));
        car.setMileage(resultSet.getInt("mileage"));
        car.setGearbox(resultSet.getString("gearbox"));
        car.setCategory(resultSet.getString("category"));
    }
}
