/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author lor
 */
public class BurpTab extends javax.swing.JPanel {

    /**
     * Creates new form BurpTab
     */
    public BurpTab() {
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        headerValueLabel = new javax.swing.JLabel();
        headerNameLabel = new javax.swing.JLabel();
        headerValuePrefixText = new javax.swing.JTextField();
        headerValuePrefixLabel = new javax.swing.JLabel();
        headerNameText = new javax.swing.JTextField();
        finalResultLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        hardCodedText = new javax.swing.JTextArea();
        updatePreviewButton = new javax.swing.JButton();
        regExpRadio = new javax.swing.JRadioButton();
        hardCodedRadio = new javax.swing.JRadioButton();
        regExpText = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();

        headerValueLabel.setText("Header value");

        headerNameLabel.setText("Header name");

        headerValuePrefixText.setText("Bearer ");
        headerValuePrefixText.setToolTipText("Don't forget the space");
        headerValuePrefixText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                headerValuePrefixTextActionPerformed(evt);
            }
        });

        headerValuePrefixLabel.setText("Header value (prefix)");

        headerNameText.setText("Authorization");
        headerNameText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                headerNameTextActionPerformed(evt);
            }
        });

        finalResultLabel.setText("The new header will look like this");
        finalResultLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        hardCodedText.setColumns(20);
        hardCodedText.setRows(5);
        jScrollPane1.setViewportView(hardCodedText);

        updatePreviewButton.setText("Update Preview");
        updatePreviewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatePreviewButtonActionPerformed(evt);
            }
        });

        buttonGroup1.add(regExpRadio);
        regExpRadio.setText("Regular Expression");
        regExpRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regExpRadioActionPerformed(evt);
            }
        });

        buttonGroup1.add(hardCodedRadio);
        hardCodedRadio.setText("Hard-Coded Value");

        regExpText.setText("jTextField1");
        regExpText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regExpTextActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("Disable custom header");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(headerValueLabel)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(headerValuePrefixLabel)
                                                        .addComponent(headerNameLabel))
                                                .addGap(44, 44, 44)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(headerNameText, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(headerValuePrefixText, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(199, 199, 199)
                                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(updatePreviewButton)
                                                                .addGap(44, 44, 44))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(hardCodedRadio)
                                                                        .addComponent(regExpRadio)
                                                                        .addComponent(jRadioButton1))
                                                                .addGap(18, 18, 18)))
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(finalResultLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(regExpText, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(headerNameLabel)
                                                        .addComponent(headerNameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(headerValuePrefixLabel)
                                                        .addComponent(headerValuePrefixText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addComponent(headerValueLabel))
                                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(regExpRadio)
                                        .addComponent(regExpText, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(hardCodedRadio))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(9, 9, 9)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(21, 21, 21)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(updatePreviewButton)
                                        .addComponent(finalResultLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(45, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void headerNameTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_headerNameTextActionPerformed
        updateFinalResultLabel();
    }//GEN-LAST:event_headerNameTextActionPerformed

    private void headerValuePrefixTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_headerValuePrefixTextActionPerformed
        updateFinalResultLabel();
    }//GEN-LAST:event_headerValuePrefixTextActionPerformed

    private void updatePreviewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatePreviewButtonActionPerformed
        updateFinalResultLabel();
    }//GEN-LAST:event_updatePreviewButtonActionPerformed

    private void regExpRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regExpRadioActionPerformed

    }//GEN-LAST:event_regExpRadioActionPerformed

    private void regExpTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regExpTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_regExpTextActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel finalResultLabel;
    private javax.swing.JRadioButton hardCodedRadio;
    javax.swing.JTextArea hardCodedText;
    private javax.swing.JLabel headerNameLabel;
    javax.swing.JTextField headerNameText;
    private javax.swing.JLabel headerValueLabel;
    private javax.swing.JLabel headerValuePrefixLabel;
    javax.swing.JTextField headerValuePrefixText;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton regExpRadio;
    private javax.swing.JTextField regExpText;
    private javax.swing.JButton updatePreviewButton;
    // End of variables declaration//GEN-END:variables

    void setHeaderName(String text) {
        headerNameText.setText(text);
    }

    String getHeaderName() {
        return headerNameText.getText();
    }

    void setHeaderValuePrefix(String text) {
        headerValuePrefixText.setText(text);
    }

    String getHeaderValuePrefix() {
        return headerValuePrefixText.getText();
    }

    public boolean useHardCoded() {
        return hardCodedRadio.isSelected();
    }

    public boolean useRegExp() {
        return regExpRadio.isSelected();
    }

    void setHardCodedText(String text) {
        hardCodedText.setText(text);
    }

    String getHardCodedText() {
        return hardCodedText.getText();
    }

    public String getRegExpText() {
        return regExpText.getText();
    }

    public void setRegExpText(String regExpText) {
        this.regExpText.setText(regExpText);
    }

    // custom code
    void updateFinalResultLabel() {
        String finalText = headerNameText.getText() + ": " + headerValuePrefixText.getText();
        if ( useHardCoded() ) finalText += getHardCodedText();
        else if ( useRegExp()) finalText += "[regular expression: " + getRegExpText() + " ]";
        else ;
        finalResultLabel.setText(finalText);

    }

}