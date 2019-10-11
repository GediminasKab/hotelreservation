import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Client {
    public String clientId;
    public String name;
    public String surname;
    public String email;

     Client(String clientId, String name, String surname, String email) {
        this.clientId = clientId;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public static void addClient(String name, String surname, String email) throws SQLException {

        String sql = "INSERT INTO CLIENTS (NAME, SURNAME, EMAIL) VALUES(?,?,?)";
        Connection conn = DBConnection.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, name);
        pstmt.setString(2, surname);
        pstmt.setString(3, email);
        pstmt.executeUpdate();
    }
}



