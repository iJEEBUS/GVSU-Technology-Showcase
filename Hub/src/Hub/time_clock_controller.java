package Hub;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class time_clock_controller {

    @FXML
    public GridPane time_clock_pane;

    /**
     * Changes the root scene back to the main hub
     */
    public void returnToHub() {
        Navigation nav = new Navigation();
        nav.returnToHub(time_clock_pane);
    }

    public void submitTime() {
        
    }
}
