package com.thesoftparrot.classlecture.ali.model;

import java.io.Serializable;

public class Friend implements Serializable {
    private long age;
    private String userId, email, image;

    public Friend() {}

    public Friend(long age, String userId, String email, String image) {
        this.age = age;
        this.userId = userId;
        this.email = email;
        this.image = image;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
