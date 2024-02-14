package barberadmin1;
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.sql.Connection;
import java.sql.DriverManager;

class DataBaseConnection {
    DataBaseConnection() {

    }

    public static Connection getConnectionToDB() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://sql.freedb.tech:3306/freedb_BarberManagement1","freedb_Israel1234","!Tuz??V@C2qq&gv");  
        } catch (Exception var1) {  
           // System.out.println("Error connecting to database: " + var1.getMessage());
            return null;
        }
    }
}
