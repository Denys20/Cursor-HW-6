package com.example.JpaHibernate.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_user;
    private String firstName;
    private String lastName;
    private String email;
    private int age;

    public Users() {
    }

    public Users(String firstName, String lastName, String email, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }
    public Users(long id_user, String firstName, String lastName, String email, int age) {
        this.id_user = id_user;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id_user=" + id_user +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}
