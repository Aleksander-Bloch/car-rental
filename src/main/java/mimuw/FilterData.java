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
    private List<String> brands = new ArrayList<>();
    private List<String> years = new ArrayList<>();
    private List<String> gearboxes = new ArrayList<>();
    private List<String> categories = new ArrayList<>();
    boolean filtersAvailable = false;

    private String selectedBrand;
    private String selectedYear;
    private String selectedGearbox;
    private String selectedCategory;

    List<String> getFilterOptions(String column, Connection connection) throws SQLException {
        ArrayList<String> options = new ArrayList<>();

        // Retrieving all possible values for given column.
        String sql = "SELECT DISTINCT " + column + " FROM car";
        Statement statement = connection.createStatement();
        var resultSet = statement.executeQuery(sql);

        // Adding all values to the list.
        while (resultSet.next()) {
            options.add(resultSet.getString(1));
        }
        return options;
    }

    public void clearFilters() {
        selectedBrand = null;
        selectedYear = null;
        selectedGearbox = null;
        selectedCategory = null;
        if (filtersAvailable) return;
        var connection = DatabaseManager.getInstance().connection();
        try {
            brands = getFilterOptions("brand", connection);
            years = getFilterOptions("year", connection);
            gearboxes = getFilterOptions("gearbox", connection);
            categories = getFilterOptions("category", connection);

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
    }
}
