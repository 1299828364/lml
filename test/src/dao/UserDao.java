package dao;

import model.Info;
import model.UInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static util.DBUtil.getConnection;

public class UserDao {

    private static UserDao userDao;

    public static UserDao getInfoDao(){
        if (userDao==null){
            userDao = new UserDao();
        }
        return userDao;
    }

    public List<UInfo> findAll() throws SQLException {
        String sql = "SELECT * FROM user";
        List<UInfo> infos = new ArrayList<>();
        ResultSet rs= select(sql);

        ResultSetMetaData data=rs.getMetaData();
        while (rs.next()){
            UInfo info =new UInfo();

            for (int i=1;i<=data.getColumnCount();i++){
                String columnValue = rs.getString(i);
                switch (i){
                    case 1:{
                        info.setUser(columnValue);
                        break;
                    }
                    case 2:{
                        info.setPassword(columnValue);
                        break;
                    }
                }
            }
            infos.add(info);
        }
        return  infos;
    }

    public static ResultSet select(String sql) throws SQLException {
        Connection connection=getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);
        return stmt.executeQuery(sql);
    }


    public boolean check(String user,String code){
        try {
            List<UInfo> list = findAll();
            for (int i=0;i<list.size();i++){
                if(user.equals(list.get(i).getUser())){
                    if(code.equals(list.get(i).getPassword())){
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    public boolean enroll(String name,String password){
        try {
            Statement stmt=getConnection().createStatement();
            String sql="INSERT INTO user(user,code) VALUES("+name+",'"+password+"')";
            System.out.println(sql);
            stmt.execute(sql);
            stmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
