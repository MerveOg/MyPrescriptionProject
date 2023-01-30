/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package medicinefollowupproject;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author merve
 */
public class DataConnection {

    private String kullanici_adi = "root";
    private String parola = "";

    private String db_ismi = "MyMedicineProject";

    private String host = "localhost";

    private int port = 3306;

    private Connection con = null;

    private Statement statement = null;
    private PreparedStatement preparedStatement = null;

    public DataConnection() {
        //jdbc:mysql://localhost:3306/demo
        String url = "jdbc:mysql://" + host + ":" + port + "/" + db_ismi + "?useUnicode=true&characterEncoding=utf8";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver could not find");
        }

        try {
            con = DriverManager.getConnection(url, kullanici_adi, parola);
            System.out.println("Connection succeed.");
        } catch (SQLException ex) {
            System.out.println("Connection unsucceed.");
            //ex.printStackTrace();
        }
    }

//Girilen bilgilere göre doktor bilgilerini doktor database'ine aktaracak metodu yazdım.
    public void addDoctor(String name, String email, String password, String gender, int age, int id) throws SQLException {
        statement = con.createStatement();

        String sorgu = "Insert Into doctors(name,email,password,gender,age,id)VALUES ('" + name + "','" + email + "','" + password + "','" + gender + "','" + age + "','" + id + "')";
        statement.executeUpdate(sorgu);
    }

//Girilen biigilere göre hasta bilgilerini hasta database'ine aktaracak metodu yazdım.
    public void addPatient(String name, String email, String password, String gender, int age, int id) throws SQLException {
        statement = con.createStatement();

        String sorgu = "Insert Into patients(name,email,password,gender,age,id)VALUES ('" + name + "','" + email + "','" + password + "','" + gender + "','" + age + "','" + id + "')";
        statement.executeUpdate(sorgu);
    }

//Doktorun girdiği bilgilere göre reçete bilgilerini reçete database'ine aktardım.
    public void prescribe(String name, String email, String medicine, String barcod, String daily, String weekly, String time, String hungry, String doctor) throws SQLException {
        statement = con.createStatement();

        String sorgu = "Insert Into prescribe(name,email,medicine,barcod,daily,weekly,time,hungry,doctor)VALUES ('" + name + "','" + email + "','" + medicine + "','" + barcod + "','" + daily + "','" + weekly + "','" + time + "','" + hungry + "','" + doctor + "')";
        statement.executeUpdate(sorgu);
    }

//doktor giriş yapacakken gireceği email ve passwordu doğru ise (önceden kayıt yapmışsa ve database'de o bilgilere karşılık
    //doktor bulunuyorsa) giriş yapabilecek.
    public boolean controlEnterDoctor(String email, String password) {
        String sorgu = "Select*From doctors where email = ? and password = ?";
        try {
            preparedStatement = con.prepareStatement(sorgu);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet executeQuery = preparedStatement.executeQuery();
            return executeQuery.next();
        } catch (SQLException ex) {
            Logger.getLogger(DataConnection.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

//hasta giriş yapacakken gireceği email ve passwordu doğru ise (önceden kayıt yapmışsa ve database'de o bilgilere karşılık
    //hasta bulunuyorsa) giriş yapabilecek.
    public boolean controlEnterPatient(String email, String password) {
        String sorgu = "Select * From patients where email = ? and password = ?";
        try {
            preparedStatement = con.prepareStatement(sorgu);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet executeQuery = preparedStatement.executeQuery();
            return executeQuery.next();
        } catch (SQLException ex) {
            Logger.getLogger(DataConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
//Girilen hasta bilgileri databasede bulunuyorsa doktorun randevu oluşturabilmesi için
    //bu metodu oluşturdum.

    public boolean controlPatientForPrescribe(String name, String email) {
        String sorgu = "Select * From patients where email = ? and name =?";
        try {
            preparedStatement = con.prepareStatement(sorgu);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, name);

            ResultSet executeQuery = preparedStatement.executeQuery();
            return executeQuery.next();
        } catch (SQLException ex) {
            Logger.getLogger(DataConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
//randevu bilgilerini bir arraylistte topladım ve doktor classında bu bilgileri tabloya aktardım.

    public ArrayList<Prescribe> showTable() {
        ArrayList<Prescribe> output = new ArrayList<Prescribe>();
        try {
            statement = con.createStatement();
            String sorgu = "Select * From prescribe";
            ResultSet rs = statement.executeQuery(sorgu);
            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String medicine = rs.getString("medicine");
                String barcod = rs.getString("barcod");
                String daily = rs.getString("daily");
                String weekly = rs.getString("weekly");
                String time = rs.getString("time");
                String hungry = rs.getString("hungry");
                String doctor = rs.getString("doctor");

                output.add(new Prescribe(name, email, medicine, barcod, daily, weekly, time, hungry, doctor));

            }
            return output;

        } catch (SQLException ex) {
            Logger.getLogger(DataConnection.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
//sisteme giriş yapan hastanın email adresi yardımıyla randevu bilgilerini çektim.

    public ArrayList<Prescribe> showPatientsPrescribesOnTable(String email) {
        ArrayList<Prescribe> output = new ArrayList<Prescribe>();
        try {
            statement = con.createStatement();
            String sorgu = "Select * From prescribe where email = ?";
            preparedStatement = con.prepareStatement(sorgu);
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                email = rs.getString("email");
                String medicine = rs.getString("medicine");
                String barcod = rs.getString("barcod");
                String daily = rs.getString("daily");
                String weekly = rs.getString("weekly");
                String time = rs.getString("time");
                String hungry = rs.getString("hungry");
                String doctor = rs.getString("doctor");

                output.add(new Prescribe(name, email, medicine, barcod, daily, weekly, time, hungry, doctor));

            }
            return output;

        } catch (SQLException ex) {
            Logger.getLogger(DataConnection.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
//verilen emaile ve doctor id'sine sahip reçete bilgisini silen metodu yazdım.

    public void deletePrescribe(String email, int id) {
        String sorgu = "Delete From prescribe Where email = ? and doctor =?";
        try {
            preparedStatement = con.prepareStatement(sorgu);
            preparedStatement.setString(1, email);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DataConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //doktor hesap oluşturmaya çalışırken girdiği (unique olması gereken) id ya da email'i başka 
    //bir kişide bulunup bulunmadığını kontrol eden boolean değer döndüren method yazdım.
    //Başka kişide bulunuyorsa o kişinin hesap oluşturabilmesi için yeni email ya da şifre girmesi gerekecek
    //Enter class'ında kontrolü yaptım.
    public boolean isThereThisDoctor(String email, int id) {
        String sorgu = "Select * from doctors where email = ? or id =? ";
        try {
            preparedStatement = con.prepareStatement(sorgu);
            preparedStatement.setString(1, email);
            preparedStatement.setInt(2, id);

            ResultSet executeQuery = preparedStatement.executeQuery();
            return executeQuery.next();
        } catch (SQLException ex) {
            Logger.getLogger(DataConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    //hasta hesap oluşturmaya çalışırken girdiği (unique olması gereken) id ya da email'i başka 
    //bir kişide bulunup bulunmadığını kontrol eden boolean değer döndüren method yazdım.
    //Başka kişide bulunuyorsa o kişinin hesap oluşturabilmesi için yeni email ya da şifre girmesi gerekecek
    //Enter class'ında kontrolü yaptım.
    public boolean isThereThisPatient(String email, int id) {
        String sorgu = "Select * from patients where email = ? or id =?";
        try {
            preparedStatement = con.prepareStatement(sorgu);
            preparedStatement.setString(1, email);
            preparedStatement.setInt(2, id);

            ResultSet executeQuery = preparedStatement.executeQuery();
            return executeQuery.next();
        } catch (SQLException ex) {
            Logger.getLogger(DataConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
//hasta şifresini update etmek istediği zaman gireceği email hastanın database bilgisinde
    //bulunuyorsa güncelleme yapabilmesini sağlayan metodu yazmak istedim.

    public boolean isThereThisEmailOnPatientForUpdatePass(String email) {
        String sorgu = "Select * from patients where email = ?";
        try {
            preparedStatement = con.prepareStatement(sorgu);
            preparedStatement.setString(1, email);

            ResultSet executeQuery = preparedStatement.executeQuery();
            return executeQuery.next();
        } catch (SQLException ex) {
            Logger.getLogger(DataConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
//doktor şifresini update etmek istediği zaman gireceği email doktorun database bilgisinde
    //bulunuyorsa güncelleme yapabilmesini sağlayan metodu yazmak istedim.

    public boolean isThereThisEmailOnDoctorsForUpdatePass(String email) {
        String sorgu = "Select * from doctors where email = ?";
        try {
            preparedStatement = con.prepareStatement(sorgu);
            preparedStatement.setString(1, email);

            ResultSet executeQuery = preparedStatement.executeQuery();
            return executeQuery.next();
        } catch (SQLException ex) {
            Logger.getLogger(DataConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
//doktorun şifresini güncelleyebilmesini sağlayan metodu yazdım.

    public void updatePasswordDoctor(String email, String newPassword) {
        String sorgu = "Update doctors set password = ? where email =?";
        try {
            preparedStatement = con.prepareStatement(sorgu);
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, email);
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DataConnection.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("işlem basarısız");
        }

    }

//hastanın şifresini güncelleyebilmesini sağlayan metodu yazdım.
    public void updatePasswordPatient(String email, String newPassword) {
        String sorgu = "Update patients set password = ? where email =?";
        try {
            preparedStatement = con.prepareStatement(sorgu);
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, email);
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DataConnection.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("işlem basarısız");
        }

    }

    //Enter tuşuna bastıktan sonra doktor bilgilerinin ekranda gözükmesi 
    //için bu metodu oluşturdum.
    public Doctor getDoctor(String email) {
        String sorgu = "Select * From doctors Where email = ?";
        try {
            preparedStatement = con.prepareStatement(sorgu);
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                email = rs.getString("email");
                String name = rs.getString("name");
                String password = rs.getString("password");
                String gender = rs.getString("gender");
                int age = rs.getInt("age");
                int id = rs.getInt("id");
                return new Doctor(name, email, password, gender, age, id);

            }
        } catch (SQLException ex) {
            Logger.getLogger(DataConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    //Enter tuşuna bastıktan sonra hasta bilgilerinin ekranda gözükmesi 
    //için bu metodu oluşturdum.
    public Patient getPatient(String email) {
        String sorgu = "Select * From patients Where email = ?";
        try {
            preparedStatement = con.prepareStatement(sorgu);
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                email = rs.getString("email");
                String name = rs.getString("name");
                String password = rs.getString("password");
                String gender = rs.getString("gender");
                int age = rs.getInt("age");
                int id = rs.getInt("id");
                return new Patient(name, email, password, gender, age, id);

            }
        } catch (SQLException ex) {
            Logger.getLogger(DataConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }

    public void setPreparedStatement(PreparedStatement preparedStatement) {
        this.preparedStatement = preparedStatement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public Statement getStatement() {
        return statement;
    }

}
