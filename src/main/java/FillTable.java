import javafx.beans.property.SimpleStringProperty;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FillTable {

    public String getClientId() {
        return clientId.get();
    }

    public SimpleStringProperty clientIdProperty() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId.set(clientId);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSurname() {
        return surname.get();
    }

    public SimpleStringProperty surnameProperty() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public SimpleStringProperty clientId = new SimpleStringProperty();
    public SimpleStringProperty name = new SimpleStringProperty();
    public SimpleStringProperty surname = new SimpleStringProperty();
    public SimpleStringProperty email = new SimpleStringProperty();

    public FillTable(String clientId, String name, String surname, String email) {
    }


    public void fillClientJTable() {
        PreparedStatement ps;
        ResultSet rs;
        String selectQuery = "SELECT * FROM `clients`";
        JTable table = null;
        try {

            ps = DBConnection.connect().prepareStatement(selectQuery);

            rs = ps.executeQuery();

            DefaultTableModel tableModel = (DefaultTableModel) table.getModel();

            Object[] row;

            while (rs.next()) {
                row = new Object[5];
                row[0] = rs.getString(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);


                tableModel.addRow(row);
            }


        } catch (SQLException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
