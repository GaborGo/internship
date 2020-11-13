package com.nexttech.internship.lambdasOnStreams;

public class Author {
    private int age;
    private String firstName;
    private String lastName;
    private String gender;

    public Author(int age, String firstName, String lastName, String gender) {
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return this.gender;
    }
}
