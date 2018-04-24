/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author Florian Widder
 */
public class Settings {

    public static final String dbHost = "localhost";
    public static final int dbPort = 1527;
    public static final String dbName = "TIME_FINDER";
    public static final String dbUser = "TimeFinder";
    public static final String dbPass = "AUT2018";
    public static final String dbUrl = "jdbc:derby://" + dbHost + ":" + dbPort + "/" + dbName + ";create=true;user=" + dbUser + ";password=" + dbPass;

    private Settings() {
    }

}
