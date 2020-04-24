package kr.ac.jejunu.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface ConnectionMaker {
    public Connection getConnection() throws ClassNotFoundException, SQLException;
//        // driver 로딩
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        // connection
//        return DriverManager.getConnection("jdbc:mysql://localhost/jeju?serverTimezone=Asia/Seoul",
//                "jeju", "jejupw");
}