package Hub;

import Hub.Conrollers.printQueueOrder;
import Hub.Conrollers.rentalQueueOrder;

import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Database {
    Boolean ALIVE;
    Connection myConn;

    /**
     * Creates a connection to the local mySQL database.
     * Used as a helper method for the createPrintQuery function.
     */
    public Connection connectToDatabase() {
        try {
            String url = "jdbc:mysql://localhost:3306/Tech_Showcase?autoReconnect=true&useSSL=false";
            String user = "root";
            String password = "fuck off";

            // Create connection
            myConn = DriverManager.getConnection(url, user, password);
            if (myConn != null) {
//                System.out.println("Connection success.");
                ALIVE = true;
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

    /**
     * Used to kill the current connection to the database.
     *
     * @throws SQLException
     */
    public void kill() throws SQLException {
        if (ALIVE)
            myConn.close();
    }



    /**
     * Creates and sends a query to the 3d printing database with all of the
     * needed user inputs.
     */
    public Boolean sendPrintQuery(String fname, String lname, String email) {
//        System.out.println("Generating query to submit....");

        // make sure that all of the text boxes are filled
        if (fname != null & lname != null & email != null) {

            String query = "INSERT INTO prints (last_name, first_name, email)"
                            + "VALUES (?,?,?)";
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
                    final_statement.setString(1,lname);
                    final_statement.setString(2, fname);
                    final_statement.setString(3, email);
                    final_statement.execute();
                    System.out.println("Query executed.");
                    System.out.println("Killing connection to database...:(");
                    return true;
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
        return false;
        }


    /**
     * Creates and sends a query to the tech rental database with all of the
     * needed user inputs.
     */
    public Boolean sendRentalQuery(String fname, String lname, String email, String tech, String return_date, String comps, String comments, Boolean signature) {
        System.out.println("Generating query to submit....");

        // make sure that all of the text boxes are filled
        if (fname != null & lname != null & email != null) {

            String query = "INSERT INTO rentals (last_name, first_name, email, technology, return_date, comps, comments, signature)"
                    + "VALUES (?,?,?,?,?,?,?,?)";


            // Connect to and send query to database
            try {
                Connection myConn = connectToDatabase();

                // Check if connection exists.
                // If no, try to reconnect 5 times before quitting.
                if (myConn != null) {
                    PreparedStatement final_statement = myConn.prepareStatement(query);
                    final_statement.setString(1,lname);
                    final_statement.setString(2, fname);
                    final_statement.setString(3, email);
                    final_statement.setString(4, tech);
                    final_statement.setString(5, return_date);
                    final_statement.setString(6, comps);
                    final_statement.setString(7, comments);
                    final_statement.setBoolean(8, signature);
                    final_statement.execute();
                    return true;
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
        return false;
    }



    public List<rentalQueueOrder> getRentalQueueOrders() {

        List<rentalQueueOrder> all_row_data = new ArrayList<rentalQueueOrder>();

        String query = "SELECT * FROM rentals";

        // Connect to and send query to database
        try {

            // Only try to connect if not connected already
            if (myConn == null) {
                myConn = connectToDatabase();
            }

            // Check if connection exists.
            // If no, try to reconnect 5 times before quitting.
            if (myConn != null) {
                PreparedStatement final_statement = myConn.prepareStatement(query);
                ResultSet query_response = final_statement.executeQuery();

                // Closes the connection, result set, and statement
                // if nothing is found
                if (!query_response.isBeforeFirst()) {
                    query_response.close();
                    final_statement.close();
                    myConn.close();
                } else {

                    ResultSetMetaData meta_data = query_response.getMetaData();
                    int colCount = meta_data.getColumnCount();

                    for (int i = 1; i < colCount; i++) {
                        String col_name = meta_data.getColumnName(i);
                    }

                    while (query_response.next()) {
                        String last_name = query_response.getString("last_name");
                        String first_name = query_response.getString("first_name");
                        String email = query_response.getString("email");
                        String tech = query_response.getString("tech_col");
                        String return_date = query_response.getString("return_date_col");
                        String additional_comps = query_response.getString("additional_components_col");
                        String comments = query_response.getString("comments_col");
//                        String signature = query_response.getString("signature_col");
                        rentalQueueOrder order = new rentalQueueOrder(last_name, first_name, email, tech, return_date, additional_comps, comments);

                        all_row_data.add(order);
                    }
                }
                return all_row_data;
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
        return null;
    }

    public List<printQueueOrder> getprintQueueOrder() {

        List<printQueueOrder> all_row_data = new ArrayList<printQueueOrder>();

        String query = "SELECT * FROM prints";

        // Connect to and send query to database
        try {

            // Only try to connect if not connected already
            if (myConn == null) {
                myConn = connectToDatabase();
            }

            // Check if connection exists.
            // If no, try to reconnect 5 times before quitting.
            if (myConn != null) {
                PreparedStatement final_statement = myConn.prepareStatement(query);
                ResultSet query_response = final_statement.executeQuery();

                // Closes the connection, result set, and statement
                // if nothing is found
                if (!query_response.isBeforeFirst()) {
                    query_response.close();
                    final_statement.close();
                    myConn.close();
                } else {

                    ResultSetMetaData meta_data = query_response.getMetaData();
                    int colCount = meta_data.getColumnCount();

                    for (int i = 1; i < colCount; i++) {
                        String col_name = meta_data.getColumnName(i);
                        System.out.println(col_name);
                    }

                    while (query_response.next()) {
                        String last_name = query_response.getString("last_name");
                        String first_name = query_response.getString("first_name");
                        String email = query_response.getString("email");
                        printQueueOrder order = new printQueueOrder(last_name, first_name, email);

                        all_row_data.add(order);
                    }
                }
                return all_row_data;
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
        return null;
    }
}

