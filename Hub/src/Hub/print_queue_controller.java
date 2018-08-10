package Hub;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class print_queue_controller {

    @FXML
    public GridPane print_queue_pane, print_table_parent_pane;

    @FXML
    public AnchorPane print_table_anchor;

    @FXML
    TableView<printQueueOrder> print_queue_table;

    @FXML
    TableColumn last_name_column, first_name_column, email_column;

    /**
     * Changes the root scene back to the main hub
     */
    public void returnToHub() {
        Navigation nav = new Navigation();
        nav.returnToHub(print_queue_pane);
    }

    /**
     * Queries the database and returns an obser
     *
     * @return ObservableList - the print queue data
     * @throws SQLException
     */
    public ObservableList<printQueueOrder> getPrintQueueOrder() throws SQLException {

        // List of queue objects to be displayed
        ObservableList<printQueueOrder> print_queue = FXCollections.observableArrayList();

        // Database connection
        Database db = new Database();
        db.connectToDatabase();

        // The list of all the queue objects
        List<printQueueOrder> print_queue_data = db.getprintQueueOrder();


        // Add all instances of an order to the observable list print queue
        for (printQueueOrder data : print_queue_data) {
            print_queue.add(data);
        }

        // Close db connection
        db.kill();

        return print_queue;
    }


    public void updateTable() throws SQLException {

        // Clear the table
//        print_queue_table.getItems().clear();

        // Last name column
        TableColumn<printQueueOrder, String> last_name_col;
        last_name_col = new TableColumn("Last Name");
        last_name_col.setPrefWidth(200);
        last_name_col.setCellValueFactory(new PropertyValueFactory<>("LastName"));


        // First name column
        TableColumn<printQueueOrder, String> first_name_col;
        first_name_col = new TableColumn("First Name");
        last_name_col.setPrefWidth(200);
        first_name_col.setCellValueFactory(new PropertyValueFactory<>("FirstName"));


        // Email column
        TableColumn<printQueueOrder, String> email_col;
        email_col = new TableColumn("Email");
        last_name_col.setPrefWidth(200);
        email_col.setCellValueFactory(new PropertyValueFactory<>("email"));


        // Adding columns to table
        print_queue_table = new TableView<>();
        print_queue_table.setItems(getPrintQueueOrder()); // Throws a SQLException
        print_queue_table.getColumns().addAll(last_name_col, first_name_col, email_col);

        print_table_pane.getChildren().add(print_queue_table);
        print_queue_table.refresh();


        for (printQueueOrder o : print_queue_table.getItems()) {
            System.out.println(o.getLastName() + " " + o.getFirstName() + " " + o.getEmail());
        }
    }
}
