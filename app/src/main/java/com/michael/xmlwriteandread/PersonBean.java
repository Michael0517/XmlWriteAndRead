package com.michael.xmlwriteandread;


public class PersonBean {
    public  String name = null;
    public  int    age  = 0;
    public  String sex  = null;
    public  String tel  = null;
    public  String address = null;

    public PersonBean(String name, int age, String tel, String sex, String address) {
        this.name = name;
        this.age = age;
        this.tel = tel;
        this.sex = sex;
        this.address = address;
    }

    public String getName() {

        return name;
    }

    public int getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }

    public String getTel() {
        return tel;
    }

    public String getAddress() {
        return address;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
