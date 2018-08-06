package Hub;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class rental_queue_controller {

    @FXML
    public GridPane rental_queue;

    /**
     * Changes the root scene back to the main hub
     */
    public void returnToHub() {
        Navigation nav = new Navigation();
        nav.returnToHub(rental_queue);
    }

    /**
     * Populates the rental queue during first run.
     * Refreshes the rental queue in other uses.
     */
    public void retreiveRentalQueue() throws SQLException {
        Database db = new Database();
        db.connectToDatabase();
        db.kill();
    }
}
