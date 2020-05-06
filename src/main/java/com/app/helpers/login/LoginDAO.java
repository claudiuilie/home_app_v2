package com.app.helpers.login;

import com.app.helpers.SqlConnector;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {

    public static boolean validate(String user, String password) {

        SqlConnector sqlConnector = new SqlConnector();


        try {
            String query = "Select uname , password from Users where uname ='"+ user +"' and password = '"+password+"'";
            ResultSet rs = sqlConnector.selectResults(query);
            System.out.println(rs);
            if (rs.next()) {
                //result found, means valid inputs
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
            return false;
        }
        return false;
    }
}