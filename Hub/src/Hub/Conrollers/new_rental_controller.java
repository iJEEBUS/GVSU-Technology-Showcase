package Hub.Conrollers;

import Hub.Database;
import Hub.Navigation;
import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class new_rental_controller {

    @FXML
    public GridPane new_rental_pane;

    @FXML
    ComboBox tech_items;
    @FXML
    DatePicker return_date_picker;
    @FXML
    public TextField user_input_first_name;
    @FXML
    public TextField user_input_last_name;
    @FXML
    public TextField user_input_email;
    @FXML
    public TextArea additional_components;
    @FXML
    public TextArea comments;
    @FXML
    public RadioButton signature_yes;
    @FXML
    public Label terms_and_conditions_text;

    /**
     * Code to execute when scene is loaded.
     */
    public void initialize() {
        String terms_and_conditions = getTermsAndConditions();
        terms_and_conditions_text.setText(terms_and_conditions);
    }

    /**
     * Helper method that will read in the terms and conditions from the file
     * in the Resource folder.
     * Used by the terms_and_conditions_text label to add text to the label.
     *
     * @return terms_and_conditions - String - the terms and conditions
     */
    private String getTermsAndConditions() {

        String file_location = "/Users/user/Desktop/Programming/GitHub/GVSU-Technology-Showcase/Hub/src/Hub/terms_and_conditions.txt";

        String terms_and_conditions = "";

        try {
            FileReader fileReader = new FileReader(file_location);

            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuffer stringBuffer = new StringBuffer();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
                stringBuffer.append("\n");
            }

            terms_and_conditions = stringBuffer.toString();

            fileReader.close();
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return terms_and_conditions;
    }

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
        String technology = (String) tech_items.getValue();
        String components = additional_components.getText();
        String add_comments = comments.getText();
        Boolean signature = signature_yes.isPressed();

        LocalDate return_date = return_date_picker.getValue();
        LocalDate checkout_date = LocalDate.now(); // the time of the submission
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("mm-dd-yyyy");
        String formatted_return_date = return_date.format(formatter);
        String formatted_checkout_date = checkout_date.format(formatter);

        Boolean success;

        Database db = new Database();
        db.connectToDatabase();
        success = db.sendRentalQuery(fname, lname, email, technology, formatted_return_date, components, add_comments, signature);

        if (success) {
            returnToHub();
        } else {
            System.out.println("Order not submitted. Please try again.");
        }
    }
}
