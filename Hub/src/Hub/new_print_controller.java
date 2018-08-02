package Hub;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class new_print_controller {

    @FXML
    public GridPane new_print;

    /**
     * Changes the root scene back to the main hub
     */
    public void returnToHub() {
        try {

            Stage stage = (Stage) new_print.getScene().getWindow();
            System.out.println();
            stage.getScene().setRoot(FXMLLoader.load(getClass().getResource("main_hub.fxml")));
            stage.setFullScreen(true);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public void submitOrder() {

    }

}
