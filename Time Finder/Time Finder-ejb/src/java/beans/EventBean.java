/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import model.Event;
import model.User;
import util.Settings;

/**
 *
 * @author Florian Widder
 */
@Stateless
@LocalBean
public class EventBean {
    
    private final Connection dbConnection;
    
    public EventBean() throws SQLException {
        dbConnection = DriverManager.getConnection(Settings.dbUrl);
        DatabaseMetaData dbmd = dbConnection.getMetaData();
        ResultSet rs = dbmd.getTables(null, null, "EVENTS", null);
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
                + "     EVENTID   INT NOT NULL REFERENCES USERS(USERID),\n"
                + "     AVAILABLE DATE NOT NULL\n"
                + "  )  ";
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
    
    public void setAvailable(int userID, int eventID, Date[] availableOn)
            throws SQLException {
        String sql = "INSERT INTO EVENTS_USERS\n"
                + "            (MEMBERID,\n"
                + "             EVENTID,\n"
                + "             AVAILABLE)\n"
                + "VALUES     (?,\n"
                + "            ?,\n"
                + "            ?)  ";
        PreparedStatement statement = dbConnection.prepareStatement(sql);
        statement.setInt(1, userID);
        statement.setInt(2, eventID);
        for (Date d : availableOn) {
            statement.setDate(3, d);
            statement.executeUpdate();
        }
        //TODO Calculate and populate best Date
    }
    
    public void setAvailable(int userID, int eventID, Date availableOn)
            throws SQLException {
        Date[] dates = new Date[1];
        dates[0] = availableOn;
        setAvailable(userID, eventID, dates);
    }
    
    public Event[] getAllEvents() throws SQLException {
        String sql = "SELECT E.EVENTID,\n"
                + "       E.NAME,\n"
                + "       E.DESCRIPTION,\n"
                + "       E.START,\n"
                + "       E.ENDING,\n"
                + "       E.BEST,\n"
                + "       U.USERID,\n"
                + "       U.USERNAME,\n"
                + "       U.EMAIL\n"
                + "FROM   EVENTS E\n"
                + "       JOIN USERS U\n"
                + "         ON E.CREATORID = U.USERID ";
        PreparedStatement statement = dbConnection.prepareStatement(sql);
        ResultSet result = statement.executeQuery();
        ArrayList<Event> eventList = new ArrayList<>();
        while (result.next()) {
            Event e = new Event(result.getInt(1), result.getString(2),
                    result.getString(3), result.getDate(4), result.getDate(5),
                    result.getDate(6), new User(result.getInt(7),
                            result.getString(8), result.getString(9)));
            eventList.add(e);
        }
        return eventList.toArray(new Event[0]);
    }
    
    public Event[] getMyEvents(int userID) throws SQLException {
        String sql = "SELECT E.EVNTID,\n"
                + "       E.NAME,\n"
                + "       E.DESCRIPTION,\n"
                + "       E.START,\n"
                + "       E.ENDING,\n"
                + "       E.BEST,\n"
                + "       U.USERID,\n"
                + "       U.USERNAME,\n"
                + "       U.EMAIL\n"
                + "FROM   EVENTS E\n"
                + "       JOIN EVENTS_USERS EU\n"
                + "         ON E.EVENTID = EU.EVENTID\n"
                + "       JOIN USERS U\n"
                + "         ON E.CREATORID = U.USERID "
                + "WHERE  EU.MEMBERID = ? ";
        PreparedStatement statement = dbConnection.prepareStatement(sql);
        statement.setInt(1, userID);
        ResultSet result = statement.executeQuery();
        ArrayList<Event> eventList = new ArrayList<>();
        while (result.next()) {
            Event e = new Event(result.getInt(1), result.getString(2),
                    result.getString(3), result.getDate(4), result.getDate(5),
                    result.getDate(6), new User(result.getInt(7),
                            result.getString(8), result.getString(9)));
            eventList.add(e);
        }
        return eventList.toArray(new Event[0]);
    }
    
    public Event getEventByID(int eventID) throws SQLException {
        String sql = "SELECT E.EVNTID,\n"
                + "       E.NAME,\n"
                + "       E.DESCRIPTION,\n"
                + "       E.START,\n"
                + "       E.ENDING,\n"
                + "       E.BEST,\n"
                + "       U.USERID,\n"
                + "       U.USERNAME,\n"
                + "       U.EMAIL\n"
                + "FROM   EVENTS E\n"
                + "       JOIN USERS U\n"
                + "         ON E.CREATORID = U.USERID "
                + "WHERE  E.EVENTID = ? ";
        PreparedStatement statement = dbConnection.prepareStatement(sql);
        statement.setInt(1, eventID);
        ResultSet result = statement.executeQuery();
        Event e = null;
        if (result.next()) {
            e = new Event(result.getInt(1), result.getString(2),
                    result.getString(3), result.getDate(4), result.getDate(5),
                    result.getDate(6), new User(result.getInt(7),
                            result.getString(8), result.getString(9)));
        }
        return e;
    }
}
