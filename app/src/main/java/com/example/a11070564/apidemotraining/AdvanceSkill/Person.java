package com.example.a11070564.apidemotraining.AdvanceSkill;

import java.io.Serializable;

/**
 * Created by 11070564 on 2017/9/25.
 */

public class Person implements Serializable {
    private String name;
    private int age;

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }
}

