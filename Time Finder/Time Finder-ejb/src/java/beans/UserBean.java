/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import util.Settings;

/**
 *
 * @author Florian Widder
 */
@Stateless
@LocalBean
public class UserBean {

    private final Connection dbConnection;

    public UserBean() throws SQLException {
        dbConnection = DriverManager.getConnection(Settings.dbUrl);
        DatabaseMetaData dbmd = dbConnection.getMetaData();
        ResultSet rs = dbmd.getTables(null, "TIME_FINDER", "USERS", null);
        if (!rs.next()) {
            createUserTable();
        }
    }

    public boolean isLoggedIn() {
        return true;
    }

    private void createUserTable() throws SQLException {
        String sql = "CREATE TABLE USERS(UserID INT NOT NULL PRIMARY KEY "
                + "GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                + "Username VARCHAR(255) NOT NULL, Password VARCHAT(255) "
                + "NOT NULL, Session VARCHAR(255))";
        PreparedStatement statement = dbConnection.prepareStatement(sql);
        statement.execute();
    }
}
