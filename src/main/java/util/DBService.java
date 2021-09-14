package util;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBService {
    public static List<User> userList = new ArrayList<User>();

    //logic
    public static List<User> getUniversityList() throws SQLException, ClassNotFoundException {
        String ketmon = "select * from users";

        Connection connection = DBConfig.getConnection();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(ketmon);

        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt(1)); //id
            user.setEmail(resultSet.getString(2));
            user.setFullName(resultSet.getString(3));
            user.setAge(resultSet.getInt(4));
            user.setPassword(resultSet.getString(5));
            userList.add(user);
        }
        return userList;
    }

    public static boolean registerUser(String email, String fullName, int age, String password) throws SQLException, ClassNotFoundException {
        Connection connection = DBConfig.getConnection();

        CallableStatement callableStatement = connection.prepareCall("{call register(?,?,?,?,?,?)}");

        callableStatement.setString(1, email);
        callableStatement.setString(2, fullName);
        callableStatement.setInt(3, age);
        callableStatement.setString(4, password);
        callableStatement.registerOutParameter(5, Types.VARCHAR);
        callableStatement.registerOutParameter(6, Types.BOOLEAN);

        callableStatement.execute();
        System.out.println(callableStatement.getBoolean(6));
        System.out.println(callableStatement.getString(5));
        return callableStatement.getBoolean(6);
    }

    public static boolean loginUser(String email, String password) throws SQLException, ClassNotFoundException {

        Connection connection = DBConfig.getConnection();

        CallableStatement callableStatement = connection.prepareCall("{call login(?,?,?,?)}");

        callableStatement.setString(1, email);
        callableStatement.setString(2, password);
        callableStatement.registerOutParameter(3, Types.VARCHAR);
        callableStatement.registerOutParameter(4, Types.BOOLEAN);

        callableStatement.execute();
        System.out.println(callableStatement.getBoolean(4));
        System.out.println(callableStatement.getString(3));

        return callableStatement.getBoolean(4);
    }

}


