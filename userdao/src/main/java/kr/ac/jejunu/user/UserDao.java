package kr.ac.jejunu.user;

import java.sql.*;

public class UserDao {
    public User get(Integer id) throws ClassNotFoundException, SQLException {
        // mysql
        Connection connection = getConnection();
        // query
        PreparedStatement preparedStatement =
                connection.prepareStatement("select id, name, password from userinfo where id = ?");
        preparedStatement.setInt(1, id);
        // 실행
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        // 결과 매핑
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));
        // 자원 해제
        resultSet.close();
        preparedStatement.close();
        connection.close();

        // 결과 return
        return user;
    }

    public void insert(User user) throws ClassNotFoundException, SQLException {
        // mysql
        Connection connection = getConnection();
        // query
        PreparedStatement preparedStatement =
                connection.prepareStatement("insert into userinfo(name, password) values (?, ?)", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.executeUpdate();
        // 실행
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        resultSet.next();
        // 결과 매핑
        user.setId(resultSet.getInt(1));
        // 자원 해제
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        // driver 로딩
        Class.forName("com.mysql.cj.jdbc.Driver");
        // connection
        return DriverManager.getConnection("jdbc:mysql://localhost/jeju?serverTimezone=Asia/Seoul",
                "jeju", "jejupw");
    }
}
