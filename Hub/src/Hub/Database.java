package Hub;

import Hub.Conrollers.printQueueOrder;
import Hub.Conrollers.rentalQueueOrder;
import com.sun.org.apache.xpath.internal.operations.Bool;

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
    public Boolean sendPrintQuery(String fname,
                                  String lname,
                                  String email,
                                  String file_1,
                                  String file_2,
                                  String file_3,
                                  String file_4,
                                  Boolean for_class,
                                  String class_name,
                                  String class_proof,
                                  Boolean t_and_c) {

        // make sure that the terms and conditions are signed
        // and that the needed info is inputted
        if (
                fname != null &
                lname != null &
                email != null) {

            String query = "INSERT INTO prints(first_name, last_name, email, print_one, print_two, print_three, print_four, for_class, class_name, class_proof, t_and_c) VALUES (?,?,?,?,?,?,?,?,?,?,?)";

            // Connect to and send query to database
            try {
                Connection myConn = connectToDatabase();

                // Check if connection exists.
                // If no, try to reconnect 5 times before quitting.
                if (myConn != null) {

                    System.out.println("Adding meat to query bones....");
                    PreparedStatement final_statement = myConn.prepareStatement(query);

                    final_statement.setString(1,fname);
                    final_statement.setString(2, lname);
                    final_statement.setString(3, email);
                    final_statement.setString(4, file_1);
                    final_statement.setString(5, file_2);
                    final_statement.setString(6, file_3);
                    final_statement.setString(7, file_4);
                    final_statement.setBoolean(8, for_class);
                    final_statement.setString(9, class_name);
                    final_statement.setString(10, class_proof);
                    final_statement.setBoolean(11, t_and_c);

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

    public List<printQueueOrder> getPrintQueueOrder() {

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

                    while (query_response.next()) {
                        String last_name = query_response.getString("last_name");
                        String first_name = query_response.getString("first_name");
                        String email = query_response.getString("email");
                        String print_one = query_response.getString("print_one");
                        String print_two = query_response.getString("print_two");
                        String print_three = query_response.getString("print_three");
                        String print_four = query_response.getString("print_four");

                        Boolean for_class = query_response.getBoolean("for_class");
                        String class_name = query_response.getString("class_name");
                        String class_proof = query_response.getString("class_proof");
                        Boolean t_and_c = query_response.getBoolean("t_and_c");

                        Timestamp timestamp = query_response.getTimestamp("submission_time");

                        printQueueOrder order = new printQueueOrder(last_name,
                                                                    first_name,
                                                                    email,
                                                                    print_one,
                                                                    print_two,
                                                                    print_three,
                                                                    print_four,
                                                                    for_class,
                                                                    class_name,
                                                                    class_proof,
                                                                    t_and_c,
                                                                    timestamp);
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

