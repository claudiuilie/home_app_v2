package com.app.helpers;

import com.app.resources.SqlConfig;
import java.sql.*;
import java.util.List;

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

            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }

            return resultSet;
        }

    public int insertResult (String insert, List<String> values){
        int result = 0;
        try {

            Class.forName(SqlConfig.JDBC_DRIVER);
            // Establish connection object
            Connection conn = DriverManager.getConnection(SqlConfig.DB_URL,SqlConfig.USER,SqlConfig.PASS);
            //Create SQL statement object to send to the database
            PreparedStatement statement = conn.prepareStatement(insert);

            for(int i=1; i <= values.size(); i++){
                statement.setString(i, values.get(i - 1));
            }
            //Execute the statement object;
            System.out.println(statement);
            result =  statement.executeUpdate();
            
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }
}