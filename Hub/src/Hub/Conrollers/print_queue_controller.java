package Hub.Conrollers;

import Hub.Database;
import Hub.Navigation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import java.sql.SQLException;
import java.util.List;

public class print_queue_controller {

    @FXML
    public GridPane print_queue_pane, print_table_parent_pane;
    @FXML
    TableView<printQueueOrder> print_queue_table;
    @FXML
    TableColumn<printQueueOrder, String> last_name_col, first_name_col, email_col;

    /**
     * Code to execute when scene is loaded.
     */
    public void initialize() {

        // Try to display the table at load time
        try {
            updateTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
    private ObservableList<printQueueOrder> getPrintQueueOrder() throws SQLException {

        // List of queue objects to be displayed
        ObservableList<printQueueOrder> print_queue = FXCollections.observableArrayList();

        // Database connection
        Database db = new Database();
        db.connectToDatabase();

        // The list of all the queue objects
        List<printQueueOrder> print_queue_orders = db.getprintQueueOrder();


        // Add all instances of an order to the observable list print queue
        for (printQueueOrder order : print_queue_orders) {
            print_queue.add(order);
        }

        // Close db connection
        db.kill();

        return print_queue;
    }

    public void updateTable() throws SQLException {

        // Clear the table
        print_queue_table.getItems().clear();

        // Populate / set column sizes
        last_name_col.setCellValueFactory(new PropertyValueFactory<>("LastName")); // Last name column
        first_name_col.setCellValueFactory(new PropertyValueFactory<>("FirstName")); // First name column
        email_col.setCellValueFactory(new PropertyValueFactory<>("email")); // Email column

        last_name_col.prefWidthProperty().bind(print_queue_table.widthProperty().divide(3));
        first_name_col.prefWidthProperty().bind(print_queue_table.widthProperty().divide(3));
        email_col.prefWidthProperty().bind(print_queue_table.widthProperty().divide(3));


        print_queue_table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Set columns to be non-sortable / non-resizable
        last_name_col.setSortable(false);
        first_name_col.setSortable(false);
        email_col.setSortable(false);
        last_name_col.setResizable(false);
        first_name_col.setResizable(false);
        email_col.setResizable(false);
        first_name_col.setEditable(false);
        last_name_col.setEditable(false);
        email_col.setEditable(false);


        print_queue_table.setItems(getPrintQueueOrder()); // Throws a SQLException
        print_queue_table.refresh();
        print_queue_table.setVisible(true);
    }
}
