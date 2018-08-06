package Hub;

import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sun.security.krb5.internal.crypto.Des;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class new_print_controller {

    /** Queue */
    private ArrayList<File> temporary_file_queue = new ArrayList<File>();

    /** Warning pop up */
    Alert file_limit_reached = new Alert(Alert.AlertType.INFORMATION, "File limit reached for upload.", ButtonType.OK);

    /** Used to pass files between methods */
    private File temp_file;

    /** True of button contains file */
    private Boolean upload_btn_1_filled = Boolean.FALSE;
    private Boolean upload_btn_2_filled = Boolean.FALSE;
    private Boolean upload_btn_3_filled = Boolean.FALSE;
    private Boolean upload_btn_4_filled = Boolean.FALSE;

    @FXML
    public GridPane new_print_pane;
    @FXML
    public Button upload_btn_1;
    @FXML
    public Button upload_btn_2;
    @FXML
    public Button upload_btn_3;
    @FXML
    public Button upload_btn_4;

    @FXML
    public TextField user_input_first_name;
    @FXML
    public TextField user_input_last_name;
    @FXML
    public TextField user_input_email;

    /////////////// NEED TO MOVE THIS TO A NEW CLASS. NAVIGATION.JAVA ///////////////////
    /////////////// NEED TO MOVE THIS TO A NEW CLASS. NAVIGATION.JAVA ///////////////////
    /////////////// NEED TO MOVE THIS TO A NEW CLASS. NAVIGATION.JAVA ///////////////////
    /////////////// NEED TO MOVE THIS TO A NEW CLASS. NAVIGATION.JAVA ///////////////////
    /**
     * Changes the root scene back to the main hub
     */
    public void returnToHub() {
        try {
            Stage stage = (Stage) new_print_pane.getScene().getWindow();
            System.out.println();
            stage.getScene().setRoot(FXMLLoader.load(getClass().getResource("main_hub.fxml")));
            stage.setFullScreen(true);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
    /////////////// NEED TO MOVE THIS TO A NEW CLASS. NAVIGATION.JAVA ///////////////////
    /////////////// NEED TO MOVE THIS TO A NEW CLASS. NAVIGATION.JAVA ///////////////////
    /////////////// NEED TO MOVE THIS TO A NEW CLASS. NAVIGATION.JAVA ///////////////////
    /////////////// NEED TO MOVE THIS TO A NEW CLASS. NAVIGATION.JAVA ///////////////////

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
                upload_btn.setId("upload_btn_" + Integer.toString(btn_number));
            }
        }
    }

    /**
     * Submits the order as an SQL query to the database.
     * Clears the hubs temporary queue of prints.
     * Returns to the main screen of the hub.
     */
    public void submitOrder() {
        createPrintQuery();
        temporary_file_queue.clear();
        returnToHub();
    }

    /**
     * Creates and sends a query to the database with all of the
     * needed user inputs.
     */
    public void createPrintQuery() {
        System.out.println("Generating query to submit....");

        // make sure that all of the text boxes are filled
        if (user_input_first_name != null
                & user_input_last_name != null
                & user_input_email != null) {

            String query = "INSERT INTO prints (last_name, first_name, email) VALUES (?,?,?)";
            System.out.println("Query skeleton created.");


            // Connect to and send query to database
            try {
                System.out.println("Connecting to database....");
                Connection myConn = connectToDatabase();

                // Check if connection exists.
                // If no, try to reconnect 5 times before quitting.
                if (myConn != null) {
                    System.out.println("Adding meat to query bones....");
                    PreparedStatement final_statement = myConn.prepareStatement(query);
                    final_statement.setString(1,user_input_last_name.getText());
                    final_statement.setString(2, user_input_first_name.getText());
                    final_statement.setString(3, user_input_email.getText());
                    final_statement.execute();
                    System.out.println("Query executed.");
                    System.out.println("Killing connection to database...:(");
                } else {
                    for (int i = 0; i < 5; i++) {
                        myConn = connectToDatabase();
                        if (myConn != null) {
                            throw new Exception("Database connection not stable. Check and try again.");
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Connection failed. Check connection and try again.");
                e.printStackTrace();
            }
        }

        clearTemporaryQueue();
        returnToHub();
    }

    /**
     * Creates a connection to the local mySQL database.
     * Used as a helper method for the createPrintQuery function.
     */

    private Connection connectToDatabase() {
        try {
            String url = "jdbc:mysql://localhost:3306/Tech_Showcase?autoReconnect=true&useSSL=false";
            String user = "root";
            String password = "*** *** *** ***";

            // Create connection
            Connection myConn = DriverManager.getConnection(url, user, password);
            if (myConn != null) {
                System.out.println("Connection success.");
                return myConn;
            } else {
                System.out.println("Connection failed. Check connection and try again.");
            }
        } catch (Exception e) {
            System.out.println("Connection failed. Check connection and try again.");
            e.printStackTrace();
        }
        return null;
    }
}
