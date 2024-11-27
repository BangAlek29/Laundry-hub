package Main;

import model.AkunModel;
import koneksiDatabase.Connect;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AuthController {

    public static AkunModel validateLogin(String username, String password) {
        try {
            Statement stmt = Connect.configDB().createStatement();
            String query = "SELECT * FROM akun WHERE username = '" + username + "';";
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                String storedPassword = rs.getString("password");
                if (storedPassword.equals(password)) {
                    String role = rs.getString("role");
                    String idAkun = getIdAkunByUsername(username);
                    return new AkunModel(idAkun,username, password, role);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getIdAkunByUsername(String username) {
        String idAkun = "";
        try {
            Statement stmt = Connect.configDB().createStatement();
            String query = "SELECT id_akun FROM akun WHERE username = '" + username + "';";
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                idAkun = rs.getString("id_akun");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idAkun;
    }
}
