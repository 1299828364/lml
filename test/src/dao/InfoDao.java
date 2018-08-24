package dao;

import model.Info;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static util.DBUtil.getConnection;

public class InfoDao {
    /**
     * 单例模式，懒汉模式
     */

    private static InfoDao infoDao;

    public static InfoDao getInfoDao(){
        if (infoDao==null){
            infoDao = new InfoDao();

        }
        return infoDao;
    }



    public List<Info> findAll() throws SQLException {
        String sql = "SELECT * FROM mytest";
        List<Info> infos = new ArrayList<>();
        ResultSet rs= select(sql);

        ResultSetMetaData data=rs.getMetaData();
        while (rs.next()){
            Info info =new Info();

            for (int i=1;i<=data.getColumnCount();i++){
                String columnValue = rs.getString(i);
                switch (i){
                    case 1:{
                        info.setName(columnValue);
                        break;
                    }
                    case 2:{
                        info.setAge(columnValue);
                        break;
                    }
                    case 3:{
                        info.setSex(columnValue);
                        break;
                    }
                    case 4:{
                        info.setID(columnValue);
                        break;
                    }
                    case 5:{
                        info.setGrade(columnValue);
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

    public static boolean change(String name,String age,String sex,String ID,String grade){
        try {
            Statement stmt=getConnection().createStatement();
            String sql="UPDATE mytest SET name='"+name
                    +"',age='"+age+"',sex='"+sex+"',grade='"+grade
                    +"' WHERE ID='"+ID+"'";
            stmt.executeUpdate(sql);
            stmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public static boolean add(String name,String age,String sex,String grade) {
        Statement stmt= null;
        try {
            stmt = getConnection().createStatement();
            String sql="INSERT INTO mytest (name,age,sex,grade) value('"+name+"','"+age+"','"+sex+"','"+grade+"')";
            System.out.println(sql+"撒旦"+"11111");
            stmt.execute(sql);
            stmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean delete(String ID) {
        Statement stmt= null;
        try {
            stmt = getConnection().createStatement();
            String sql="DELETE From mytest WHERE ID="+ID;
            stmt.execute(sql);
            System.out.println(sql);
            stmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
