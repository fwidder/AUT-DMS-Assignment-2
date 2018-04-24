/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
    private String SessionID;

    private String hashHex(String toHash) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedhash = digest.digest(toHash.getBytes(StandardCharsets.UTF_8));
        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < encodedhash.length; i++) {
            String hex = Integer.toHexString(0xff & encodedhash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public UserBean() throws SQLException {
        dbConnection = DriverManager.getConnection(Settings.dbUrl);
        DatabaseMetaData dbmd = dbConnection.getMetaData();
        ResultSet rs = dbmd.getTables(null, null, "USERS", null);
        if (!rs.next()) {
            createUserTable();
        }
    }

    public boolean isLoggedIn() throws SQLException {
        String sql = "SELECT * FROM USERS WHERE SESSION = ?";
        PreparedStatement statement = dbConnection.prepareStatement(sql);
        statement.setString(1, getSessionID());
        return (statement.executeQuery()).next();
    }

    public String getUsername() throws SQLException {
        if (!isLoggedIn()) {
            return null;
        }
        String sql = "SELECT USERNAME FROM USERS WHERE SESSION = ?";
        PreparedStatement statement = dbConnection.prepareStatement(sql);
        statement.setString(1, getSessionID());
        ResultSet result;
        (result = (statement.executeQuery())).next();
        return result.getString(1);
    }

    private void createUserTable() throws SQLException {
        String sql = "CREATE TABLE USERS(USERID INT NOT NULL PRIMARY KEY "
                + "GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                + "USERNAME VARCHAR(255) UNIQUE NOT NULL, PASSWORD VARCHAR(255) "
                + "NOT NULL,EMAIL VARCHAR(255) UNIQUE "
                + "NOT NULL, SESSION VARCHAR(255))";
        PreparedStatement statement = dbConnection.prepareStatement(sql);
        statement.execute();
    }

    public String getSessionID() {
        return SessionID;
    }

    public void setSessionID(String SessionID) {
        this.SessionID = SessionID;
    }

    public void login(String username, String password) throws SQLException, NoSuchAlgorithmException {
        String sqlGetPassword = "SELECT PASSWORD FROM USERS WHERE USERNAME = ?";
        String sqlSetSession = "UPDATE USERS SET SESSION = ? WHERE USERNAME = ?";
        PreparedStatement statement = dbConnection.prepareStatement(sqlGetPassword);
        statement.setString(1, username);
        ResultSet result = statement.executeQuery();
        if (result.next() && result.getString(1).equals(hashHex(password))) {
            statement = dbConnection.prepareStatement(sqlSetSession);
            statement.setString(1, getSessionID());
            statement.setString(2, username);
            statement.execute();
        }
    }

    public void logout() throws SQLException {
        if (!isLoggedIn()) {
            return;
        }
        String sqlSetSession = "UPDATE USERS SET SESSION = ? WHERE SESSION = ?";
        PreparedStatement statement = dbConnection.prepareStatement(sqlSetSession);
        statement.setNull(1, java.sql.Types.VARCHAR);
        statement.setString(2, getSessionID());
        statement.execute();
    }

    public void register(String username, String password, String email) throws SQLException, NoSuchAlgorithmException {
        if (isLoggedIn()) {
            return;
        }
        String sqlSetSession = "INSERT INTO USERS(USERNAME, PASSWORD, EMAIL) VALUES(?, ?, ?)";
        PreparedStatement statement = dbConnection.prepareStatement(sqlSetSession);
        statement.setString(1, username);
        statement.setString(2, hashHex(password));
        statement.setString(3, email);
        statement.executeUpdate();
    }
}
