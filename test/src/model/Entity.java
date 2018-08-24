package model;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;

public abstract class Entity {
    abstract String getType();

    public void setProperties(HashMap<String,String> info){
        Field[] fields=this.getClass().getFields();
        for(Field field:fields){
            String name=field.getName();
            Object value=changeType(info.get(name),field);
            if(value!=null){
                try {
                    this.getClass().getMethod("set"+firstUp(name),value.getClass()).invoke(this,value);
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
            case "int" : return Integer.valueOf(value);
            case "float" :return Float.valueOf(value);
            case "double" :return Double.valueOf(value);
            case "long" :return Long.valueOf(value);
            case "boolean" :return Boolean.valueOf(value);
            case "Date" :return Date.valueOf(value);
        }
        return value;
    }

    public String firstUp(String name){
        name=name.toUpperCase().charAt(0)+name.substring(1);
        return name;
    }
}
