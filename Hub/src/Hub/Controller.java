package Hub;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.IOException;

public class Controller {

    @FXML
    public GridPane main_hub;

    @FXML
    public GridPane new_print;

    @FXML
    public GridPane rental_queue;

    @FXML
    public GridPane new_rental;

    @FXML
    public GridPane time_clock;

    @FXML
    public Button print_queue_btn;

    @FXML
    public Button new_print_btn;

    @FXML
    public Button rental_queue_btn;

    @FXML
    public Button new_rental_btn;

    @FXML
    public Button time_clock_btn;

    @FXML
    public Button back_btn;

    /**
     * Changes the root scene to the scene of the print queue
     */
    @FXML
    public void showPrintQueue() {
        try {
            Stage stage = (Stage) main_hub.getScene().getWindow();
            stage.getScene().setRoot(FXMLLoader.load(getClass().getResource("print_queue.fxml")));
            stage.setFullScreen(true);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    /**
     * Changes the root scene to the scene of the new print form
     */
    @FXML
    public void showNewPrint() {
        try {
            Stage stage = (Stage) main_hub.getScene().getWindow();
            stage.getScene().setRoot(FXMLLoader.load(getClass().getResource("new_print.fxml")));
            stage.setFullScreen(true);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    /**
     * Changes the root scene to the scene of the rental queue
     */
    @FXML
    public void showRentalQueue() {
        try {
            Stage stage = (Stage) main_hub.getScene().getWindow();
            stage.getScene().setRoot(FXMLLoader.load(getClass().getResource("rental_queue.fxml")));
            stage.setFullScreen(true);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    /**
     * Changes the root scene to the scene of the new rental form
     */
    @FXML
    public void showNewRental() {
        try {
            Stage stage = (Stage) main_hub.getScene().getWindow();
            stage.getScene().setRoot(FXMLLoader.load(getClass().getResource("new_rental.fxml")));
            stage.setFullScreen(true);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    /**
     * Changes the root scene to the scene of the time clock
     */
    @FXML
    public void showTimeClock() {
        try {
            Stage stage = (Stage) main_hub.getScene().getWindow();
            stage.getScene().setRoot(FXMLLoader.load(getClass().getResource("time_clock.fxml")));
            stage.setFullScreen(true);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }


}
