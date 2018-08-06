package Hub;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {

    /**
     * Changes the root scene back to the main hub
     */
    public void returnToHub(Pane current_pane) {
        try {
            Stage stage = (Stage) current_pane.getScene().getWindow();
            System.out.println();
            stage.getScene().setRoot(FXMLLoader.load(getClass().getResource("main_hub.fxml")));
            stage.setFullScreen(true);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}
