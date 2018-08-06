package Hub;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class print_queue_controller {

    @FXML
    public GridPane print_queue_pane;

    /**
     * Changes the root scene back to the main hub
     */
    public void returnToHub() {
        Navigation nav = new Navigation();
        nav.returnToHub(print_queue_pane);
    }

    /**
     * Populates the print queue during first run.
     * Refreshes the print queue in other uses.
     */
    public void refreshPrintQueue() throws SQLException {
        Database db = new Database();
        db.connectToDatabase();
        db.refreshPrintQueue();
        db.kill();
    }

}
