package model;

public class Info extends Entity {
    String name;
    Integer age;
    String sex;
    Integer ID;
    Integer grade;
    @Override
    public String getType(){
        return "mytest";
    }

    public Integer getGrade() { return grade; }

    public void setGrade(Integer grade) { this.grade = grade; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getID(){
        return ID;
    }

    public void setID(Integer ID){
        this.ID=ID;
    }
}
