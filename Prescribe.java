/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package medicinefollowupproject;

/**
 *
 * @author merve
 */
public class Prescribe {

    String name;
    String email;
    String medicine;
    String barcod;
    String daily;
    String weekly;
    String time;
    String hungry;
    String doctor;

    public Prescribe(String name, String email, String medicine, String barcod, String daily, String weekly, String time, String hungry, String doctor) {
        this.name = name;
        this.email = email;
        this.medicine = medicine;
        this.barcod = barcod;
        this.daily = daily;
        this.weekly = weekly;
        this.time = time;
        this.hungry = hungry;
        this.doctor = doctor;
    }

    public String getBarcod() {
        return barcod;
    }

    public String getDaily() {
        return daily;
    }

    public String getEmail() {
        return email;
    }

    public String getHungry() {
        return hungry;
    }

    public String getMedicine() {
        return medicine;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public String getWeekly() {
        return weekly;
    }

    public String getDoctor() {
        return doctor;
    }

}
