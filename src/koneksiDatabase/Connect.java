/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koneksiDatabase;

import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author MY PC
 */
public class Connect {
    private static Connection MyConnection;
    
    public static Connection configDB() throws SQLException{
        
        if(MyConnection == null){
            new Driver();
            
            MyConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_laundry","root","");
        }
        return MyConnection;
    }
}
