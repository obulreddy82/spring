package com.rajtechnologies;

public class Employee {


    public int getAge() {
        System.out.println("Age is getting called"+age);
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        System.out.println("Address is getting called"+address);
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        System.out.println("Name is getting called"+name);
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    private int age;
    private String address;
}
