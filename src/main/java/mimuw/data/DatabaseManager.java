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

    public Connection connection() {
        return connection;
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
}
