package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    public static Connection getConnection(){

        String driverName = "com.mysql.jdbc.Driver";

        String url = "jdbc:mysql://localhost:3306/mytest?useUnicode=true&characterEncoding=UTF-8";

        String userName ="root";
        String userPassword="lml";

        try {
            Class.forName(driverName);

            Connection connection= DriverManager.getConnection(url,userName,userPassword);
            System.out.println("数据库连接成功");
            return connection;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }
}
