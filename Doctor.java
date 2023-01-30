package medicinefollowupproject;

import java.awt.Color;
import java.awt.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author merve
 */
public class Doctor extends javax.swing.JFrame {

    DefaultListModel list2 = new DefaultListModel();
    DefaultListModel list1 = new DefaultListModel();
    DefaultTableModel table;

    ArrayList array = new ArrayList();

    @Override
    public void setDefaultCloseOperation(int operation) {
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Creates new form Doctor
     */
    public Doctor() {
        initComponents();
        this.setLocationRelativeTo(null);
        medicinesList.setModel(list2);
        prescribeInfoList.setModel(list1);
        table = (DefaultTableModel) prescribesTable.getModel();
        showInformations();

    }

    public Doctor(String name, String email, String password, String gender, int age, int ID) {
        initComponents();
        this.setLocationRelativeTo(null);
        prescribeInfoList.setModel(list1);
        medicinesList.setModel(list2);
        table = (DefaultTableModel) prescribesTable.getModel();
        showInformations();

        labelDoctorId.setText(String.valueOf(ID));
        labelAccNameData.setText(name);
        labelAccEmailData.setText(email);
        labelAccPassData.setText(password);
        labelAccAgeData.setText(String.valueOf(age));
        labelAccIdData.setText(String.valueOf(ID));
        txtDoctorId.setText(String.valueOf(ID));

    }
//Databaseden reçete verilerini çekip tabloya ekleyen metodu yazdım.

    final public void showInformations() {
        DataConnection connect = new DataConnection();

        table.setRowCount(0);
        ArrayList<Prescribe> output = new ArrayList<Prescribe>();
        output = connect.showTable();
        if (output != null) {
            for (Prescribe pres : output) {
                Object[] add = {pres.getName(), pres.getEmail(), pres.getMedicine(), pres.getBarcod(), pres.getDaily(), pres.getWeekly(), pres.getTime(), pres.getHungry(), pres.getDoctor()};
                table.addRow(add);
            }
        }

    }
//Doktor hastalık belirtisi girdikten sonra o belirtiye karşılık gelen ilaçları databaseden çekerek listeye ekleyen  metodu yazdım.

    public void addList() {
        DataConnection connect = new DataConnection();
        String sorgu = "Select*From  medicines WHERE illness ='" + txtPrescribeMedicine.getText() + "'";
        try {
            connect.setStatement(connect.getCon().createStatement());

        } catch (SQLException ex) {

            System.out.println("Hata var.");
        }
        ResultSet rs;
        try {
            rs = connect.getStatement().executeQuery(sorgu);
            while (rs.next()) {
                String name = rs.getString("name");
                list2.addElement(name);
                System.out.println(name);
            }

        } catch (SQLException ex) {

            System.out.println("Hata var");
        }

    }
//Belirli kontrolleri gerçekleştirdikten sonra database reçete verilerini aktardım.

    public void prescribes() throws SQLException {
        DataConnection connect = new DataConnection();
        int confirm = JOptionPane.showConfirmDialog(rootPane, "Are you sure for prescribe", "Warning", JOptionPane.YES_NO_CANCEL_OPTION);
        if (txtPrescribeName.getText().isEmpty() || txtPrescribeEmail.getText().isEmpty() || txtBarcod.getText().isEmpty() || txtDoctorId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Fill all area", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (medicinesList.getSelectedValue() == null) {
            JOptionPane.showMessageDialog(rootPane, "Please select medicine", "Warnıng", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String name = txtPrescribeName.getText();
        String email = txtPrescribeEmail.getText();
        String medicine = medicinesList.getSelectedValue();
        String barcod = txtBarcod.getText();
        String daily = dailyPotion.getSelectedItem().toString();
        String weekly = weeklyPotion.getSelectedItem().toString();
        String time = timeToDrink.getSelectedItem().toString();
        String hungry;
        if (checkBoxHungry.isSelected()) {
            hungry = "Hungry";
        } else {
            hungry = "Full";
        }
        String doctor = txtDoctorId.getText();
        boolean isControlPatientSuccess = connect.controlPatientForPrescribe(name, email);
        if (isControlPatientSuccess) {
            if (confirm == JOptionPane.YES_OPTION) {
                Prescribe prescribe = new Prescribe(name, email, medicine, barcod, daily, weekly, time, hungry, doctor);
                String prescribeInfo = "Name: " + prescribe.getName() + " Email: " + prescribe.getEmail() + " Medicine: " + prescribe.getMedicine() + " Barcod: " + prescribe.getBarcod() + " Medicine using type: " + prescribe.getDaily() + " - " + prescribe.getWeekly() + " - " + prescribe.getTime() + " - " + prescribe.getHungry() + " - " + " Doctor ınfo " + prescribe.getDoctor();

                connect.prescribe(prescribe.getName(), prescribe.getEmail(), prescribe.getMedicine(), prescribe.getBarcod(), prescribe.getDaily(), prescribe.getWeekly(), prescribe.getTime(), prescribe.getHungry(), prescribe.getDoctor());
                JOptionPane.showMessageDialog(rootPane, "Prescribe is done dear doctor");
                list1.addElement(prescribeInfo);

                Object[] add = {name, email, medicine, barcod, daily, weekly, time, hungry, doctor};
                table.addRow(add);

            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "This patient does not exist.Check informations or tell patient to sign up application first");
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

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jPopupMenu3 = new javax.swing.JPopupMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        AboutPatientInfoDialog = new javax.swing.JDialog();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel31 = new javax.swing.JLabel();
        aboutPrescribeDialog = new javax.swing.JDialog();
        jPanel5 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        doctorsPage = new javax.swing.JTabbedPane();
        searchPatientPanel = new javax.swing.JPanel();
        labelId = new javax.swing.JLabel();
        labelPatientsInfos = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        prescribesTable = new javax.swing.JTable();
        labelName = new javax.swing.JLabel();
        txtPatientEmail = new javax.swing.JTextField();
        labelEmail = new javax.swing.JLabel();
        txtPatientName = new javax.swing.JTextField();
        btnDelete = new javax.swing.JButton();
        labelMedicine = new javax.swing.JLabel();
        txtMedicine = new javax.swing.JTextField();
        txtPrescription = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtPatientId = new javax.swing.JTextField();
        labelSearch = new javax.swing.JLabel();
        btnShowMyPatient = new javax.swing.JButton();
        labelMyId = new javax.swing.JLabel();
        labelDoctorId = new javax.swing.JLabel();
        txtReset = new javax.swing.JButton();
        labelPresDoctorsId = new javax.swing.JPanel();
        labelPresName = new javax.swing.JLabel();
        txtPrescribeName = new javax.swing.JTextField();
        labelPresMedicine = new javax.swing.JLabel();
        txtPrescribeEmail = new javax.swing.JTextField();
        labelPresDaily = new javax.swing.JLabel();
        dailyPotion = new javax.swing.JComboBox<>();
        labelPresWekklyPotion = new javax.swing.JLabel();
        weeklyPotion = new javax.swing.JComboBox<>();
        labelPresDrinkTime = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        prescribeInfoList = new javax.swing.JList<>();
        labelPresEmail = new javax.swing.JLabel();
        labelPresBarcod = new javax.swing.JLabel();
        timeToDrink = new javax.swing.JComboBox<>();
        txtBarcod = new javax.swing.JTextField();
        labelHungry = new javax.swing.JLabel();
        checkBoxHungry = new javax.swing.JCheckBox();
        prescribeBtn = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        medicinesList = new javax.swing.JList<>();
        txtPrescribeMedicine = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtDoctorId = new javax.swing.JTextField();
        btnResetAreas = new javax.swing.JButton();
        doctorsAccountPanel = new javax.swing.JPanel();
        labelAccName = new javax.swing.JLabel();
        labelAccEmail = new javax.swing.JLabel();
        labelAccPassword = new javax.swing.JLabel();
        labelAccAge = new javax.swing.JLabel();
        labelAccId = new javax.swing.JLabel();
        ıconWomanDoctorLabel = new javax.swing.JLabel();
        ıconMenDoctorLabel = new javax.swing.JLabel();
        labelAccEmailData = new javax.swing.JLabel();
        labelAccNameData = new javax.swing.JLabel();
        labelAccPassData = new javax.swing.JLabel();
        labelAccAgeData = new javax.swing.JLabel();
        labelAccIdData = new javax.swing.JLabel();
        btnExitAccount = new javax.swing.JButton();

        jMenuItem1.setText("Turn Light Off");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        jScrollPane3.setViewportView(jTextPane1);

        jMenuItem2.setText("Main Page");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jPopupMenu2.add(jMenuItem2);

        jMenuItem4.setText("How to use application");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jPopupMenu2.add(jMenuItem4);

        jMenuItem3.setText("Main Page");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jPopupMenu3.add(jMenuItem3);

        jMenuItem5.setText("How To Use This Page");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jPopupMenu3.add(jMenuItem5);

        AboutPatientInfoDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel6.setBackground(new java.awt.Color(0, 153, 153));

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("Dear doctor you can search your new patients old or new prescribe informations for better cure.\nYou can have dynamic search for old prescribes. İf your patients periode ofa disease is finished\nyou can delete old prescribes if you want. \n\nİf ypu want to see your own patients you should click show my patients and then enter button \non dynamic search area. if you click a patients informations you will see them very clear on page");
        jScrollPane5.setViewportView(jTextArea1);

        jLabel31.setText("INFO");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel31)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel31)
                .addGap(30, 30, 30)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout AboutPatientInfoDialogLayout = new javax.swing.GroupLayout(AboutPatientInfoDialog.getContentPane());
        AboutPatientInfoDialog.getContentPane().setLayout(AboutPatientInfoDialogLayout);
        AboutPatientInfoDialogLayout.setHorizontalGroup(
            AboutPatientInfoDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        AboutPatientInfoDialogLayout.setVerticalGroup(
            AboutPatientInfoDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel5.setBackground(new java.awt.Color(0, 153, 153));

        jLabel32.setText("INFO");

        jTextArea2.setEditable(false);
        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jTextArea2.setText("Dear doctor for choose a medicine from list first you should enter next to (Medicine Name) patients sign of a patient \nproblem. It is for choosing medicine correctly. Prescribe imformations will be shown in list. ");
        jScrollPane6.setViewportView(jTextArea2);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel32)
                .addGap(35, 35, 35)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout aboutPrescribeDialogLayout = new javax.swing.GroupLayout(aboutPrescribeDialog.getContentPane());
        aboutPrescribeDialog.getContentPane().setLayout(aboutPrescribeDialogLayout);
        aboutPrescribeDialogLayout.setHorizontalGroup(
            aboutPrescribeDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        aboutPrescribeDialogLayout.setVerticalGroup(
            aboutPrescribeDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        searchPatientPanel.setBackground(new java.awt.Color(0, 153, 153));
        searchPatientPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchPatientPanelMouseClicked(evt);
            }
        });

        labelId.setText("My ID ");

        labelPatientsInfos.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        labelPatientsInfos.setText(" PATİENTS INFOS");

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        prescribesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Patient name", "Patient Email", "Medicine", "Barcod", "Daily", "Weekly", "Time", "Hungry", "DoctorId"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        prescribesTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                prescribesTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(prescribesTable);

        labelName.setText("Patient Name");

        labelEmail.setText("Patient Email");

        btnDelete.setText("Delete Patient");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        labelMedicine.setText("Medicine");

        jLabel15.setText("Medicine Using Type");

        labelSearch.setText("Search İnformation Dynamically");

        btnShowMyPatient.setText("ShowMyPatient");
        btnShowMyPatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowMyPatientActionPerformed(evt);
            }
        });

        labelMyId.setText("MY ID");

        labelDoctorId.setText("jLabel30");

        txtReset.setText("Reset");
        txtReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout searchPatientPanelLayout = new javax.swing.GroupLayout(searchPatientPanel);
        searchPatientPanel.setLayout(searchPatientPanelLayout);
        searchPatientPanelLayout.setHorizontalGroup(
            searchPatientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPatientPanelLayout.createSequentialGroup()
                .addGroup(searchPatientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(searchPatientPanelLayout.createSequentialGroup()
                        .addGroup(searchPatientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(searchPatientPanelLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(searchPatientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(searchPatientPanelLayout.createSequentialGroup()
                                        .addGroup(searchPatientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, searchPatientPanelLayout.createSequentialGroup()
                                                .addComponent(labelId)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(txtPatientId, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, searchPatientPanelLayout.createSequentialGroup()
                                                .addComponent(labelEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(txtPatientEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(40, 40, 40)
                                        .addGroup(searchPatientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(labelName)
                                            .addComponent(labelMedicine)))))
                            .addGroup(searchPatientPanelLayout.createSequentialGroup()
                                .addGap(252, 252, 252)
                                .addComponent(labelPatientsInfos)))
                        .addGap(31, 31, 31)
                        .addGroup(searchPatientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPatientName, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMedicine, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(searchPatientPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(searchPatientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(searchPatientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 683, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(searchPatientPanelLayout.createSequentialGroup()
                                    .addComponent(txtPrescription, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(67, 67, 67)
                                    .addGroup(searchPatientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(labelSearch)
                                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(searchPatientPanelLayout.createSequentialGroup()
                                .addGroup(searchPatientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(searchPatientPanelLayout.createSequentialGroup()
                                        .addComponent(labelMyId)
                                        .addGap(92, 92, 92)
                                        .addComponent(labelDoctorId, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(searchPatientPanelLayout.createSequentialGroup()
                                        .addComponent(btnShowMyPatient)
                                        .addGap(101, 101, 101)
                                        .addComponent(btnDelete)))
                                .addGap(89, 89, 89)
                                .addComponent(txtReset, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(94, 94, 94)))))
                .addContainerGap(7, Short.MAX_VALUE))
        );
        searchPatientPanelLayout.setVerticalGroup(
            searchPatientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPatientPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelPatientsInfos, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(searchPatientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelId)
                    .addComponent(txtPatientId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelName)
                    .addComponent(txtPatientName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 17, Short.MAX_VALUE)
                .addGroup(searchPatientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelEmail)
                    .addComponent(txtPatientEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelMedicine)
                    .addComponent(txtMedicine, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(searchPatientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(labelSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(searchPatientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(searchPatientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelMyId)
                    .addComponent(labelDoctorId))
                .addGap(29, 29, 29)
                .addGroup(searchPatientPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnShowMyPatient)
                    .addComponent(btnDelete)
                    .addComponent(txtReset))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        doctorsPage.addTab("Search Patient Information", searchPatientPanel);

        labelPresDoctorsId.setBackground(new java.awt.Color(0, 153, 153));
        labelPresDoctorsId.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelPresDoctorsIdMouseClicked(evt);
            }
        });

        labelPresName.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        labelPresName.setText("Patient Name");

        txtPrescribeName.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N

        labelPresMedicine.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        labelPresMedicine.setText("Medicine Name");

        txtPrescribeEmail.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N

        labelPresDaily.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        labelPresDaily.setText("Daily Potion");

        dailyPotion.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        dailyPotion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Once", "Twice", "Thrice" }));

        labelPresWekklyPotion.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        labelPresWekklyPotion.setText("Weekly Potion");

        weeklyPotion.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        weeklyPotion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Everyday", "1 on a week", "2 on a week" }));

        labelPresDrinkTime.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        labelPresDrinkTime.setText("Time For Drink");

        jScrollPane2.setViewportView(prescribeInfoList);

        labelPresEmail.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        labelPresEmail.setText("Email Name ");

        labelPresBarcod.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        labelPresBarcod.setText("Barcod Number");

        timeToDrink.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        timeToDrink.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Morning", "Noon", "Mid Afternoon", "Evening", "Night" }));

        txtBarcod.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N

        labelHungry.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        labelHungry.setText("Hungry");

        checkBoxHungry.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        checkBoxHungry.setText("Yes");

        prescribeBtn.setText("Prescribe");
        prescribeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prescribeBtnActionPerformed(evt);
            }
        });

        medicinesList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Nurofen", "Parol" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane4.setViewportView(medicinesList);

        txtPrescribeMedicine.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPrescribeMedicineKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel1.setText("DOCTOR ID");

        btnResetAreas.setText("Reset");
        btnResetAreas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetAreasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout labelPresDoctorsIdLayout = new javax.swing.GroupLayout(labelPresDoctorsId);
        labelPresDoctorsId.setLayout(labelPresDoctorsIdLayout);
        labelPresDoctorsIdLayout.setHorizontalGroup(
            labelPresDoctorsIdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(labelPresDoctorsIdLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(labelPresDoctorsIdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(labelPresDoctorsIdLayout.createSequentialGroup()
                        .addGroup(labelPresDoctorsIdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelPresDaily, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelPresDrinkTime, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(61, 61, 61)
                        .addGroup(labelPresDoctorsIdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(labelPresDoctorsIdLayout.createSequentialGroup()
                                .addGroup(labelPresDoctorsIdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(labelPresDoctorsIdLayout.createSequentialGroup()
                                        .addComponent(timeToDrink, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(23, 23, 23)
                                        .addComponent(labelHungry))
                                    .addComponent(txtPrescribeName)
                                    .addComponent(txtPrescribeEmail)
                                    .addComponent(txtBarcod, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                                    .addComponent(txtDoctorId))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, labelPresDoctorsIdLayout.createSequentialGroup()
                                .addComponent(dailyPotion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelPresWekklyPotion)
                                .addGap(36, 36, 36)))
                        .addGroup(labelPresDoctorsIdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(weeklyPotion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(checkBoxHungry))
                        .addGap(110, 110, 110))
                    .addGroup(labelPresDoctorsIdLayout.createSequentialGroup()
                        .addGroup(labelPresDoctorsIdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelPresBarcod)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 636, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelPresName, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelPresEmail)
                            .addGroup(labelPresDoctorsIdLayout.createSequentialGroup()
                                .addComponent(labelPresMedicine)
                                .addGap(61, 61, 61)
                                .addComponent(txtPrescribeMedicine, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(54, Short.MAX_VALUE))
                    .addGroup(labelPresDoctorsIdLayout.createSequentialGroup()
                        .addGroup(labelPresDoctorsIdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnResetAreas)
                            .addComponent(prescribeBtn))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 526, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        labelPresDoctorsIdLayout.setVerticalGroup(
            labelPresDoctorsIdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(labelPresDoctorsIdLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(labelPresDoctorsIdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelPresName)
                    .addComponent(txtPrescribeName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(labelPresDoctorsIdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelPresEmail)
                    .addComponent(txtPrescribeEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(labelPresDoctorsIdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelPresMedicine)
                    .addComponent(txtPrescribeMedicine, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(labelPresDoctorsIdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelPresBarcod)
                    .addComponent(txtBarcod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(labelPresDoctorsIdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelPresDaily)
                    .addComponent(dailyPotion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(weeklyPotion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelPresWekklyPotion))
                .addGap(23, 23, 23)
                .addGroup(labelPresDoctorsIdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelPresDrinkTime)
                    .addComponent(timeToDrink, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelHungry)
                    .addComponent(checkBoxHungry))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(labelPresDoctorsIdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtDoctorId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(labelPresDoctorsIdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(labelPresDoctorsIdLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(prescribeBtn)
                        .addGap(21, 21, 21)
                        .addComponent(btnResetAreas))
                    .addGroup(labelPresDoctorsIdLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        doctorsPage.addTab("Prescribe", labelPresDoctorsId);

        doctorsAccountPanel.setBackground(new java.awt.Color(255, 255, 255));

        labelAccName.setText("NAME");

        labelAccEmail.setText("EMAIL");

        labelAccPassword.setText("PASSWORD");

        labelAccAge.setText("AGE");

        labelAccId.setText("ID");

        ıconWomanDoctorLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/doctor.png"))); // NOI18N

        ıconMenDoctorLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/doctor-2.png"))); // NOI18N

        labelAccEmailData.setText("jLabel25");

        labelAccNameData.setText("jLabel26");

        labelAccPassData.setText("jLabel27");

        labelAccAgeData.setText("jLabel28");

        labelAccIdData.setText("jLabel29");

        btnExitAccount.setText("EXİT FROM ACCOUNT");
        btnExitAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitAccountActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout doctorsAccountPanelLayout = new javax.swing.GroupLayout(doctorsAccountPanel);
        doctorsAccountPanel.setLayout(doctorsAccountPanelLayout);
        doctorsAccountPanelLayout.setHorizontalGroup(
            doctorsAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(doctorsAccountPanelLayout.createSequentialGroup()
                .addGroup(doctorsAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(doctorsAccountPanelLayout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addGroup(doctorsAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(doctorsAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(doctorsAccountPanelLayout.createSequentialGroup()
                                    .addGroup(doctorsAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(labelAccAge)
                                        .addComponent(labelAccId, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(labelAccPassword))
                                    .addGap(54, 54, 54)
                                    .addGroup(doctorsAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(doctorsAccountPanelLayout.createSequentialGroup()
                                            .addComponent(labelAccPassData, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
                                            .addGap(103, 103, 103))
                                        .addComponent(labelAccAgeData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(labelAccIdData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(doctorsAccountPanelLayout.createSequentialGroup()
                                    .addGroup(doctorsAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(labelAccName)
                                        .addComponent(labelAccEmail))
                                    .addGap(85, 85, 85)
                                    .addGroup(doctorsAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(labelAccEmailData, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(labelAccNameData, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(0, 0, Short.MAX_VALUE)))
                            .addGroup(doctorsAccountPanelLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(ıconWomanDoctorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(ıconMenDoctorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(btnExitAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        doctorsAccountPanelLayout.setVerticalGroup(
            doctorsAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(doctorsAccountPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(doctorsAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ıconWomanDoctorLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ıconMenDoctorLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(doctorsAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelAccName)
                    .addComponent(labelAccNameData))
                .addGap(65, 65, 65)
                .addGroup(doctorsAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelAccEmail)
                    .addComponent(labelAccEmailData))
                .addGap(57, 57, 57)
                .addGroup(doctorsAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelAccPassword)
                    .addComponent(labelAccPassData))
                .addGap(61, 61, 61)
                .addGroup(doctorsAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelAccAge)
                    .addComponent(labelAccAgeData))
                .addGap(47, 47, 47)
                .addGroup(doctorsAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelAccId)
                    .addComponent(labelAccIdData))
                .addGap(27, 27, 27)
                .addComponent(btnExitAccount))
        );

        doctorsPage.addTab("My Account", doctorsAccountPanel);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(doctorsPage)
                .addGap(16, 16, 16))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(doctorsPage)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
//Tıkladığım yerde popup menünün açılmasını sağlamak istedim.
    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        // TODO add your handling code here:
        jPopupMenu1.setVisible(true);
        jPopupMenu1.show(this, evt.getX(), evt.getY());
    }//GEN-LAST:event_jPanel1MouseClicked
//Kişinin göz rahatlığı için isterse background rengini siyah yapmasını sağladım.
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        if (jMenuItem1.getText().equals("Turn Light Off")) {
            jPanel1.setBackground(Color.black);
            jMenuItem1.setText("Turn Light On");
        } else if (jMenuItem1.getText().equals("Turn Light On")) {
            jPanel1.setBackground(Color.white);
            jMenuItem1.setText("Turn Light Off");
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed
//Delete tuşuna basınca doktor tablonun seçili satırındaki kendi hastasının reçete bilgilerini silebiliyor.
    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        DataConnection connect = new DataConnection();
        DefaultTableModel model = (DefaultTableModel) prescribesTable.getModel();

        int row = prescribesTable.getSelectedRow();

        if (row == -1) {
            if (model.getRowCount() == 0) {
                JOptionPane.showMessageDialog(rootPane, "This table is free now", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(rootPane, "No row is selected . Please select on", "Warning", JOptionPane.WARNING_MESSAGE);

            }
        } else {
            int showConfirmDialog = JOptionPane.showConfirmDialog(rootPane, "Are You Sure", " you sure to reset areas", JOptionPane.YES_NO_CANCEL_OPTION);
            if (showConfirmDialog == JOptionPane.YES_OPTION) {
                if (labelDoctorId.getText().equals(prescribesTable.getValueAt(row, 8))) {
                    String email = (String) model.getValueAt(row, 1);
                    connect.deletePrescribe(email, Integer.parseInt(labelDoctorId.getText()));
                    showInformations();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "YOU CAN NOT DELETE OTHER DOCTOR'S PATİENT");
                }
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed
//prescribe methodumu çağırdım.
    private void prescribeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prescribeBtnActionPerformed
        try {

            prescribes();
        } catch (SQLException ex) {
            Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_prescribeBtnActionPerformed
//listeye ekleme metodumu çağırdım ve her seferinde listeyi temizlemesini sağladım.
    private void txtPrescribeMedicineKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrescribeMedicineKeyReleased
        list2.removeAllElements();
        addList();
    }//GEN-LAST:event_txtPrescribeMedicineKeyReleased
//Tıkladığım yerde popup menünün açılmasını sağlamak istedim.
    private void searchPatientPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchPatientPanelMouseClicked
        // TODO add your handling code here:
        jPopupMenu2.setVisible(true);
        jPopupMenu2.show(this, evt.getX(), evt.getY());

    }//GEN-LAST:event_searchPatientPanelMouseClicked
//Main Page sayfasını açmak istedim.
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        MainPage main = new MainPage();
        main.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed
//Main Page sayfasını açmak istedim.
    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        MainPage main = new MainPage();
        main.setVisible(true);

    }//GEN-LAST:event_jMenuItem3ActionPerformed
//Tıkladığım yerde popup menünün açılmasını sağlamak istedim.
    private void labelPresDoctorsIdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelPresDoctorsIdMouseClicked
        jPopupMenu3.setVisible(true);
        jPopupMenu3.show(this, evt.getX(), evt.getY());

    }//GEN-LAST:event_labelPresDoctorsIdMouseClicked
    //Dinamik bir şekide arama yapmak için bu methodu oluşturdum.
    public void search(String text) {
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(table);
        prescribesTable.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(text));
    }
//search methodunu çağırdım. Dinamik arama gerçekleşmesini sağladım.
    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        // TODO add your handling code here:
        String search = txtSearch.getText();
        search(search);

    }//GEN-LAST:event_txtSearchKeyReleased
//Tablonun tıklanan satırının verilerini ilgili textfield kısımlarına aktardım.  
    private void prescribesTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_prescribesTableMouseClicked
        // TODO add your handling code here:
        txtSearch.setText("");
        int selectedRow = prescribesTable.getSelectedRow();
        txtPatientId.setText((String) table.getValueAt(selectedRow, 8));
        txtPatientName.setText((String) table.getValueAt(selectedRow, 0));
        txtPatientEmail.setText((String) table.getValueAt(selectedRow, 1));
        txtMedicine.setText((String) table.getValueAt(selectedRow, 2));
        txtPrescription.setText(table.getValueAt(selectedRow, 4) + " " + table.getValueAt(selectedRow, 5) + " " + table.getValueAt(selectedRow, 6) + " " + table.getValueAt(selectedRow, 7));
    }//GEN-LAST:event_prescribesTableMouseClicked
//Bu kısımda doktor kendi hastalarının bilgilerini görebilmesi için dinamik arama kısmına
    //id'sini aktardım.
    private void btnShowMyPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowMyPatientActionPerformed
        // TODO add your handling code here:

        txtSearch.setText(labelDoctorId.getText());

    }//GEN-LAST:event_btnShowMyPatientActionPerformed
//Enter sayfasını açıp bu sayfadan da çıkarak sistemden çıkış yapmasını sağladım.
    private void btnExitAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitAccountActionPerformed
        Enter enter = new Enter();
        enter.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnExitAccountActionPerformed
//Dialog kısmını açtım.
    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        AboutPatientInfoDialog.setLocationRelativeTo(null);
        AboutPatientInfoDialog.setVisible(true);
        AboutPatientInfoDialog.setSize(600, 400);
    }//GEN-LAST:event_jMenuItem4ActionPerformed
//Dialog kısmını açtım.
    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        aboutPrescribeDialog.setLocationRelativeTo(null);
        aboutPrescribeDialog.setVisible(true);
        aboutPrescribeDialog.setSize(600, 400);
    }//GEN-LAST:event_jMenuItem5ActionPerformed
//Gözüken verileri belirli kısımlardan sildim.
    private void btnResetAreasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetAreasActionPerformed
        // TODO add your handling code here:
        int showConfirmDialog = JOptionPane.showConfirmDialog(rootPane, "Are You Sure", " you sure to reset areas", JOptionPane.YES_NO_CANCEL_OPTION);
        if (showConfirmDialog == JOptionPane.YES_OPTION) {

            txtPrescribeName.setText("");
            txtPrescribeEmail.setText("");
            txtPrescribeMedicine.setText("");
            txtBarcod.setText("");
            checkBoxHungry.setSelected(false);
            list1.clear();
            list2.clear();
        }
    }//GEN-LAST:event_btnResetAreasActionPerformed
//Gözüken verileri belirli kısımlardan sildim.

    private void txtResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtResetActionPerformed
        // TODO add your handling code here:
        txtPatientId.setText("");
        txtPatientEmail.setText("");
        txtPatientName.setText("");
        txtMedicine.setText("");
        txtPrescription.setText("");
        txtSearch.setText("");
    }//GEN-LAST:event_txtResetActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog AboutPatientInfoDialog;
    private javax.swing.JDialog aboutPrescribeDialog;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExitAccount;
    private javax.swing.JButton btnResetAreas;
    private javax.swing.JButton btnShowMyPatient;
    private javax.swing.JCheckBox checkBoxHungry;
    private javax.swing.JComboBox<String> dailyPotion;
    private javax.swing.JPanel doctorsAccountPanel;
    private javax.swing.JTabbedPane doctorsPage;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JPopupMenu jPopupMenu3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JLabel labelAccAge;
    private javax.swing.JLabel labelAccAgeData;
    private javax.swing.JLabel labelAccEmail;
    private javax.swing.JLabel labelAccEmailData;
    private javax.swing.JLabel labelAccId;
    private javax.swing.JLabel labelAccIdData;
    private javax.swing.JLabel labelAccName;
    private javax.swing.JLabel labelAccNameData;
    private javax.swing.JLabel labelAccPassData;
    private javax.swing.JLabel labelAccPassword;
    private javax.swing.JLabel labelDoctorId;
    private javax.swing.JLabel labelEmail;
    private javax.swing.JLabel labelHungry;
    private javax.swing.JLabel labelId;
    private javax.swing.JLabel labelMedicine;
    private javax.swing.JLabel labelMyId;
    private javax.swing.JLabel labelName;
    private javax.swing.JLabel labelPatientsInfos;
    private javax.swing.JLabel labelPresBarcod;
    private javax.swing.JLabel labelPresDaily;
    private javax.swing.JPanel labelPresDoctorsId;
    private javax.swing.JLabel labelPresDrinkTime;
    private javax.swing.JLabel labelPresEmail;
    private javax.swing.JLabel labelPresMedicine;
    private javax.swing.JLabel labelPresName;
    private javax.swing.JLabel labelPresWekklyPotion;
    private javax.swing.JLabel labelSearch;
    private javax.swing.JList<String> medicinesList;
    private javax.swing.JButton prescribeBtn;
    private javax.swing.JList<String> prescribeInfoList;
    private javax.swing.JTable prescribesTable;
    private javax.swing.JPanel searchPatientPanel;
    private javax.swing.JComboBox<String> timeToDrink;
    private javax.swing.JTextField txtBarcod;
    private javax.swing.JTextField txtDoctorId;
    private javax.swing.JTextField txtMedicine;
    private javax.swing.JTextField txtPatientEmail;
    private javax.swing.JTextField txtPatientId;
    private javax.swing.JTextField txtPatientName;
    private javax.swing.JTextField txtPrescribeEmail;
    private javax.swing.JTextField txtPrescribeMedicine;
    private javax.swing.JTextField txtPrescribeName;
    private javax.swing.JTextField txtPrescription;
    private javax.swing.JButton txtReset;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JComboBox<String> weeklyPotion;
    private javax.swing.JLabel ıconMenDoctorLabel;
    private javax.swing.JLabel ıconWomanDoctorLabel;
    // End of variables declaration//GEN-END:variables
}
