import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Client {
    public String name ;
    public String surname;
    public String email;
public String clientId;
    public String getName() {
        return name;
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



