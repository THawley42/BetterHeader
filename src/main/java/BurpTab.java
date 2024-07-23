import burp.api.montoya.MontoyaApi;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 *
 * Original @author lor
 * Modified by Theron Hawley
 */
public class BurpTab extends javax.swing.JPanel {
	
	//vars decs
    private static final String SETTINGS_KEY = "BetterHeader.settings.";
    private javax.swing.JLabel finalResultLabel;
    private javax.swing.JRadioButton hardCodedRadio;
    javax.swing.JTextArea hardCodedText;
    javax.swing.JTextField headerNameText;
    javax.swing.JTextField headerValuePrefixText;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton regExpRadio;
    private javax.swing.JTextField regExpText;
    private javax.swing.JCheckBox addbox;
    private javax.swing.JCheckBox replacebox;
    /**
     * Creates new form BurpTab
     */
    public BurpTab(MontoyaApi api) {
        initComponents(api);
    }

    
    private void initComponents(MontoyaApi api) {

    	//Setting up swing components
        ButtonGroup buttonGroup1 = new ButtonGroup();
        JLabel headerValueLabel = new JLabel();
        JLabel headerNameLabel = new JLabel();
        headerValuePrefixText = new javax.swing.JTextField();
        JLabel headerValuePrefixLabel = new JLabel();
        headerNameText = new javax.swing.JTextField();
        finalResultLabel = new javax.swing.JLabel();
        JScrollPane jScrollPane1 = new JScrollPane();
        hardCodedText = new javax.swing.JTextArea();
        JButton updatePreviewButton = new JButton();
        regExpRadio = new javax.swing.JRadioButton();
        hardCodedRadio = new javax.swing.JRadioButton();
        regExpText = new javax.swing.JTextField();
        JPanel jPanel1 = new JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        addbox = new JCheckBox();
        replacebox = new JCheckBox();
        FocusListener fc = new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                updateFinalResultLabel(api);
            }
        };

        //setting up swing element values
        addbox.setText("Add header");
        addbox.setSelected( api.persistence().preferences().getBoolean(SETTINGS_KEY+"IsAdding"));
        addbox.addActionListener(evt -> updateFinalResultLabel(api));
        replacebox.setText("Replace header");
        replacebox.setSelected( api.persistence().preferences().getBoolean(SETTINGS_KEY+"IsReplacing"));
        replacebox.addActionListener(evt -> updateFinalResultLabel(api));

        headerValueLabel.setText("Header value");

        headerNameLabel.setText("Header name");

        headerValuePrefixText.setText(api.persistence().preferences().getString(SETTINGS_KEY+"HeaderPrefix"));
        headerValuePrefixText.setToolTipText("Don't forget the space");
        headerValuePrefixText.addFocusListener(fc);

        headerValuePrefixLabel.setText("Header value (prefix)");

        headerNameText.setText(api.persistence().preferences().getString(SETTINGS_KEY+"HeaderName"));
        headerNameText.addFocusListener(fc);

        finalResultLabel.setText("The new header will look like this");
        finalResultLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        hardCodedText.setColumns(20);
        hardCodedText.setRows(5);
        hardCodedText.setText(api.persistence().preferences().getString(SETTINGS_KEY+"HardcodedText"));
        jScrollPane1.setViewportView(hardCodedText);
        hardCodedText.addFocusListener(fc);

        updatePreviewButton.setText("Update Header");
        updatePreviewButton.addActionListener(evt -> updateFinalResultLabel(api));

        buttonGroup1.add(regExpRadio);
        regExpRadio.setText("Regular Expression");
        regExpRadio.addActionListener(evt -> updateFinalResultLabel(api));

        buttonGroup1.add(hardCodedRadio);
        hardCodedRadio.setText("Hard-Coded Value");
        hardCodedRadio.addActionListener(evt -> updateFinalResultLabel(api));

        regExpText.setText(api.persistence().preferences().getString(SETTINGS_KEY+"RegexText"));
        regExpText.addFocusListener(fc);

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
        jRadioButton1.addActionListener(evt -> updateFinalResultLabel(api));

        //setting up layout
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
                                                                        .addComponent(addbox)
                                                                        .addComponent(hardCodedRadio)
                                                                        .addComponent(regExpRadio)
                                                                        .addComponent(jRadioButton1))
                                                                .addGap(18, 18, 18)))
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(finalResultLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 1432, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(replacebox)
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
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(addbox)
                                        .addComponent(replacebox))
                                .addGap(21, 21, 21)

                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(updatePreviewButton)
                                        .addComponent(finalResultLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(45, Short.MAX_VALUE))
        );
    }


    // Updating settings and setting the text to show the header
    void updateFinalResultLabel(MontoyaApi api) {

        //saving the current settings to persistent values
        //api.persistence().preferences().setBoolean(SETTINGS_KEY+"init", true);
        api.persistence().preferences().setBoolean(SETTINGS_KEY+"IsRegex", regExpRadio.isSelected());
        api.persistence().preferences().setBoolean(SETTINGS_KEY+"IsHardcoded", hardCodedRadio.isSelected());
        api.persistence().preferences().setString(SETTINGS_KEY+"RegexText", regExpText.getText());
        api.persistence().preferences().setString(SETTINGS_KEY+"HardcodedText",hardCodedText.getText());
        api.persistence().preferences().setString(BurpTab.SETTINGS_KEY+"HeaderName", headerNameText.getText());
        api.persistence().preferences().setString(BurpTab.SETTINGS_KEY+"HeaderPrefix", headerValuePrefixText.getText());
        api.persistence().preferences().setBoolean(SETTINGS_KEY+"IsAdding", addbox.isSelected());
        api.persistence().preferences().setBoolean(SETTINGS_KEY+"IsReplacing", replacebox.isSelected());

        //logging settings
        //api.logging().logToOutput("init="+api.persistence().preferences().getBoolean(SETTINGS_KEY+"init").toString());
        //api.logging().logToOutput("IsRegex="+api.persistence().preferences().getBoolean(SETTINGS_KEY+"IsRegex").toString());
        //api.logging().logToOutput("IsHardcoded="+api.persistence().preferences().getBoolean(SETTINGS_KEY+"IsHardcoded").toString());
        //api.logging().logToOutput("Regextext="+api.persistence().preferences().getString(SETTINGS_KEY+"RegexText"));
        //api.logging().logToOutput("Hardcodedtext="+api.persistence().preferences().getString(SETTINGS_KEY+"HardcodedText"));
        //api.logging().logToOutput("headername="+api.persistence().preferences().getString(BurpTab.SETTINGS_KEY+"HeaderName"));
        //api.logging().logToOutput("headerprefix="+api.persistence().preferences().getString(BurpTab.SETTINGS_KEY+"HeaderPrefix"));
        //api.logging().logToOutput("IsAdding="+api.persistence().preferences().getBoolean(SETTINGS_KEY+"IsAdding").toString());
        //api.logging().logToOutput("IsReplacing="+api.persistence().preferences().getBoolean(SETTINGS_KEY+"IsReplacing").toString());

        //setting final text by checking if its regex or hardcoded
        String finalText = headerNameText.getText() + ": " + headerValuePrefixText.getText();
        if ( hardCodedRadio.isSelected() ) {
            if (hardCodedText.getText().length() < 30) {
                finalText += hardCodedText.getText();
            }else{
                finalText += hardCodedText.getText().substring(0,30)+"...";
            }
        }
        else if ( regExpRadio.isSelected()) {
            finalText += "[regular expression: " + regExpText.getText() + " ]";
        }
        else {
            finalText = "Extension currently disabled.";
        }

        //checking settings and displaying the behavior and header to the user
        if (addbox.isSelected() && replacebox.isSelected() && !jRadioButton1.isSelected()) {
            finalResultLabel.setText("The header \""+ finalText+ "\" will be added or replaced in all requests");
        }
        else if (addbox.isSelected() && !jRadioButton1.isSelected()){
            finalResultLabel.setText("The header \""+ finalText+ "\" will be added to requests that don't have the "+ headerNameText.getText() + " header.");
        }
        else if (replacebox.isSelected() && !jRadioButton1.isSelected()) {
            finalResultLabel.setText("The header \""+finalText+"\" will replace the "+ headerNameText.getText() + " header in any requests that have it.");
        }
        else finalResultLabel.setText("Extension currently disabled.");

    }

}