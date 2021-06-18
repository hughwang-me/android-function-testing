package com.uwjx.function.domain;

public class Person {

    private String name;
    private int age;
    private boolean isBody;

    public Person(String name, int age, boolean isBody) {
        this.name = name;
        this.age = age;
        this.isBody = isBody;
    }

    public boolean isBody() {
        return isBody;
    }

    public void setBody(boolean body) {
        isBody = body;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
