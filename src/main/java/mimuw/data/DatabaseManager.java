package mimuw.data;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import mimuw.Car;
import mimuw.CarLot;
import mimuw.User;
import org.springframework.beans.CachedIntrospectionResults;

import java.sql.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class DatabaseManager {
    private static DatabaseManager instance = null;
    private Connection connection = null;
    private Session session = null;

    private DatabaseManager() {
    }

    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    private ResourceBundle getResourceBundle() {
        Locale locale = new Locale("pl", "PL");
        return ResourceBundle.getBundle("dbconfig", locale);
    }

    private void forwardPort() throws JSchException {
        ResourceBundle bundle = getResourceBundle();
        // SSH tunnel parameters.
        String sshHost = bundle.getString("db.sshHost");
        String sshUser = bundle.getString("db.sshUser");
        String sshPassword = bundle.getString("db.sshPassword");
        int sshPort = Integer.parseInt(bundle.getString("db.sshPort"));

        // SSH tunnel setup.
        java.util.Properties config = new java.util.Properties();
        JSch jsch = new JSch();
        session = jsch.getSession(sshUser, sshHost, sshPort);
        session.setPassword(sshPassword);
        config.put("StrictHostKeyChecking", "no");
        config.put("Compression", "yes");
        config.put("ConnectionAttempts", "2");
        session.setConfig(config);
        session.connect();
        System.out.println("SSH connected successfully.");

        int localPort = Integer.parseInt(bundle.getString("db.localPort"));
        int remotePort = Integer.parseInt(bundle.getString("db.remotePort"));
        String remoteHost = bundle.getString("db.remoteHost");

        // Forwarding port.
        int assignedPort = session.setPortForwardingL(localPort, remoteHost, remotePort);
        System.out.println("localhost:" + assignedPort + " -> " + remoteHost + ":" + remotePort);
        System.out.println("Port forwarded successfully.");
    }

    public void connect() {
        // Database parameters.
        ResourceBundle bundle = getResourceBundle();

        try {
            forwardPort();
            try {
                // Port forwarded successfully.
                // Establishing connection to the database.
                Class.forName(bundle.getString("db.driver"));

                // Database connection parameters.
                String url = bundle.getString("db.url");
                String user = bundle.getString("db.dbUser");
                String password = bundle.getString("db.dbPassword");

                // Database connection.
                connection = DriverManager.getConnection(url, user, password);
                System.out.println("Connected to the database successfully.");
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
                System.out.println("Failed to make connection!");
                closeSSH(); // Close SSH tunnel if connection failed.
            }
        } catch (JSchException ex) {
            ex.printStackTrace();
            System.out.println("SSH connection failed.");
        }
    }

    public void closeSSH() {
        if (session != null) {
            session.disconnect();
        }
        System.out.println("SSH disconnected successfully.");
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("Connection closed successfully.");
    }

    public void closeAll() {
        closeConnection();
        closeSSH();
    }

    private void getUserInfo(User user, ResultSet userInfo) throws SQLException {
        // User's client data.
        user.setPesel(userInfo.getString("pesel"));
        user.setAmountSpent(userInfo.getInt("amount_spent"));
        user.setUserRole(userInfo.getString("user_role"));
        user.setStatus(userInfo.getString("status"));

        // Querying user's personal data.
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Person WHERE pesel = ?");
        statement.setString(1, user.getPesel());

        // User's personal data.
        ResultSet nameAndSurname = statement.executeQuery();
        if (!nameAndSurname.next()) return;
        user.setName(nameAndSurname.getString("name"));
        user.setSurname(nameAndSurname.getString("surname"));
    }

    public boolean isValidUser(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Users WHERE login = ? AND password = ?");
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            ResultSet resultSet = statement.executeQuery();

            // Retrieve user data.
            if (resultSet.next()) {
                getUserInfo(user, resultSet);
                return true;
            } else return false;
        } catch (SQLException ex) {
            System.out.println("Failed to create statement.");
            throw new RuntimeException(ex);
        }
    }

    public void populateCarLot(CarLot carLot) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Car WHERE brand = 'Mercedes-Benz' FETCH FIRST 20 ROWS ONLY ");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                var car = new Car();
                car.setCarId(resultSet.getInt("car_id"));
                car.setBrand(resultSet.getString("brand"));
                car.setModel(resultSet.getString("model"));
                car.setHorsepower(resultSet.getInt("horsepower"));
                car.setYear(resultSet.getInt("year"));
                car.setMileage(resultSet.getInt("mileage"));
                car.setGearbox(resultSet.getString("gearbox"));
                car.setCategory(resultSet.getString("category"));
                carLot.addCarToLot(car);
            }

        } catch (SQLException ex) {
            System.out.println("Failed to create statement.");
            throw new RuntimeException(ex);
        }
    }
}
