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
    TableColumn<printQueueOrder, String>
            first_name_col,
            last_name_col,
            email_col,
            print_1,
            print_2,
            print_3,
            print_4,
            for_class_col,
            class_name_col,
            class_proof_col,
            t_and_c_col,
            timestamp;
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
        List<printQueueOrder> print_queue_orders = db.getPrintQueueOrder();


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

        // Set columns to non-editable
        first_name_col.setEditable(false);
        last_name_col.setEditable(false);
        email_col.setEditable(false);
        print_1.setEditable(false);
        print_2.setEditable(false);
        print_3.setEditable(false);
        print_4.setEditable(false);
        for_class_col.setEditable(false);
        class_name_col.setEditable(false);
        class_proof_col.setEditable(false);
        t_and_c_col.setEditable(false);
        timestamp.setEditable(false);

        timestamp.setSortable(true); // can sort by submission times


        // Set width of columns
        first_name_col.prefWidthProperty().bind(print_queue_table.widthProperty().divide(12));
        last_name_col.prefWidthProperty().bind(print_queue_table.widthProperty().divide(12));
        email_col.prefWidthProperty().bind(print_queue_table.widthProperty().divide(12));
        print_1.prefWidthProperty().bind(print_queue_table.widthProperty().divide(12));
        print_2.prefWidthProperty().bind(print_queue_table.widthProperty().divide(12));
        print_3.prefWidthProperty().bind(print_queue_table.widthProperty().divide(12));
        print_4.prefWidthProperty().bind(print_queue_table.widthProperty().divide(12));
        for_class_col.prefWidthProperty().bind(print_queue_table.widthProperty().divide(12));
        class_name_col.prefWidthProperty().bind(print_queue_table.widthProperty().divide(12));
        class_proof_col.prefWidthProperty().bind(print_queue_table.widthProperty().divide(12));
        t_and_c_col.prefWidthProperty().bind(print_queue_table.widthProperty().divide(12));
        timestamp.prefWidthProperty().bind(print_queue_table.widthProperty().divide(12));

        // Populate columns
        first_name_col.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        last_name_col.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        email_col.setCellValueFactory(new PropertyValueFactory<>("Email"));
        print_1.setCellValueFactory(new PropertyValueFactory<>("PrintOne"));
        print_2.setCellValueFactory(new PropertyValueFactory<>("PrintTwo"));
        print_3.setCellValueFactory(new PropertyValueFactory<>("PrintThree"));
        print_4.setCellValueFactory(new PropertyValueFactory<>("PrintFour"));
        for_class_col.setCellValueFactory(new PropertyValueFactory<>("ForClass"));
        class_name_col.setCellValueFactory(new PropertyValueFactory<>("ClassName"));
        class_proof_col.setCellValueFactory(new PropertyValueFactory<>("ClassProof"));
        t_and_c_col.setCellValueFactory(new PropertyValueFactory<>("TermsAndConditions"));
        timestamp.setCellValueFactory(new PropertyValueFactory<>("SubmissionTime"));

        // Apply the resizing
        print_queue_table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        print_queue_table.setItems(getPrintQueueOrder()); // Throws a SQLException
        print_queue_table.refresh();
        print_queue_table.setVisible(true);
    }
}
