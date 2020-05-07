package com.app.resources;

public interface SqlConfig {

     String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
     String DB_URL = "jdbc:mysql://192.168.1.102:3306/home_app?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";


     String USER = "prod_app";
     String PASS = "Bulgaria188";
//     String DB_URL = "jdbc:mysql://localhost:3306/home_app?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
//     String USER = "prod";
//     String PASS = "Bulgaria188";
}
