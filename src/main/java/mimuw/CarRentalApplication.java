package mimuw;

import mimuw.data.DatabaseManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CarRentalApplication {

    public static void main(String[] args) {
        DatabaseManager db = DatabaseManager.getInstance();
        SpringApplication.run(CarRentalApplication.class, args);
        db.connect();
        Runtime.getRuntime().addShutdownHook(new Thread(db::closeAll));
    }

}
