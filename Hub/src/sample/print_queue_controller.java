package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class print_queue_controller {

    @FXML
    public GridPane print_queue;

    /**
     * Changes the root scene back to the main hub
     */
    public void returnToHub() {
        try {

            Stage stage = (Stage) print_queue.getScene().getWindow();
            System.out.println();
            stage.getScene().setRoot(FXMLLoader.load(getClass().getResource("main_hub.fxml")));
            stage.setFullScreen(true);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

}
