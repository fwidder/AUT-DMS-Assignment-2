 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.ejb.Stateful;
import javax.ejb.LocalBean;
import model.User;
import util.Hash;
import util.Settings;

/**
 *
 * @author Florian Widder
 */
@Stateful
@LocalBean
public class UserBean {

    private final Connection dbConnection;
    private String SessionID;

    public UserBean() throws SQLException {
        dbConnection = DriverManager.getConnection(Settings.dbUrl);
        DatabaseMetaData dbmd = dbConnection.getMetaData();
        ResultSet rs = dbmd.getTables(null, null, "USERS", null);
        if (!rs.next()) {
            createUserTable();
        }
        rs = dbmd.getTables(null, null, "EVENTS", null);
        if (!rs.next()) {
            createEventsTables();
        }
        rs = dbmd.getTables(null, null, "EVENTS_USERS", null);
        if (!rs.next()) {
            createEventsUsersTables();
        }
    }

    private void createEventsTables() throws SQLException {
        String sql = "CREATE TABLE EVENTS\n"
                + "  (\n"
                + "     EVENTID     INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\n"
                + "     CREATORID   INT NOT NULL REFERENCES USERS(USERID),\n"
                + "     NAME        VARCHAR(30) NOT NULL UNIQUE,\n"
                + "     DESCRIPTION VARCHAR(1000),\n"
                + "     START       DATE NOT NULL,\n"
                + "     ENDING      DATE NOT NULL,\n"
                + "     BEST        DATE\n"
                + "  ) ";
        PreparedStatement statement = dbConnection.prepareStatement(sql);
        statement.execute();
    }

    private void createEventsUsersTables() throws SQLException {
        String sql = "CREATE TABLE EVENTS_USERS\n"
                + "  (\n"
                + "     MEMBERID  INT NOT NULL REFERENCES USERS(USERID),\n"
                + "     EVENTID   INT NOT NULL REFERENCES EVENTS(EVENTID),\n"
                + "     AVAILABLE DATE NOT NULL\n"
                + "  )  ";
        PreparedStatement statement = dbConnection.prepareStatement(sql);
        statement.execute();
    }

    private void createUserTable() throws SQLException {
        String sql = "CREATE TABLE USERS\n"
                + "  (\n"
                + "     USERID   INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),\n"
                + "     USERNAME VARCHAR(255) UNIQUE NOT NULL,\n"
                + "     PASSWORD VARCHAR(255) NOT NULL,\n"
                + "     EMAIL    VARCHAR(255) UNIQUE NOT NULL,\n"
                + "     SESSION  VARCHAR(255)\n"
                + "  ) ";
        PreparedStatement statement = dbConnection.prepareStatement(sql);
        statement.execute();
    }

    public void createEvent(int creatorID, String eventName,
            String eventDescription, Date eventStart, Date eventEnd)
            throws SQLException {
        String sql = "INSERT INTO EVENTS\n"
                + "            (CREATORID,\n"
                + "             NAME,\n"
                + "             DESCRIPTION,\n"
                + "             START,\n"
                + "             ENDING,"
                + "             BEST)\n"
                + "VALUES     (?,\n"
                + "            ?,\n"
                + "            ?,\n"
                + "            ?,\n"
                + "            ?,\n"
                + "            ?)";
        PreparedStatement statement = dbConnection.prepareStatement(sql);
        statement.setInt(1, creatorID);
        statement.setString(2, eventName);
        statement.setString(3, eventDescription);
        statement.setDate(4, eventStart);
        statement.setDate(5, eventEnd);
        statement.setDate(6, eventStart);
        statement.executeUpdate();
    }

    public boolean isLoggedIn() throws SQLException {
        String sql = "SELECT *\n"
                + "FROM   USERS\n"
                + "WHERE  SESSION = ? ";
        PreparedStatement statement = dbConnection.prepareStatement(sql);
        statement.setString(1, getSessionID());
        return (statement.executeQuery()).next();
    }

    public User getUser() throws SQLException {
        if (!isLoggedIn()) {
            return new User();
        }
        String sql = "SELECT USERID,\n"
                + "       USERNAME,\n"
                + "       EMAIL\n"
                + "FROM   USERS\n"
                + "WHERE  SESSION = ? ";
        PreparedStatement statement = dbConnection.prepareStatement(sql);
        statement.setString(1, getSessionID());
        ResultSet result = statement.executeQuery();
        if (!result.next()) {
            return new User();
        }
        User user = new User(result.getInt(1), result.getString(2), result.getString(3));
        return user;
    }

    public String getSessionID() {
        return SessionID;
    }

    public void setSessionID(String SessionID) {
        this.SessionID = SessionID;
    }

    public void login(String username, String password) throws SQLException, NoSuchAlgorithmException {
        String sqlGetPassword = "SELECT PASSWORD\n"
                + "FROM   USERS\n"
                + "WHERE  USERNAME = ?  ";
        String sqlSetSession = "UPDATE USERS\n"
                + "SET    SESSION = ?\n"
                + "WHERE  USERNAME = ? ";
        PreparedStatement statement = dbConnection.prepareStatement(sqlGetPassword);
        statement.setString(1, username);
        ResultSet result = statement.executeQuery();
        if (result.next() && result.getString(1).equals(Hash.hashHex(password))) {
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
        String sqlSetSession = "UPDATE USERS\n"
                + "SET    SESSION = ?\n"
                + "WHERE  SESSION = ?  ";
        PreparedStatement statement = dbConnection.prepareStatement(sqlSetSession);
        statement.setNull(1, java.sql.Types.VARCHAR);
        statement.setString(2, getSessionID());
        statement.execute();
    }

    public void register(String username, String password, String email) throws SQLException, NoSuchAlgorithmException {
        if (isLoggedIn()) {
            return;
        }
        String sqlSetSession = "INSERT INTO USERS\n"
                + "            (USERNAME,\n"
                + "             PASSWORD,\n"
                + "             EMAIL)\n"
                + "VALUES     (?,\n"
                + "            ?,\n"
                + "            ?) ";
        PreparedStatement statement = dbConnection.prepareStatement(sqlSetSession);
        statement.setString(1, username);
        statement.setString(2, Hash.hashHex(password));
        statement.setString(3, email);
        statement.executeUpdate();
    }
}
