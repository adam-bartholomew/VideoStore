/*
 * ManagementPane.java
 */

package videostore.gui;

import videostore.controller.ManagementUseCase;

/** The pane associated with the "Management" tab on the main card of the GUI
 *
 * @author Russell C. Bjork
 * 
 * MODIFIED BY: Adam Bartholomew and Steve Smith
 */
public class ManagementPane extends javax.swing.JPanel 
{    
    /** Creates new form ManagementPane 
     */
    ManagementPane() 
    {
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        passwordLabel = new javax.swing.JLabel();
        managerPasswordField = new javax.swing.JPasswordField();
        logInButton = new javax.swing.JButton();
        logOutButton = new javax.swing.JButton();

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        passwordLabel.setText("Manager Password:");

        managerPasswordField.setEditable(false);
        managerPasswordField.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                managerPasswordFieldCaretUpdate(evt);
            }
        });
        managerPasswordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                managerPasswordFieldActionPerformed(evt);
            }
        });

        logInButton.setText("Log In");
        logInButton.setEnabled(false);
        logInButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logInButtonActionPerformed(evt);
            }
        });

        logOutButton.setText("Log Out");
        logOutButton.setEnabled(false);
        logOutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOutButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(passwordLabel)
                .add(18, 18, 18)
                .add(managerPasswordField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 174, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(logInButton)
                .add(18, 18, 18)
                .add(logOutButton)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(passwordLabel)
                    .add(managerPasswordField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(logInButton)
                    .add(logOutButton))
                .addContainerGap(290, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    /** Occurs when a user enters a password and presses enter/return on the keyboard */
    private void managerPasswordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_managerPasswordFieldActionPerformed
        if(logInButton.isEnabled())
            logInButtonActionPerformed(evt);
    }//GEN-LAST:event_managerPasswordFieldActionPerformed

    /** Lets the system know that a manager is logged in */
    private void logInButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logInButtonActionPerformed
        char[] inputPassword = managerPasswordField.getPassword();
        try {
            ManagementUseCase.getInstance().startActivateManager(inputPassword);
            logOutButton.setEnabled(true);
            managerPasswordField.setText("");
            managerPasswordField.requestFocus();
            managerPasswordField.setEditable(false);
            logInButton.setEnabled(false);
            GUI.getInstance().showMessage("Manager logged in");
        }
        catch(IllegalArgumentException e) {
            GUI.getInstance().showMessage(e.getMessage());
        }
    }//GEN-LAST:event_logInButtonActionPerformed

    /** Resets the password field every time the panel is shown */
    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        managerPasswordField.setText("");
        managerPasswordField.requestFocus();
        if(ManagementUseCase.getInstance().checkManagerActivity() == true)
        {
            logOutButton.setEnabled(true);
            logInButton.setEnabled(false);
            managerPasswordField.setEditable(false);
        }
        else
        {
            logOutButton.setEnabled(false);
            managerPasswordField.setEditable(true);
        }
    }//GEN-LAST:event_formComponentShown

    /** Enable the log in button only when the text field has something in it */
    private void managerPasswordFieldCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_managerPasswordFieldCaretUpdate
        if(ManagementUseCase.getInstance().checkManagerActivity() == false)
            logInButton.setEnabled(managerPasswordField.getPassword().length != 0);
        else
            logInButton.setEnabled(false);
    }//GEN-LAST:event_managerPasswordFieldCaretUpdate

    /** Deactivate the manager */
    private void logOutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutButtonActionPerformed
        ManagementUseCase.getInstance().startDeactiveManager();
        logOutButton.setEnabled(false);
        logInButton.setEnabled(true);
        managerPasswordField.setEditable(true);
        GUI.getInstance().showMessage("Manager logged out");
    }//GEN-LAST:event_logOutButtonActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton logInButton;
    private javax.swing.JButton logOutButton;
    private javax.swing.JPasswordField managerPasswordField;
    private javax.swing.JLabel passwordLabel;
    // End of variables declaration//GEN-END:variables
    
}
