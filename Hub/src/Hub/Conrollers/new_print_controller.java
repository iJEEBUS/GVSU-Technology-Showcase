package Hub.Conrollers;

import Hub.Database;
import Hub.Navigation;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class new_print_controller {

    @FXML
    public GridPane
            new_print_pane;
    @FXML
    public TextField
            user_input_first_name,
            user_input_last_name,
            user_input_email,
            class_name_input;
    @FXML
    public Button
            upload_btn_1,
            upload_btn_2,
            upload_btn_3,
            upload_btn_4,
            upload_class_file_btn;
    @FXML
    public ToggleButton
            class_agree,
            class_disagree;
    @FXML
    public Label
            terms_and_conditions_text;

    /** Queue */
    private ArrayList<File> temporary_file_queue = new ArrayList<File>();
    /** Warning pop up */
    Alert file_limit_reached = new Alert(Alert.AlertType.INFORMATION, "File limit reached for upload.", ButtonType.OK);
    /** Used to pass files between methods */
    private File temp_file;
    /** True if button currently holds a file */
    private Boolean upload_btn_1_filled = Boolean.FALSE;
    private Boolean upload_btn_2_filled = Boolean.FALSE;
    private Boolean upload_btn_3_filled = Boolean.FALSE;
    private Boolean upload_btn_4_filled = Boolean.FALSE;
    private Boolean class_file_btn_filled = Boolean.FALSE;

    /**
     * Code that executes when the pane is loaded.
     */
    public void initialize() {
        String terms_and_conditions = getTermsAndConditions();
        terms_and_conditions_text.setText(terms_and_conditions);
        disableClassPane();
    }

    /**
     * Used to disable the inputs in the class pane.
     * Active when the "No" button is toggled.
     */
    public void disableClassPane() {
        class_name_input.setEditable(false);
        class_name_input.setDisable(true);
        upload_class_file_btn.setDisable(true);
        class_disagree.setId("class_disagree_active");

    }

    /**
     * Used to enable the inputs in the class pane.
     * Active when the "Yes" button is toggled.
     */
    public void enableClassPane() {
        class_name_input.setEditable(true);
        class_name_input.setDisable(false);
        upload_class_file_btn.setDisable(false);
        class_disagree.setId("class_disagree");
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
        nav.returnToHub(new_print_pane);
    }

    /**
     * Calls on handleFile helper method to get file from user.
     * Sets the second buttons new icon if successful.
     */
    public void getFirstFile() {
        if (upload_btn_1_filled == Boolean.FALSE) {
            if (handleFile() == Boolean.TRUE){
                upload_btn_1.setText(temp_file.getName());
                upload_btn_1.setId("file_loaded_btn");
                upload_btn_1_filled = Boolean.TRUE;
            }
        } else {
            removeFile(1);
            upload_btn_1_filled = Boolean.FALSE;
        }
    }

    /**
     * Calls on handleFile helper method to get file from user.
     * Sets the second buttons new icon if successful.
     */
    public void getSecondFile() {
        if (upload_btn_2_filled == Boolean.FALSE) {
            if (handleFile() == Boolean.TRUE) {
                upload_btn_2.setText(temp_file.getName());
                upload_btn_2.setId("file_loaded_btn");
                upload_btn_2_filled = Boolean.TRUE;
            }
        } else {
            removeFile(2);
            upload_btn_2_filled = Boolean.FALSE;
        }
    }

    /**
     * Calls on handleFile helper method to get file from user.
     * Sets the second buttons new icon if successful.
     */
    public void getThirdFile() {
        if (upload_btn_3_filled == Boolean.FALSE) {
            if (handleFile() == Boolean.TRUE){
                upload_btn_3.setText(temp_file.getName());
                upload_btn_3.setId("file_loaded_btn");
                upload_btn_3_filled = Boolean.TRUE;
            }
        } else {
            removeFile(3);
            upload_btn_3_filled = Boolean.FALSE;
        }
    }

    /**
     * Calls on handleFile helper method to get file from user.
     * Sets the second buttons new icon if successful.
     */
    public void getFourthFile() {
        if (upload_btn_4_filled == Boolean.FALSE) {
            if (handleFile() == Boolean.TRUE){
                upload_btn_4.setText(temp_file.getName());
                upload_btn_4.setId("file_loaded_btn");
                upload_btn_4_filled = Boolean.TRUE;
            }
        } else {
            removeFile(4);
            upload_btn_4_filled = Boolean.FALSE;
        }
    }

    /**
     * Calls on handleFile helper method to get file from user.
     * Sets the class file buttons new icon if successful.
     */
    public void getClassfile() {
        if (class_file_btn_filled == Boolean.FALSE) {
            if (handleFile() == Boolean.TRUE){
                upload_class_file_btn.setText(temp_file.getName());
                upload_class_file_btn.setId("file_loaded_btn");
                class_file_btn_filled = Boolean.TRUE;
            }
        } else {
            removeFile(5);
            class_file_btn_filled = Boolean.FALSE;
        }

    }

    /**
     * Prompts the user to open the desired file one at a time.
     * Allows up to 4 files to be uploaded to the temporary queue.
     * Displays a warning when the queue length has been reached.
     */
    private Boolean handleFile() {

        // Clear temp file
        temp_file = null;

        // The entire window since it is a fullscreen
        Stage stage = (Stage) new_print_pane.getScene().getWindow();

        // Check length/display warning else add file to the queue
        if (temporary_file_queue.toArray().length >= 4) {

            if (file_limit_reached.getOwner() != stage)
                file_limit_reached.initOwner(stage);
            file_limit_reached.showAndWait();
            return Boolean.FALSE;

        } else {

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("File to Upload");
            File file = fileChooser.showOpenDialog(stage);
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            temporary_file_queue.add(file);
            temp_file = file;
            return Boolean.TRUE;
        }
    }

    /**
     * Private helper method that will search through the list
     * and remove the file that the button currently holds.
     */
    private void removeFile(int btn) {

        int btn_number = btn;
        Button upload_btn = null;

        // Determine which button to change
        switch (btn_number) {
            case 1:
                upload_btn = upload_btn_1;
                break;
            case 2:
                upload_btn = upload_btn_2;
                break;
            case 3:
                upload_btn = upload_btn_3;
                break;
            case 4:
                upload_btn = upload_btn_4;
                break;
            case 5:
                upload_btn = upload_class_file_btn;
            default:
                break;
        }

        // Loop through queue
        // Delete file if a match is found
        for (File file : temporary_file_queue) {

            String filename = file.getName();
            String btn_txt = upload_btn.getText();

            if (filename.equals(btn_txt)) {
                temporary_file_queue.remove(file);
                upload_btn.setText("Upload");
                if (btn_number <= 4) {
                    upload_btn.setId("upload_btn_" + Integer.toString(btn_number));
                } else {
                    upload_btn.setId("upload_class_file_btn");
                }
            }
        }
    }

    /**
     * Submits the order as an SQL query to the database.
     * Clears the hubs temporary queue of prints.
     * Returns to the main screen of the hub.
     */
    public void submitOrder() {
        String fname = user_input_first_name.getText();
        String lname = user_input_last_name.getText();
        String email = user_input_email.getText();
        Boolean success;

        Database db = new Database();
        db.connectToDatabase();
        success = db.sendPrintQuery(fname, lname, email);

        if (success) {
            temporary_file_queue.clear();
            returnToHub();
        } else {
            System.out.println("Order not submitted. Please try again.");
        }
    }
}


