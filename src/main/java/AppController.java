import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;

public class AppController implements Initializable {

    @FXML
    private TextField userName;
    @FXML
    private TextField userSurname;
    @FXML
    private TextField userEmail;
    @FXML
    private DatePicker beginningDate;
    @FXML
    private DatePicker endingDate;
    @FXML
    private TextField clientId;
    @FXML
    private static TableView<FillTable> table;
    @FXML
    private static TableColumn<FillTable, String> tableClientId;
    @FXML
    private TableColumn<FillTable, String> tableNameCol;
    @FXML
    private TableColumn<FillTable, String> tableSurnameCol;
    @FXML
    private TableColumn<FillTable, String> tableEmailCol;
    ;
    @FXML
    private static TableView<Room> tableRoom;
    @FXML
    private TableColumn<Room, String> tableRoomIdCol;
    @FXML
    private TableColumn<Room, String> tableCategoryCol;
    @FXML
    private TableColumn<Room, String> tableStatusCol;
    @FXML
    private static TableView<Room> tableDate;
    @FXML
    private TableColumn<Client, Date> tableStartDateCol;
    @FXML
    private TableColumn<Client, String> tableEndingDateCol;
    Client client = new Client();

    ResultSet rs = null;

    public void initialize(URL url, ResourceBundle rb) {
       ObservableList<FillTable> data = FXCollections.observableArrayList();
        try (Connection conn = DBConnection.connect();
             Statement prep = conn.createStatement()) {
            ResultSet rs = prep.executeQuery("SELECT * FROM Clients");
            while (rs.next()) {
                data.add(new FillTable(
                        rs.getString("ClientId"),
                        rs.getString("Name"),
                        rs.getString("Surname"),
                        rs.getString("Email")
                ));
            }
            tableClientId.setCellValueFactory(new PropertyValueFactory<>("ClientID"));
            tableNameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
            tableSurnameCol.setCellValueFactory(new PropertyValueFactory<>("Surname"));
            tableEmailCol.setCellValueFactory(new PropertyValueFactory<>("Email"));
            table.setItems(data);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error");
        }

    }

    private Object JRootPane;

    public void saveUser() {

        String name = userName.getText().trim();
        String surname = userSurname.getText().trim();
        String email = userEmail.getText().trim();
        if (name.equals("") || surname.equals("") || email.equals("")) {
            JOptionPane.showMessageDialog((Component) JRootPane, "Please fill fields except ClientId", "Fill all fields", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                Client.addClient(name, surname, email);
                JOptionPane.showMessageDialog((Component) JRootPane, "Saved succesfully", "Saved User", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog((Component) JRootPane, "Not saved", "Not saved", JOptionPane.ERROR_MESSAGE);
                System.out.println(e.getMessage());
            }
            userName.clear();
            userSurname.clear();
            userEmail.clear();

        }

        /*public void editUser() {
            PreparedStatement st;
            String editQuery = "UPDATE `clients` SET `name`=?,`surname`=?,`email`=? WHERE `id`=?";
            String name = userName.getText();
            String surname = userSurname.getText();
            String email = userEmail.getText();
            int clientId;
            try {

                st = DBConnection.connect().prepareStatement(editQuery);
                //st.setInt(1, clientId);
                st.setString(2, name);
                st.setString(3, surname);
                st.setString(4, email);

            } catch (SQLException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);

            }

        }*/

        /*public void deleteUser () {
            PreparedStatement st;
            String deleteQuery = "DELETE FROM `clients` WHERE `id`=?";

            try {

                st = DBConnection.connect().prepareStatement(deleteQuery);

                //st.setInt(1, clientId);


            } catch (SQLException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
*/
    }

    public void editUser(ActionEvent actionEvent) {
    }

    public void deleteUser(ActionEvent actionEvent) {
    }
}



