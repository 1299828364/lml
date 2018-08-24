package dao;

import model.Entity;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static util.DBUtil.getConnection;

public class TestDao {
    private static TestDao testDao;

    public static TestDao getTestDao(){
        if (testDao==null){
            testDao = new TestDao();

        }
        return testDao;
    }

    public<T extends Entity> boolean updateSQL(Entity entity ,String condition){
        Connection connection=getConnection();
        PreparedStatement stm=connection.prepareStatement();
    }

    public static <T extends Entity> List<T> findAll(Class<T> clazz ,String condition) {
        String sql = null;
        try {
            sql = "SELECT * FROM "+clazz.getMethod("getType").invoke(new  Object())+" WHERE 1=1";
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        if(condition!=null){
            sql+=" and "+ condition;
        }
        List<T> infos = new ArrayList<>();
        try {
//            Entity entity=clazz.newInstance();
            ResultSet rs= select(sql);
            while (rs.next()){
                T temp=clazz.newInstance();
                HashMap<String,String> info=new HashMap<>();
                ResultSetMetaData mate=rs.getMetaData();
                for(int i=0;i<rs.getMetaData().getColumnCount();i++){
                    info.put(mate.getColumnLabel(i),rs.getString(i));
                }
                temp.setProperties(info);
                infos.add(temp);
            }
            return infos;
        } catch (Exception e) {
            e.printStackTrace();
        }
       return null;

    }

    public<T extends Entity> T getOne(Class<T> clazz,String condition){
         List<T> list=findAll(clazz,condition);
         if(list!=null&&list.size()>0){
             return list.get(0);
         }
         return null;
    }

    public static ResultSet select(String sql) throws SQLException {
        Connection connection=getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);
        return stmt.executeQuery(sql);
    }

    public Map<String,Object> toMap(){

    }
}
