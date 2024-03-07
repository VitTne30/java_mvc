package Controller.Serviece;

import Controller.DbConnection.DataConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author ADMIN
 */
public class LoginAndRegisterService {
    private DataConnection databaseConnection;

    public LoginAndRegisterService() {
        databaseConnection = DataConnection.getInstance();
    }

    public boolean authenticate(String username, String password) {
        Connection connection = null;
        try {
            connection = (Connection) databaseConnection.getConnection();
            String query = "SELECT * FROM tbl_taikhoan WHERE tentaikhoan = ? AND matkhau = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, username);
                statement.setString(2, password);
                try (ResultSet resultSet = statement.executeQuery()) {
                    return resultSet.next();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (connection != null) {
                databaseConnection.releaseConnection((java.sql.Connection) connection);
            }
        }
    }
}
