package model;

public class Info extends Entity {
    String name;
    String age;
    String sex;
    String ID;
    String grade;
    public String getType(){
        return "mytest";
    }

    public String getGrade() { return grade; }

    public void setGrade(String grade) { this.grade = grade; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getID(){
        return ID;
    }

    public void setID(String ID){
        this.ID=ID;
    }
}
