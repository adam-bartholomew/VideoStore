/* AddTitleCard.java */

package videostore.gui;

import videostore.controller.ManagementUseCase;

/** This card represents the place to enter in the name of a new title
 *
 * @author Adam Bartholomew and Steve Smith
 */
public class AddTitleCard extends javax.swing.JPanel {

    /**
     * Creates new form AddTitleCard
     */
    public AddTitleCard() {
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

        jLabel1 = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        addGameButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        addMovieButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        creatorTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        yearTextField = new javax.swing.JTextField();

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jLabel1.setText("Name of Title:");

        nameTextField.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                nameTextFieldCaretUpdate(evt);
            }
        });

        addGameButton.setText("Add Game");
        addGameButton.setEnabled(false);
        addGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addGameButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        addMovieButton.setText("Add Movie");
        addMovieButton.setEnabled(false);
        addMovieButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addMovieButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("Creator:");

        jLabel3.setText("Year:");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(jLabel2)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(creatorTextField)
                        .add(18, 18, 18)
                        .add(jLabel3)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(yearTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 73, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(cancelButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(addMovieButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(addGameButton))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(0, 0, Short.MAX_VALUE)
                        .add(jLabel1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(nameTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 230, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(407, Short.MAX_VALUE))
        );

        layout.linkSize(new java.awt.Component[] {addGameButton, addMovieButton, cancelButton}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1)
                    .add(nameTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(creatorTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel3)
                    .add(yearTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(addMovieButton)
                    .add(addGameButton)
                    .add(cancelButton))
                .addContainerGap(133, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    /** Code that adds a game to the store database */
    private void addGameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addGameButtonActionPerformed
        String name = nameTextField.getText();
        String company = creatorTextField.getText();
        String year = yearTextField.getText();
        
        try {
            ManagementUseCase.getInstance().startAddGame(name, company, year);
            GUI.getInstance().showMessage("Game: " + name + " added");
            GUI.getInstance().goBack();
        }
        catch(IllegalArgumentException e) {
            GUI.getInstance().showMessage(e.getMessage());
            nameTextField.setText("");
            nameTextField.requestFocus();
        }
    }//GEN-LAST:event_addGameButtonActionPerformed

    /** Makes the GUI go back to the inventory pane */
    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        GUI.getInstance().goBack();
    }//GEN-LAST:event_cancelButtonActionPerformed

    /** Enables the add buttons only when something is in the text field */
    private void nameTextFieldCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_nameTextFieldCaretUpdate
        addMovieButton.setEnabled(nameTextField.getText().length() != 0);
        addGameButton.setEnabled(nameTextField.getText().length() != 0);
    }//GEN-LAST:event_nameTextFieldCaretUpdate

    /** Code that adds a movie to the store database */
    private void addMovieButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addMovieButtonActionPerformed
        String name = nameTextField.getText();
        String director = creatorTextField.getText();
        String year = yearTextField.getText();
        
        try {
            ManagementUseCase.getInstance().startAddMovie(name, director, year);
            GUI.getInstance().showMessage("Movie: " + name + " added");
            GUI.getInstance().goBack();
                }
        catch(IllegalArgumentException e) {
            GUI.getInstance().showMessage(e.getMessage());
            nameTextField.setText("");
            nameTextField.requestFocus();
        }
    }//GEN-LAST:event_addMovieButtonActionPerformed

    /** Makes the text field empty when pane shows */
    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        nameTextField.setText("");
        nameTextField.requestFocus();
        creatorTextField.setText("");
        creatorTextField.requestFocus();
        yearTextField.setText("");
        yearTextField.requestFocus();        
    }//GEN-LAST:event_formComponentShown

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addGameButton;
    private javax.swing.JButton addMovieButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextField creatorTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JTextField yearTextField;
    // End of variables declaration//GEN-END:variables
}