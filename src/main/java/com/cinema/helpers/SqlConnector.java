package com.cinema.helpers;

import com.cinema.resources.SqlConfig;
import java.sql.*;

public class SqlConnector {


    public ResultSet selectResults(String query) {

        ResultSet resultSet = null;
            try {

                Class.forName(SqlConfig.JDBC_DRIVER);
                // Establish connection object
                Connection conn = DriverManager.getConnection(SqlConfig.DB_URL,SqlConfig.USER,SqlConfig.PASS);
                //Create SQL statement object to send to the database
                Statement statement = conn.createStatement();

                //Execute the statement object;
                resultSet =  statement.executeQuery(query);

                //inserting data
//            rowsAffected = statement.executeUpdate("insert into employees_tbl values(900,'Michael','Sales',500)");
//            System.out.println(rowsAffected);

            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }

            return resultSet;
        }

    public int insertResult (String query){
        int result = 0;
        try {

            Class.forName(SqlConfig.JDBC_DRIVER);
            // Establish connection object
            Connection conn = DriverManager.getConnection(SqlConfig.DB_URL,SqlConfig.USER,SqlConfig.PASS);
            //Create SQL statement object to send to the database
            Statement statement = conn.createStatement();

            //Execute the statement object;
            result =  statement.executeUpdate(query);
            
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

}