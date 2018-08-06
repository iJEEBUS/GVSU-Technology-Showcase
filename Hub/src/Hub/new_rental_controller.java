package Hub;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class new_rental_controller {

    @FXML
    public GridPane new_rental_pane;

    @FXML
    ComboBox tech_items;


    @FXML
    public TextField user_input_first_name;
    @FXML
    public TextField user_input_last_name;
    @FXML
    public TextField user_input_email;

    /**
     * Changes the root scene back to the main hub
     */
    public void returnToHub() {
        Navigation nav = new Navigation();
        nav.returnToHub(new_rental_pane);
    }

    /**
     * Connects to database.
     * Creates new order query.
     * Injects into database.
     * Kills connection.
     *
     * @throws SQLException
     */
    public void submitOrder() throws SQLException {
        String fname = user_input_first_name.getText();
        String lname = user_input_last_name.getText();
        String email = user_input_email.getText();
        Boolean success;

        Database db = new Database();
        db.connectToDatabase();
        success = db.sendRentalQuery(fname, lname, email);

        if (success) {
            returnToHub();
        } else {
            System.out.println("Order not submitted. Please try again.");
        }
    }
}
