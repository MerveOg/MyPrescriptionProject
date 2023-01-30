package medicinefollowupproject;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import java.sql.*;
import java.text.AttributedCharacterIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static javax.swing.DropMode.INSERT;
import javax.swing.JFrame;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author merve
 */
public class Enter extends javax.swing.JFrame {
//Merve Og
    SpinnerNumberModel spinner = new SpinnerNumberModel(7, 7, 150, 1);
    ArrayList doctorList = new ArrayList();
    ArrayList patientList = new ArrayList();
//    static Connection con;

    /**
     * Creates new form Enter
     */
    public Enter() {
        initComponents();

        ageSpinner.setModel(spinner);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

//email başında sayı ile başlamamalı. @gmail.com içermeli.
    public boolean isCorrectEmail(String email) {
        Matcher matcher;
        matcher = Pattern.compile("^[^0-9]\\w*@((gmail))(.com)$").matcher(email);
        if (!matcher.matches()) {
            return false;
        } else {
            return true;
        }
    }

    //Başlangıçta min 1 maksimum 3 tane rakam olmalı. Devamında min 1 maksimum 9 büyük harf içermeli.Sonrasında küçük harf olmalı.
    public boolean isCorrectPassword(String password) {
        Matcher matcher;

        matcher = Pattern.compile("^[0-9]{1,3}[A-Z]{1,9}[a-z]{1,8}").matcher(password);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }
//İsim minimum 3 harften oluşmalı, boşluk olmalı, soyisim minimum 2 harften oluşmalı.

    public boolean isCorrectName(String name) {
        Matcher matcher;
        matcher = Pattern.compile("^[A-z]{3,}\\s[A-z]{2,}$").matcher(name);
        if (!matcher.matches()) {
            return false;
        } else {
            return true;
        }
    }
//ID 1 rakamı ile başlamalı, devamında 9 rakam içermeli.

    public boolean isCorrectId(int id) {

        Matcher matcher;
        matcher = Pattern.compile("^1[0-9]{9}$").matcher(String.valueOf(id));
        if (!matcher.matches()) {
            return false;
        } else {
            return true;
        }
    }
//Burada belli kontrolleri gerçekleştirdikten sonra yeni hesap oluşturacak bir method oluşturdum. Eğer girilen bilgilerde bir 
    //kişi varsa o kişiyi ekleme yapmadım ve uyarı verdim. 

    public void NewAccountİnformation() throws SQLException {
        String gender;
        String name;
        String email;
        String password = "";
        String personType = personTypeBox.getSelectedItem().toString();
        boolean isThereFreeSpace = false;
        DataConnection connect = new DataConnection();
        if (femaleButton.isSelected()) {
            gender = "Female";
        } else {
            gender = "Male";
        }
        if (txtId.getText().isEmpty() || txtNameSurname.getText().isEmpty() || txtEmail.getText().isEmpty() || passwordArea.getText().isEmpty() || !acceptCheckBox.isSelected() || passwordAgainArea.getText().isEmpty()) {
            isThereFreeSpace = true;
        }
        if (isThereFreeSpace) {
            JOptionPane.showMessageDialog(rootPane, "Fill All Areas", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            name = txtNameSurname.getText();
            boolean correctName = isCorrectName(name);
            if (!correctName) {
                JOptionPane.showMessageDialog(rootPane, "UNCORRECT name:Merve Og, merve og");
                return;
            }
            email = txtEmail.getText();
            boolean correctEmail = isCorrectEmail(email);
            if (!correctEmail) {
                JOptionPane.showMessageDialog(rootPane, "UNCORRECT EMAİL: merveog@gmail.com");
                return;
            }
            int age = Integer.parseInt(ageSpinner.getValue().toString());

            int ID = Integer.parseInt(txtId.getText().toString());
            boolean correctId = isCorrectId(ID);
            if (!correctId) {
                JOptionPane.showMessageDialog(rootPane, "UNCORRECT Id:Id should start with 1 and should contain 9 count");
                return;
            }

            String toString = Arrays.toString(passwordArea.getPassword());
            String toString1 = Arrays.toString(passwordAgainArea.getPassword());

            if (toString.equals(toString1) && personTypeBox.getSelectedIndex() == 0) {

                password = passwordArea.getText();
                boolean isCorrectPassword = isCorrectPassword(password);
                if (!isCorrectPassword) {

                    JOptionPane.showMessageDialog(rootPane, "UNCORRECT password:At the start there must be min 1 mak 3 number,ıt should continue min 1 mak 9 big word,ıt should continue min 1 mak 8 little word");
                    return;
                }
                Person person = new Person(name, email, password, gender, age, ID);
                boolean thereThisDoctor = connect.isThereThisDoctor(email, ID);
                if (!thereThisDoctor) {
                    if (Integer.parseInt(ageSpinner.getValue().toString()) < 25) {
                        JOptionPane.showMessageDialog(rootPane, "Doctor please check your age. Your age must be bigger than 24");
                    } else {
                        connect.addDoctor(person.getName(), person.getEmail(), person.getPassword(), person.getGender(), person.getAge(), person.getID());

                        doctorList.add(person);

                        JOptionPane.showMessageDialog(rootPane, "IT IS SUCCESSFULL DEAR DOCTOR", "GREAT", JOptionPane.INFORMATION_MESSAGE);
                        this.dispose();

                        Enter enter = new Enter();
                        enter.setVisible(true);
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "This email or id already exist", "Warning", JOptionPane.WARNING_MESSAGE);

                }
            } else if (toString.equals(toString1) && personTypeBox.getSelectedIndex() == 1) {
                password = passwordArea.getText();

                Person person = new Person(name, email, password, gender, age, ID);
                boolean thereThisPatient = connect.isThereThisPatient(email, ID);
                if (!thereThisPatient) {
                    connect.addPatient(person.getName(), person.getEmail(), person.getPassword(), person.getGender(), person.getAge(), person.getID());
                    patientList.add(person);

                    JOptionPane.showMessageDialog(rootPane, "IT IS SUCCESSFULL DEAR PATİENT", "Great", JOptionPane.INFORMATION_MESSAGE);

                    this.dispose();
                    Enter enter = new Enter();
                    enter.setVisible(true);
                    patientBtn.setSelected(true);
                } else {
                    JOptionPane.showMessageDialog(rootPane, "This email or id already exist", "Warning", JOptionPane.WARNING_MESSAGE);

                }
            } else {
                passwordArea.setText(null);
                passwordAgainArea.setText(null);

                JOptionPane.showMessageDialog(rootPane, "Passwords did not match. Try again", "Warning", JOptionPane.WARNING_MESSAGE);

            }

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroupGender = new javax.swing.ButtonGroup();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        buttonGroup2 = new javax.swing.ButtonGroup();
        btnGroupPersonType = new javax.swing.ButtonGroup();
        updateDialog = new javax.swing.JDialog();
        dialogOldPass = new javax.swing.JLabel();
        dialogNewPass = new javax.swing.JLabel();
        updateOldPass = new javax.swing.JPasswordField();
        updateNewPass = new javax.swing.JPasswordField();
        updatePasswordButton = new javax.swing.JButton();
        dialogEmail = new javax.swing.JLabel();
        updatePassEmailArea = new javax.swing.JTextField();
        jSplitPane1 = new javax.swing.JSplitPane();
        panelWelcome = new javax.swing.JPanel();
        btnGoMain = new javax.swing.JButton();
        ıconMedicineLabel = new javax.swing.JLabel();
        ıconPersonLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        enterTabbedPane = new javax.swing.JTabbedPane();
        panelSignIn = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnEnterAccount = new javax.swing.JButton();
        txtEnterEmail = new javax.swing.JTextField();
        txtEnterPass = new javax.swing.JPasswordField();
        doctorBtn = new javax.swing.JRadioButton();
        patientBtn = new javax.swing.JRadioButton();
        labelWarn = new javax.swing.JLabel();
        btnUpdatePass = new javax.swing.JButton();
        panelCreateAccuont = new javax.swing.JPanel();
        lblName = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblPass = new javax.swing.JLabel();
        acceptCheckBox = new javax.swing.JCheckBox();
        createBtn = new javax.swing.JButton();
        txtNameSurname = new javax.swing.JTextField();
        passwordArea = new javax.swing.JPasswordField();
        txtEmail = new javax.swing.JTextField();
        femaleButton = new javax.swing.JRadioButton();
        maleButton = new javax.swing.JRadioButton();
        lblGender = new javax.swing.JLabel();
        ageSpinner = new javax.swing.JSpinner();
        lblAge = new javax.swing.JLabel();
        lblPassAgain = new javax.swing.JLabel();
        passwordAgainArea = new javax.swing.JPasswordField();
        personTypeBox = new javax.swing.JComboBox<>();
        lblPersonType = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        enterMenuBar = new javax.swing.JMenuBar();
        menuContact = new javax.swing.JMenu();
        menuContactCompany = new javax.swing.JCheckBoxMenuItem();
        contactSeperator = new javax.swing.JPopupMenu.Separator();
        menuContactServices = new javax.swing.JCheckBoxMenuItem();

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        updateDialog.setBackground(new java.awt.Color(255, 255, 255));

        dialogOldPass.setText("Old Password");

        dialogNewPass.setText("New Password");

        updatePasswordButton.setText("Update");
        updatePasswordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatePasswordButtonActionPerformed(evt);
            }
        });

        dialogEmail.setText("Email");

        javax.swing.GroupLayout updateDialogLayout = new javax.swing.GroupLayout(updateDialog.getContentPane());
        updateDialog.getContentPane().setLayout(updateDialogLayout);
        updateDialogLayout.setHorizontalGroup(
            updateDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateDialogLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(updateDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(updatePasswordButton)
                    .addGroup(updateDialogLayout.createSequentialGroup()
                        .addGroup(updateDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dialogOldPass)
                            .addComponent(dialogNewPass)
                            .addComponent(dialogEmail))
                        .addGap(61, 61, 61)
                        .addGroup(updateDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(updateOldPass, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                            .addComponent(updateNewPass)
                            .addComponent(updatePassEmailArea))))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        updateDialogLayout.setVerticalGroup(
            updateDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateDialogLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(updateDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dialogOldPass)
                    .addComponent(updateOldPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(updateDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dialogNewPass)
                    .addComponent(updateNewPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(updateDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updatePassEmailArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dialogEmail))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                .addComponent(updatePasswordButton)
                .addGap(14, 14, 14))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(700, 700));
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        panelWelcome.setBackground(new java.awt.Color(255, 255, 255));
        panelWelcome.setMinimumSize(new java.awt.Dimension(700, 250));
        panelWelcome.setPreferredSize(new java.awt.Dimension(700, 250));

        btnGoMain.setText("Go to Main Page");
        btnGoMain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoMainActionPerformed(evt);
            }
        });

        ıconMedicineLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/medicinefollowupproject/drugs.png"))); // NOI18N
        ıconMedicineLabel.setText("                 WELCOME TO THE LOGİN PAGE");

        ıconPersonLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/medicinefollowupproject/medical.png"))); // NOI18N

        javax.swing.GroupLayout panelWelcomeLayout = new javax.swing.GroupLayout(panelWelcome);
        panelWelcome.setLayout(panelWelcomeLayout);
        panelWelcomeLayout.setHorizontalGroup(
            panelWelcomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelWelcomeLayout.createSequentialGroup()
                .addGroup(panelWelcomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelWelcomeLayout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(ıconMedicineLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                        .addComponent(ıconPersonLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelWelcomeLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGoMain)))
                .addGap(35, 35, 35))
        );
        panelWelcomeLayout.setVerticalGroup(
            panelWelcomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelWelcomeLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelWelcomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ıconMedicineLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                    .addComponent(ıconPersonLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(btnGoMain)
                .addContainerGap())
        );

        jSplitPane1.setLeftComponent(panelWelcome);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setMinimumSize(new java.awt.Dimension(700, 450));
        jPanel2.setPreferredSize(new java.awt.Dimension(700, 450));
        jPanel2.setVerifyInputWhenFocusTarget(false);

        enterTabbedPane.setBackground(new java.awt.Color(204, 204, 255));
        enterTabbedPane.setForeground(new java.awt.Color(0, 153, 102));

        panelSignIn.setBackground(new java.awt.Color(0, 153, 153));

        jLabel5.setText("E-MAİL");

        jLabel9.setText("PASSWORD");

        btnEnterAccount.setText("ENTER");
        btnEnterAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnterAccountActionPerformed(evt);
            }
        });

        btnGroupPersonType.add(doctorBtn);
        doctorBtn.setText("DOCTOR");

        btnGroupPersonType.add(patientBtn);
        patientBtn.setText("PATIENT");

        labelWarn.setText("WARNING! If you do not have account create an account");

        btnUpdatePass.setText("Update Password");
        btnUpdatePass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdatePassActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelSignInLayout = new javax.swing.GroupLayout(panelSignIn);
        panelSignIn.setLayout(panelSignInLayout);
        panelSignInLayout.setHorizontalGroup(
            panelSignInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSignInLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEnterAccount)
                .addGap(84, 84, 84))
            .addGroup(panelSignInLayout.createSequentialGroup()
                .addGroup(panelSignInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSignInLayout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addGroup(panelSignInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelSignInLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(77, 77, 77)
                                .addComponent(txtEnterPass, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(labelWarn)
                            .addGroup(panelSignInLayout.createSequentialGroup()
                                .addComponent(doctorBtn)
                                .addGap(222, 222, 222)
                                .addComponent(patientBtn))
                            .addGroup(panelSignInLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(100, 100, 100)
                                .addComponent(txtEnterEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panelSignInLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(btnUpdatePass)))
                .addContainerGap(95, Short.MAX_VALUE))
        );
        panelSignInLayout.setVerticalGroup(
            panelSignInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSignInLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(panelSignInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(doctorBtn)
                    .addComponent(patientBtn))
                .addGap(47, 47, 47)
                .addGroup(panelSignInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtEnterEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(panelSignInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEnterPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGroup(panelSignInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSignInLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(btnEnterAccount))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSignInLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdatePass, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(labelWarn)
                .addGap(88, 88, 88))
        );

        enterTabbedPane.addTab("Sign İn", panelSignIn);

        panelCreateAccuont.setBackground(new java.awt.Color(0, 153, 153));

        lblName.setText("NAME-SURNAME");

        lblEmail.setText("E-MAİL");

        lblPass.setText("PASSWORD");

        acceptCheckBox.setText("Do you accept all engagement");
        acceptCheckBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                acceptCheckBoxMouseClicked(evt);
            }
        });

        createBtn.setText("CREATE ACCOUNT");
        createBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createBtnActionPerformed(evt);
            }
        });

        btnGroupGender.add(femaleButton);
        femaleButton.setText("FEMALE");

        btnGroupGender.add(maleButton);
        maleButton.setText("MALE");

        lblGender.setText("GENDER");

        lblAge.setText("AGE");

        lblPassAgain.setText("PASSWORD AGAİN");

        personTypeBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Doctor", "Patient" }));

        lblPersonType.setText("PERSON TYPE");

        lblId.setText("PERSON ID");

        javax.swing.GroupLayout panelCreateAccuontLayout = new javax.swing.GroupLayout(panelCreateAccuont);
        panelCreateAccuont.setLayout(panelCreateAccuontLayout);
        panelCreateAccuontLayout.setHorizontalGroup(
            panelCreateAccuontLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCreateAccuontLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(panelCreateAccuontLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCreateAccuontLayout.createSequentialGroup()
                        .addGroup(panelCreateAccuontLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblName)
                            .addComponent(lblEmail)
                            .addComponent(lblGender)
                            .addGroup(panelCreateAccuontLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lblPassAgain, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblPass, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(lblId))
                        .addGroup(panelCreateAccuontLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelCreateAccuontLayout.createSequentialGroup()
                                .addGap(72, 72, 72)
                                .addComponent(femaleButton)
                                .addGap(97, 97, 97)
                                .addComponent(maleButton)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCreateAccuontLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                                .addGroup(panelCreateAccuontLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelCreateAccuontLayout.createSequentialGroup()
                                        .addComponent(personTypeBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(33, 33, 33)
                                        .addComponent(lblAge)
                                        .addGap(51, 51, 51)
                                        .addComponent(ageSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelCreateAccuontLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(createBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(panelCreateAccuontLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtEmail)
                                            .addComponent(txtNameSurname)
                                            .addComponent(passwordArea, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
                                            .addComponent(passwordAgainArea)
                                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                    .addGroup(panelCreateAccuontLayout.createSequentialGroup()
                        .addGroup(panelCreateAccuontLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(acceptCheckBox)
                            .addComponent(lblPersonType))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(119, 119, 119))
        );
        panelCreateAccuontLayout.setVerticalGroup(
            panelCreateAccuontLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCreateAccuontLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(panelCreateAccuontLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblName)
                    .addComponent(txtNameSurname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelCreateAccuontLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelCreateAccuontLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPass)
                    .addComponent(passwordArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelCreateAccuontLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPassAgain)
                    .addComponent(passwordAgainArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(panelCreateAccuontLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGender)
                    .addComponent(femaleButton)
                    .addComponent(maleButton))
                .addGap(26, 26, 26)
                .addGroup(panelCreateAccuontLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblId)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelCreateAccuontLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(personTypeBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPersonType)
                    .addComponent(lblAge, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ageSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelCreateAccuontLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(acceptCheckBox)
                    .addComponent(createBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        enterTabbedPane.addTab("Create An Account", panelCreateAccuont);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(enterTabbedPane)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(enterTabbedPane)
        );

        jSplitPane1.setRightComponent(jPanel2);

        getContentPane().add(jSplitPane1);

        menuContact.setText("Contact");

        menuContactCompany.setText("Contact with our company");
        menuContactCompany.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuContactCompanyActionPerformed(evt);
            }
        });
        menuContact.add(menuContactCompany);
        menuContact.add(contactSeperator);

        menuContactServices.setText("Customer Services");
        menuContactServices.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuContactServicesActionPerformed(evt);
            }
        });
        menuContact.add(menuContactServices);

        enterMenuBar.add(menuContact);

        setJMenuBar(enterMenuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents
//Yeni hesap oluşturma metodumu çağırdım. 
    private void createBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createBtnActionPerformed
        try {

            NewAccountİnformation();
        } catch (SQLException ex) {
            Logger.getLogger(Enter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_createBtnActionPerformed
//Gerekli kontrolleri gerçekleştirdikten sonra database'de o kişi yer alıyorsa kişinin sistemine giriş yapmasını sağladım.
    private void btnEnterAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnterAccountActionPerformed
        // TODO add your handling code here:
        DataConnection connect = new DataConnection();
        String email = txtEnterEmail.getText();
        String password = new String(txtEnterPass.getPassword());
        if (txtEnterEmail.getText().isEmpty() || txtEnterPass.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Fill All Areas", "Warning", JOptionPane.WARNING_MESSAGE);

        } else {
            if (doctorBtn.isSelected()) {

                boolean isSuccessEnter = connect.controlEnterDoctor(email, password);
                if (isSuccessEnter) {
                    Doctor doctor = connect.getDoctor(email);

                    this.dispose();
                    doctor.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Could not entered. Please check your informations", "WARNİNG", JOptionPane.WARNING_MESSAGE);
                }

            } else {
                boolean isSuccessEnter = connect.controlEnterPatient(email, password);
                if (isSuccessEnter) {
                    Patient patient = connect.getPatient(email);
                    this.dispose();
                    patient.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Could not entered. Please check your informations", "WARNİNG", JOptionPane.WARNING_MESSAGE);

                }

            }
        }

    }//GEN-LAST:event_btnEnterAccountActionPerformed
//Burada kişi bilgilerini kaydetmememize izin veriyor mu kontrolü yaptım.
    private void acceptCheckBoxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_acceptCheckBoxMouseClicked

        if (acceptCheckBox.isSelected()) {
            int a = JOptionPane.showConfirmDialog(rootPane, "We care about your privacy. İf you do not accept this you can not log in. Are you sure to accept ?", "Our Engagement", JOptionPane.YES_NO_OPTION);
            if (a == JOptionPane.YES_OPTION) {
                acceptCheckBox.setSelected(true);
            } else {
                acceptCheckBox.setSelected(false);
                JOptionPane.showMessageDialog(rootPane, "You can not sign in!", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_acceptCheckBoxMouseClicked

//Main page sayfasını açmasını sağladım ve bu sayfayı kapattım.
    private void btnGoMainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoMainActionPerformed
        // TODO add your handling code here:
        MainPage main = new MainPage();
        main.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnGoMainActionPerformed
//Şifresini update etmesi için kişi tipini seçmesi için kontrol sağladım.Biri seçilmiş ise şifreyi update etmesi için dialogu açtım.
    private void btnUpdatePassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdatePassActionPerformed
        if (doctorBtn.isSelected() || patientBtn.isSelected()) {
            updateDialog.setVisible(true);
            updateDialog.setBounds(0, 0, 500, 500);
        } else {
            JOptionPane.showMessageDialog(rootPane, "Please choose your type patient or doctor", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnUpdatePassActionPerformed
//Şifresini update etmesi için belirli kontroller gerçekleştirdim ve sonrasında şifresini update etmesini sağladım.
    private void updatePasswordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatePasswordButtonActionPerformed
        DataConnection connect = new DataConnection();
        String toString = Arrays.toString(updateOldPass.getPassword());
        String toString2 = Arrays.toString(updateNewPass.getPassword());
        boolean isCorrectPassword = isCorrectPassword(updateNewPass.getText());
        if (!isCorrectPassword) {
            JOptionPane.showMessageDialog(rootPane, "UNCORRECT password:At the start there must be min 1 mak 3 number,ıt should continue min 1 mak 9 big word,ıt should continue min 1 mak 8 little word");
            return;
        }

        boolean isThereFreePlace = false;

        if (updatePassEmailArea.getText().isEmpty() || updateOldPass.getText().isEmpty() || updateNewPass.getText().isEmpty()) {
            isThereFreePlace = true;

        }

        if (isThereFreePlace) {
            JOptionPane.showMessageDialog(rootPane, "Fill all area", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            if (toString.equals(toString2)) {
                JOptionPane.showMessageDialog(rootPane, "Passwords are same", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (doctorBtn.isSelected()) {
                int showConfirmDialog = JOptionPane.showConfirmDialog(rootPane, "Are you sure to change your password", "Warning", JOptionPane.YES_NO_OPTION);
                if (showConfirmDialog == JOptionPane.YES_OPTION) {
                    if (!connect.isThereThisEmailOnDoctorsForUpdatePass(updatePassEmailArea.getText())) {
                        JOptionPane.showMessageDialog(rootPane, "Please check your email");
                        return;
                    }
                    connect.updatePasswordDoctor(updatePassEmailArea.getText(), updateNewPass.getText());
                    JOptionPane.showMessageDialog(rootPane, "Password changed succecfully");
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Password did not changed");
                }
            } else {
                int showConfirmDialog = JOptionPane.showConfirmDialog(rootPane, "Are you sure to change your password", "Warning", JOptionPane.YES_NO_OPTION);
                if (showConfirmDialog == JOptionPane.YES_OPTION) {
                    if (!connect.isThereThisEmailOnPatientForUpdatePass(updatePassEmailArea.getText())) {
                        JOptionPane.showMessageDialog(rootPane, "Please check your email");
                        return;
                    }
                    connect.updatePasswordPatient(updatePassEmailArea.getText(), updateNewPass.getText());
                    JOptionPane.showMessageDialog(rootPane, "Password changed succecfully");
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Password did not changed");

                }
            }
        }

    }//GEN-LAST:event_updatePasswordButtonActionPerformed
//Menu ıtema basınca iletişim numarasını gösterdim.
    private void menuContactServicesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuContactServicesActionPerformed
        // TODO add your handling code here:
        if (menuContactServices.isSelected()) {
            JOptionPane.showMessageDialog(rootPane, "Company Number: 444 55 66");
            menuContactCompany.setSelected(false);
        }
    }//GEN-LAST:event_menuContactServicesActionPerformed
//Menu ıtema basınca iletişim numarasını gösterdim.
    private void menuContactCompanyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuContactCompanyActionPerformed
        // TODO add your handling code here:
        if (menuContactCompany.isSelected()) {
            JOptionPane.showMessageDialog(rootPane, "Customer Services: 212 55 66");
            menuContactServices.setSelected(false);
        }
    }//GEN-LAST:event_menuContactCompanyActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Enter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Enter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Enter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Enter.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Enter().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox acceptCheckBox;
    public static javax.swing.JSpinner ageSpinner;
    private javax.swing.JButton btnEnterAccount;
    private javax.swing.JButton btnGoMain;
    private javax.swing.ButtonGroup btnGroupGender;
    private javax.swing.ButtonGroup btnGroupPersonType;
    private javax.swing.JButton btnUpdatePass;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JPopupMenu.Separator contactSeperator;
    private javax.swing.JButton createBtn;
    private javax.swing.JLabel dialogEmail;
    private javax.swing.JLabel dialogNewPass;
    private javax.swing.JLabel dialogOldPass;
    private javax.swing.JRadioButton doctorBtn;
    private javax.swing.JMenuBar enterMenuBar;
    private javax.swing.JTabbedPane enterTabbedPane;
    public static javax.swing.JRadioButton femaleButton;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JLabel labelWarn;
    public static javax.swing.JLabel lblAge;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblGender;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblPass;
    private javax.swing.JLabel lblPassAgain;
    private javax.swing.JLabel lblPersonType;
    public static javax.swing.JRadioButton maleButton;
    private javax.swing.JMenu menuContact;
    private javax.swing.JCheckBoxMenuItem menuContactCompany;
    private javax.swing.JCheckBoxMenuItem menuContactServices;
    private javax.swing.JPanel panelCreateAccuont;
    private javax.swing.JPanel panelSignIn;
    private javax.swing.JPanel panelWelcome;
    public static javax.swing.JPasswordField passwordAgainArea;
    public static javax.swing.JPasswordField passwordArea;
    private javax.swing.JRadioButton patientBtn;
    public static javax.swing.JComboBox<String> personTypeBox;
    public static javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEnterEmail;
    private javax.swing.JPasswordField txtEnterPass;
    public static javax.swing.JTextField txtId;
    public static javax.swing.JTextField txtNameSurname;
    private javax.swing.JDialog updateDialog;
    private javax.swing.JPasswordField updateNewPass;
    private javax.swing.JPasswordField updateOldPass;
    private javax.swing.JTextField updatePassEmailArea;
    private javax.swing.JButton updatePasswordButton;
    private javax.swing.JLabel ıconMedicineLabel;
    private javax.swing.JLabel ıconPersonLabel;
    // End of variables declaration//GEN-END:variables
}
