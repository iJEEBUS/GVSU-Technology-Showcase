package Hub;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class time_clock_controller {

    @FXML
    public GridPane time_clock;

    /**
     * Changes the root scene back to the main hub
     */
    public void returnToHub() {
        try {
            Stage stage = (Stage) time_clock.getScene().getWindow();
            System.out.println();
            stage.getScene().setRoot(FXMLLoader.load(getClass().getResource("main_hub.fxml")));
            stage.setFullScreen(true);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public void submitTime() {
        
    }
}
