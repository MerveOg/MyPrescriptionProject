/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package medicinefollowupproject;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 *
 * @author merve
 */
//Generic sınıf oluşturdum.
public class ShowInfoOfCompany<T> {

    T thingtoPrint;

    public ShowInfoOfCompany(T thingtoPrint) {
        this.thingtoPrint = thingtoPrint;

    }
//    public void print(JLabel label){
//        label.setText((String) thingtoPrint);
//    }

    public void print(JTextArea textArea) {
        textArea.setText((String.valueOf(thingtoPrint)));
    }
}
