package dao;

import model.Entity;
import model.UInfo;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.*;

import static util.DBUtil.getConnection;

public class TestDao {
    private static TestDao testDao;

    public static TestDao getTestDao(){
        if (testDao==null){
            testDao = new TestDao();

        }
        return testDao;
    }

    public static <T extends Entity> boolean updateSQL(Entity entity ,String condition) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Connection connection=getConnection();
//        Map<String,Object> map=entity.toMap();
        String sql="UPDATE "+entity.getType()+" SET ";
        Field[] fields=entity.getClass().getDeclaredFields();
        for(Field field:fields){
            if (entity.getClass().getMethod("get"+field.getName().toUpperCase().charAt(0)+
                    field.getName().substring(1)).invoke(entity)==null){
                continue;
            }

            if(field.getType().getSimpleName().equals("String")){
                sql+=field.getName()+"='"+entity.getClass().getMethod("get"+field.getName().toUpperCase().charAt(0)+
                        field.getName().substring(1)).invoke(entity)+"', ";
                continue;
            }

            sql+=field.getName()+"="+entity.getClass().getMethod("get"+field.getName().toUpperCase().charAt(0)+
                    field.getName().substring(1)).invoke(entity)+", ";
        }
        sql=sql.substring(0,sql.length()-2);
        sql+=" WHERE "+condition;
        try {
            System.out.println(sql);
            PreparedStatement stm=connection.prepareStatement(sql);
            if(!stm.execute()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static <T extends Entity> List<T> findAll(Class<T> clazz ,String condition) {
        String sql = null;
        try {
            Entity entity=clazz.newInstance();
            sql = "SELECT * FROM "+entity.getType()+" WHERE 1=1";
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }  catch (InstantiationException e) {
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
                HashMap<String,String> info=new HashMap<String,String>();
                int k=rs.getMetaData().getColumnCount();
                ResultSetMetaData mate=rs.getMetaData();
                for(int i=0;i<k;i++){
                    info.put(mate.getColumnLabel(i+1),rs.getString(i+1));
                }
//                clazz.getMethod("setProperties", HashMap.class,clazz).invoke(temp,info,clazz);
                temp.setProperties(info,clazz);
                infos.add(temp);
            }
            return infos;
        } catch (Exception e) {
            e.printStackTrace();
        }
       return null;

    }

    public static <T extends Entity> T getOne(Class<T> clazz,String condition){
         List<T> list=findAll(clazz,condition);
         if(list!=null&&list.size()>0){
             return list.get(0);
         }
         return null;
    }

    public static ResultSet select(String sql) throws SQLException {
        Connection connection=getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);
        System.out.println(sql);
        return stmt.executeQuery(sql);
    }

    public static<T extends Entity> boolean delete(Class<T> clazz,String condition){
        Entity entity= null;
        try {
            entity = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        String sql="DELETE FROM "+entity.getType()+" WHERE "+condition;
        try {
            Statement stm=getConnection().createStatement();
            stm.execute(sql);
            stm.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
//INSERT INTO sc(Sno,Cno) VALUES(95001,3)
    public static <T extends Entity> boolean add(T entity){

        String sql="INSERT INTO "+entity.getType()+" (";
        Field[] fields=entity.getClass().getDeclaredFields();
        try {
            for(Field field:fields){
                System.out.println("get"+field.getName().toUpperCase().charAt(0)+
                        field.getName().substring(1));
                if(entity.getClass().getMethod("get"+field.getName().toUpperCase().charAt(0)+
                        field.getName().substring(1)).invoke(entity)==null){
                    continue;
                }
                sql+=field.getName()+", ";
            }
            sql=sql.substring(0,sql.length()-2);
            sql+=") VALUE (";

            for(Field field:fields){

                if(entity.getClass().getMethod("get"+field.getName().toUpperCase().charAt(0)+
                    field.getName().substring(1)).invoke(entity)==null){
                continue;
            }
                if(field.getType().getSimpleName().equals("String")){
                    sql+="'"+entity.getClass().getMethod("get"+field.getName().toUpperCase().charAt(0)+
                            field.getName().substring(1)).invoke(entity)+"', ";
                    continue;
                }

                sql+=entity.getClass().getMethod("get"+field.getName().toUpperCase().charAt(0)+
                        field.getName().substring(1)).invoke(entity)+", ";
            }
            sql=sql.substring(0,sql.length()-2);
            sql+=")";
        } catch (IllegalAccessException e) {
        e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        try {
            Statement stm=getConnection().createStatement();
            System.out.println(sql);
            stm.execute(sql);
            stm.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean check(String user,String password){
        String condition="user="+user+" AND "+"code="+password;
        UInfo uInfo=getOne(UInfo.class,condition);
        if(uInfo==null){
            return false;
        }
        return true;
    }

}
