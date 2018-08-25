package model;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Entity {
    abstract public String getType();



    public<T extends Entity> void setProperties(HashMap<String,String> info,Class<T> clazz){
        Field[] fields=clazz.getDeclaredFields();
        for(Field field:fields){
            String name=field.getName();
            Object value=changeType(info.get(name),field);

            if(value!=null){
                try {
                    System.out.println("set"+firstUp(name));
                    this.getClass().getMethod("set"+firstUp(name),value.getClass()).invoke(this,value);
                    System.out.println(this.getClass().getMethod("set"+firstUp(name),value.getClass()).toString());
                } catch (Exception e) {
                    e.printStackTrace();
                        continue;
                }

            }



        }
    }

    public Object changeType(String value,Field field){
        String type=field.getType().getSimpleName();
        switch (type){
            case "boolean" :return Boolean.valueOf(value);
            case "Date" :return Date.valueOf(value);
            case "Integer":return Integer.valueOf(value);
            case "Double":return Double.valueOf(value);
            case "Character":return Character.valueOf(value.charAt(0));
        }
        return value;
    }

    public String firstUp(String name){
        name=name.toUpperCase().charAt(0)+name.substring(1);
        return name;
    }

    public Map<String,Object> toMap(){
        Map<String,Object> result=new HashMap<>();
        Field[] fields=this.getClass().getDeclaredFields();
        for(Field field:fields){
            Object temp= null;
            try {
                temp = this.getClass().getMethod("get"+firstUp(field.getName())).invoke(this);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return null;
            } catch (InvocationTargetException e) {
                e.printStackTrace();
                return null;
            } catch (NoSuchMethodException e) {
                continue;
            }
            result.put(field.getName(),temp);
        }
        return result;
    }
}
