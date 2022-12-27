package mimuw;

import lombok.Data;
import mimuw.data.DatabaseManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Data
public class FilterData {
    public final static String NULL = "null";
    private List<String> brands = new ArrayList<>();
    private List<String> years = new ArrayList<>();
    private List<String> gearboxes = new ArrayList<>();
    private List<String> categories = new ArrayList<>();
    private int minHP = -1;
    private int maxHP = -1;
    private int minMileage = -1;
    private int maxMileage = -1;
    boolean filtersAvailable = false;

    private String selectedBrand = NULL;
    private String selectedYear = NULL;
    private String selectedGearbox = NULL;
    private String selectedCategory = NULL;
    private int selectedMinHorsepower = -1;
    private int selectedMaxMileage = -1;

    int getFilterRangeBounds(String column, Connection connection, boolean isLowerBound) throws SQLException {
        String whichBound = "MIN";
        if (!isLowerBound) {
            whichBound = "MAX";
        }
        String sql = "SELECT " + whichBound + "(" + column + ")" + " FROM car WHERE car_id NOT IN (SELECT car_id FROM rental)";
        Statement statement = connection.createStatement();
        var resultSet = statement.executeQuery(sql);
        resultSet.next();
        return resultSet.getInt(1);
    }

    List<String> getFilterOptions(String column, Connection connection) throws SQLException {
        ArrayList<String> options = new ArrayList<>();

        // Retrieving all possible values for given column.
        String sql = "SELECT DISTINCT " + column + " FROM car ORDER BY " + column;
        Statement statement = connection.createStatement();
        var resultSet = statement.executeQuery(sql);

        // Adding all values to the list.
        while (resultSet.next()) {
            options.add(resultSet.getString(1));
        }
        return options;
    }

    public void clearFilters() {
        selectedBrand = NULL;
        selectedYear = NULL;
        selectedGearbox = NULL;
        selectedCategory = NULL;
        selectedMinHorsepower = -1;
        selectedMaxMileage = -1;
        if (filtersAvailable) return;
        var connection = DatabaseManager.getInstance().connection();
        try {
            brands = getFilterOptions("brand", connection);
            years = getFilterOptions("year", connection);
            gearboxes = getFilterOptions("gearbox", connection);
            categories = getFilterOptions("category", connection);
            minHP = getFilterRangeBounds("horsepower", connection, true);
            maxHP = getFilterRangeBounds("horsepower", connection, false);
            minMileage = getFilterRangeBounds("mileage", connection, true);
            System.out.println("Min mileage: " + minMileage);
            maxMileage = getFilterRangeBounds("mileage", connection, false);
            System.out.println("Max mileage: " + maxMileage);

            // Variable preventing from retrieving filter options each time.
            filtersAvailable = true;
        } catch (SQLException e) {
            System.out.println("Failed to create statement");
            throw new RuntimeException(e);
        }
    }

    public void printSelectedFilters() {
        System.out.println("Selected brand: " + selectedBrand);
        System.out.println("Selected year: " + selectedYear);
        System.out.println("Selected gearbox: " + selectedGearbox);
        System.out.println("Selected category: " + selectedCategory);
        System.out.println("Selected minimum horsepower: " + selectedMinHorsepower);
        System.out.println("Selected maximum mileage: " + selectedMaxMileage);
    }
}
