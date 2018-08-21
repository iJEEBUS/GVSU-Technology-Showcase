package Hub.Conrollers;

import Hub.Database;
import Hub.Navigation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import java.sql.SQLException;
import java.util.List;

/**
 * The logic behind all of the actions that have to do with the rental queue panel.
 */
public class rental_queue_controller {

    @FXML
    public GridPane rental_queue;
    @FXML
    TableView rental_queue_table;
    @FXML
    TableColumn<rentalQueueOrder, String>
            last_name_col,
            first_name_col,
            email_col,
            tech_col,
            additional_components_col,
            comments_col,
            return_date_col,
            signature_col,
            timestamp_col;

    /**
     * Code to execute when scene is loaded.
     */
    public void initialize() {

        // Try to display the table at load time
        try {
            updateTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Changes the root scene back to the main hub
     */
    public void returnToHub() {
        Navigation nav = new Navigation();
        nav.returnToHub(rental_queue);
    }

    /**
     * Connects to and collects the orders that are currently in the rental queue.
     * Closes the connection to the database at the end.
     *
     * @return ObservableList<rentalQueueOrder> - rental_queue - the tech rental queue
     * @throws SQLException
     */
    public ObservableList<rentalQueueOrder> getRentalQueueOrder() throws SQLException {

        // List of queue objects to be displayed
        ObservableList<rentalQueueOrder> rental_queue = FXCollections.observableArrayList();

        // Database connection
        Database db = new Database();
        db.connectToDatabase();

        // The list of the queue objects
        List<rentalQueueOrder> rental_queue_data = db.getRentalQueueOrders(); // TODO

        // Add all instances of an order to the observable list rental queue
        for (rentalQueueOrder order : rental_queue_data) {
            rental_queue.add(order);
        }

        // Close db connection
        db.kill();

        return rental_queue;
    }

    /**
     * Clears and updates the visual table with what is currently in the database.
     *
     * @throws SQLException
     */
    public void updateTable() throws SQLException {

        // Clear the table
        rental_queue_table.getItems().clear();

        // Set columns to not be editable
        last_name_col.setEditable(false);
        first_name_col.setEditable(false);
        email_col.setEditable(false);
        tech_col.setEditable(false);
        additional_components_col.setEditable(false);
        comments_col.setEditable(false);
        return_date_col.setEditable(false);
        signature_col.setEditable(false);
        timestamp_col.setEditable(false);

        // Set width of columns
        first_name_col.prefWidthProperty().bind(rental_queue_table.widthProperty().divide(9));
        last_name_col.prefWidthProperty().bind(rental_queue_table.widthProperty().divide(9));
        email_col.prefWidthProperty().bind(rental_queue_table.widthProperty().divide(9));
        tech_col.prefWidthProperty().bind(rental_queue_table.widthProperty().divide(9));
        additional_components_col.prefWidthProperty().bind(rental_queue_table.widthProperty().divide(9));
        comments_col.prefWidthProperty().bind(rental_queue_table.widthProperty().divide(9));
        return_date_col.prefWidthProperty().bind(rental_queue_table.maxWidthProperty().divide(9));
        signature_col.prefWidthProperty().bind(rental_queue_table.widthProperty().divide(9));
        timestamp_col.prefWidthProperty().bind(rental_queue_table.widthProperty().divide(9));

        // Populate
        first_name_col.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        last_name_col.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        email_col.setCellValueFactory(new PropertyValueFactory<>("email"));
        tech_col.setCellValueFactory(new PropertyValueFactory<>("Technology"));
        additional_components_col.setCellValueFactory(new PropertyValueFactory<>("AdditionalComponents"));
        comments_col.setCellValueFactory(new PropertyValueFactory<>("Comments"));
        return_date_col.setCellValueFactory(new PropertyValueFactory<>("ReturnDate"));
        signature_col.setCellValueFactory(new PropertyValueFactory<>("Signature"));
        timestamp_col.setCellValueFactory(new PropertyValueFactory<>("SubmissionTime"));

        // Apply the resizing
        rental_queue_table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        rental_queue_table.setItems(getRentalQueueOrder()); // Throws a SQLException
        rental_queue_table.refresh();
        rental_queue_table.setVisible(true);
    }
}
