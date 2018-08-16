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
 *
 */
public class rental_queue_controller {

    @FXML
    public GridPane rental_queue;

    @FXML
    TableView rental_queue_table;
    @FXML
    TableColumn<rentalQueueOrder, String> last_name_col, first_name_col, email_col;
    @FXML
    TableColumn<rentalQueueOrder, String> tech_col, return_date_col, additional_components_col;
    @FXML
    TableColumn<rentalQueueOrder, String> comments_col, signature_col;

    /**
     * Code to execute when scene is loaded.
     */
    public void initialize() {
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

        // Populate / set column sizes
        last_name_col.setCellValueFactory(new PropertyValueFactory<>("LastName")); // Last name column
        first_name_col.setCellValueFactory(new PropertyValueFactory<>("FirstName")); // First name column
        email_col.setCellValueFactory(new PropertyValueFactory<>("email")); // Email column
        tech_col.setCellValueFactory(new PropertyValueFactory<>("Technology")); // Technology column
        return_date_col.setCellValueFactory(new PropertyValueFactory<>("ReturnDate"));
        additional_components_col.setCellValueFactory(new PropertyValueFactory<>("AdditionalComponents"));
        comments_col.setCellValueFactory(new PropertyValueFactory<>("Comments"));

        last_name_col.prefWidthProperty().bind(rental_queue_table.widthProperty().divide(6));
        first_name_col.prefWidthProperty().bind(rental_queue_table.widthProperty().divide(6));
        email_col.prefWidthProperty().bind(rental_queue_table.widthProperty().divide(6));
        tech_col.prefWidthProperty().bind(rental_queue_table.widthProperty().divide(6));
        return_date_col.prefWidthProperty().bind(rental_queue_table.widthProperty().divide(6));
        additional_components_col.prefWidthProperty().bind(rental_queue_table.widthProperty().divide(6));
        comments_col.prefWidthProperty().bind(rental_queue_table.widthProperty().divide(6));

        rental_queue_table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Set columns to be non-sortable/non-editable/non-resizable
        last_name_col.setSortable(false);
        last_name_col.setEditable(false);
        last_name_col.setResizable(false);

        first_name_col.setSortable(false);
        first_name_col.setEditable(false);
        first_name_col.setResizable(false);

        email_col.setSortable(false);
        email_col.setEditable(false);
        email_col.setResizable(false);

        tech_col.setSortable(false);
        tech_col.setEditable(false);
        tech_col.setResizable(false);

        return_date_col.setSortable(false);
        return_date_col.setEditable(false);
        return_date_col.setResizable(false);

        additional_components_col.setSortable(false);
        additional_components_col.setEditable(false);
        additional_components_col.setResizable(false);

        comments_col.setSortable(false);
        comments_col.setEditable(false);
        comments_col.setResizable(false);

        rental_queue_table.setItems(getRentalQueueOrder()); // Throws a SQLException
        rental_queue_table.refresh();
        rental_queue_table.setVisible(true);
    }
}
