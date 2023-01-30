package medicinefollowupproject;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Polygon;
import javax.swing.JMenuItem;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author merve
 */
public class MainPage extends javax.swing.JFrame {

    /**
     * Creates new form MainPage
     */
    public MainPage() {
        initComponents();
        jPanel5.setBackground(Color.white);

        this.setLocationRelativeTo(null);
        Shape shape = new Shape();
        shape.setBounds(0, 0, 700, 100);
        shape.setBackground(Color.white);
        jPanel5.add(shape);

    }
//Graphics konusundan yararlanarak frame'de belirli bir görüntü oluşturmak istedim. 

    public class Shape extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
            g.setColor(Color.white);

            g.setColor(Color.black);
            g.setColor(new Color(0, 153, 153));

            g.drawOval(80, 20, 50, 50);
            g.drawOval(200, 20, 50, 50);
            g.drawRect(280, 20, 140, 80);

            g.drawString("ALWAYS HERE FOR YOU", 280, 65);
            g.drawOval(580, 20, 50, 50);
            g.drawOval(460, 20, 50, 50);

            g.setColor(Color.black);
            g.drawString("ALWAYS HERE FOR YOU", 280, 65);
            g.fillOval(20, 20, 50, 50);

            g.fillOval(140, 20, 50, 50);

            g.fillOval(520, 20, 50, 50);
            g.fillOval(640, 20, 50, 50);

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
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jPanel5 = new javax.swing.JPanel();
        labelWelcome = new javax.swing.JLabel();
        loginIcon = new javax.swing.JLabel();
        infoIcon = new javax.swing.JLabel();
        medicineIcon = new javax.swing.JLabel();
        btnTurnLogInPage = new javax.swing.JButton();
        btnTurnAboutUs = new javax.swing.JButton();
        btnTurnCheckMedicine = new javax.swing.JButton();
        mainMenuBar = new javax.swing.JMenuBar();
        helpMenu = new javax.swing.JMenu();
        menuInteractWithUs = new javax.swing.JMenuItem();

        jMenuItem1.setText("Turn Light Off");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 153, 153));
        setBounds(new java.awt.Rectangle(0, 0, 500, 500));
        setMinimumSize(new java.awt.Dimension(700, 700));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel5MouseClicked(evt);
            }
        });

        labelWelcome.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        labelWelcome.setForeground(new java.awt.Color(0, 153, 153));
        labelWelcome.setText("WELCOME OUR APPLİCATİON DEAR USER");

        loginIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/user-2.png"))); // NOI18N

        infoIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/info.png"))); // NOI18N

        medicineIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/capsules.png"))); // NOI18N

        btnTurnLogInPage.setText("LOGİN PAGE");
        btnTurnLogInPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTurnLogInPageActionPerformed(evt);
            }
        });

        btnTurnAboutUs.setText("ABOUT US");
        btnTurnAboutUs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTurnAboutUsActionPerformed(evt);
            }
        });

        btnTurnCheckMedicine.setText("CHECK MEDİCİNE TAKE");
        btnTurnCheckMedicine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTurnCheckMedicineActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(infoIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(loginIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(medicineIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(69, 69, 69)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnTurnLogInPage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTurnAboutUs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTurnCheckMedicine, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(137, Short.MAX_VALUE)
                .addComponent(labelWelcome, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(135, 135, 135)
                .addComponent(labelWelcome)
                .addGap(27, 27, 27)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(loginIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTurnLogInPage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(43, 43, 43)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(infoIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTurnAboutUs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(medicineIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTurnCheckMedicine, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(21, 21, 21))
        );

        helpMenu.setText("Help");

        menuInteractWithUs.setText("Interact with us");
        menuInteractWithUs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuInteractWithUsActionPerformed(evt);
            }
        });
        helpMenu.add(menuInteractWithUs);

        mainMenuBar.add(helpMenu);

        setJMenuBar(mainMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
//Background siyah ise isterse beyaz renkte kullanmasını, beyaz ise isterse göz rahatlığı için siyah renkte kullanmasını istedim.
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:

        if (jMenuItem1.getText().equals("Turn Light Off")) {
            jPanel5.setBackground(Color.black);

            jMenuItem1.setText("Turn Light On");
        } else if (jMenuItem1.getText().equals("Turn Light On")) {
            jPanel5.setBackground(Color.white);
            jMenuItem1.setText("Turn Light Off");

        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed
//popup menünün tıkladığım yerde görünmesini sağladım.
    private void jPanel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseClicked
        // TODO add your handling code here:
        jPopupMenu1.setVisible(true);
        jPopupMenu1.show(this, evt.getX(), evt.getY());
    }//GEN-LAST:event_jPanel5MouseClicked
//Enter sayfasına dönmesini sağladım.
    private void btnTurnLogInPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTurnLogInPageActionPerformed
        // TODO add your handling code here:
        Enter enter = new Enter();
        enter.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnTurnLogInPageActionPerformed
//AboutUs sayfasına dönmesini sağladım.
    private void btnTurnAboutUsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTurnAboutUsActionPerformed
        // TODO add your handling code here:
        AboutUs about = new AboutUs();
        about.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnTurnAboutUsActionPerformed
//CheckHowYouShouldUseMedicine sayfasına dönmesini sağladım ve bu sayfayı kapattım.
    private void btnTurnCheckMedicineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTurnCheckMedicineActionPerformed
        // TODO add your handling code here:
        CheckHowYouShouldUseMedicine check = new CheckHowYouShouldUseMedicine();
        check.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnTurnCheckMedicineActionPerformed
//menüye tıklanınca iletişim numarası bilgisinin gözükmesini sağladım.
    private void menuInteractWithUsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuInteractWithUsActionPerformed
        // TODO add your handling code here:

        JOptionPane.showMessageDialog(rootPane, "Our company number : 444 55 66", "HELP", JOptionPane.INFORMATION_MESSAGE);

    }//GEN-LAST:event_menuInteractWithUsActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new MainPage().setVisible(true);
//            }
//        });
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTurnAboutUs;
    private javax.swing.JButton btnTurnCheckMedicine;
    private javax.swing.JButton btnTurnLogInPage;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JLabel infoIcon;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JLabel labelWelcome;
    private javax.swing.JLabel loginIcon;
    private javax.swing.JMenuBar mainMenuBar;
    private javax.swing.JLabel medicineIcon;
    private javax.swing.JMenuItem menuInteractWithUs;
    // End of variables declaration//GEN-END:variables
}
