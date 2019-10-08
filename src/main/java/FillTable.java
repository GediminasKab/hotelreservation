import javafx.beans.property.SimpleStringProperty;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FillTable {

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
                row[0] = rs.getInt(1);
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
