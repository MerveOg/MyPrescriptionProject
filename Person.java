/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package medicinefollowupproject;

/**
 *
 * @author merve
 */
public class Person {

    String name;
    String email;
    String password;
    String gender;
    String personType;
    int age;
    int ID;

    public Person(String name, String email, String password, String gender, int age, int ID) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.age = age;
        this.ID = ID;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getID() {
        return ID;
    }
}
