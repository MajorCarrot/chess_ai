/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.gui;

import javax.swing.JOptionPane;

/**
 *
 * @author Adithya
 */
public class ColorChooser extends javax.swing.JFrame {

    /**
     * Creates new form ColorChooser
     */
    public ColorChooser() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jColorChooser1 = new javax.swing.JColorChooser();
        dc = new javax.swing.JButton();
        lc = new javax.swing.JButton();
        exit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        getContentPane().add(jColorChooser1);
        jColorChooser1.setBounds(30, 20, 601, 370);

        dc.setText("SET DARK COLOR");
        dc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dcActionPerformed(evt);
            }
        });
        getContentPane().add(dc);
        dc.setBounds(270, 420, 140, 60);

        lc.setText("SET LIGHT COLOR");
        lc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lcActionPerformed(evt);
            }
        });
        getContentPane().add(lc);
        lc.setBounds(40, 420, 140, 60);

        exit.setText("CLOSE");
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });
        getContentPane().add(exit);
        exit.setBounds(480, 420, 150, 60);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lcActionPerformed
        GamePage.oldL = GamePage.CUSTOM_L;
        GamePage.CUSTOM_L = jColorChooser1.getColor();
        GamePage.setColor();
    }//GEN-LAST:event_lcActionPerformed

    private void dcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dcActionPerformed
        GamePage.oldD = GamePage.CUSTOM_D;
        GamePage.CUSTOM_D = jColorChooser1.getColor();
        GamePage.setColor();
    }//GEN-LAST:event_dcActionPerformed

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        int n = JOptionPane.showConfirmDialog(null, "Do you want to save changes?");
        if (n == 0) {
            setVisible(false);
        } else if (n == 1) {
            GamePage.CUSTOM_D = GamePage.oldD;
            GamePage.CUSTOM_L = GamePage.oldL;
            GamePage.setColor();
            setVisible(false);
        }
    }//GEN-LAST:event_exitActionPerformed

    /**
     *
     * @param args
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(ColorChooser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ColorChooser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ColorChooser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ColorChooser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ColorChooser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton dc;
    private javax.swing.JButton exit;
    private javax.swing.JColorChooser jColorChooser1;
    public javax.swing.JButton lc;
    // End of variables declaration//GEN-END:variables
}