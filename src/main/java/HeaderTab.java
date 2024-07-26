import burp.api.montoya.MontoyaApi;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 *
 * Original @author lor
 * Modified by Theron Hawley
 */
public class HeaderTab extends javax.swing.JPanel {
	
	//vars decs
    private static final String SETTINGS_KEY = "BetterHeader.settings.";
    JTextArea hardCodedText;
    JTextField headerNameText;
    JTextField headerValuePrefixText;
    JRadioButton jRadioButton1;
    JRadioButton regExpRadio;
    JRadioButton hardCodedRadio;
    JTextField regExpText;
    JCheckBox addbox;
    JCheckBox replacebox;
    JCheckBox onOffRegexbox;
    JTextField onOffRegexText;
    JLabel finalResultLabel;

    /**
     * Creates new form BurpTab
     */
    public HeaderTab(MontoyaApi api, String tab) {
        //setting up the settings if they don't already exist
        if (api.persistence().preferences().getBoolean(SETTINGS_KEY+"init"+tab) == null){
            SetDefaults(api, tab);
        }
        initComponents(api, tab);
        updateFinalResultLabel(api, tab);
    }

    
    private void initComponents(MontoyaApi api, String tab) {

    	//Setting up swing components
        JLabel headerNameLabel = new JLabel();
        headerNameText = new JTextField();
        JLabel headerValuePrefixLabel = new JLabel();
        headerValuePrefixText = new JTextField();

        JLabel headerValueLabel = new JLabel();
        ButtonGroup buttonGroup1 = new ButtonGroup();
        jRadioButton1 = new JRadioButton();
        regExpRadio = new JRadioButton();
        regExpText = new JTextField();
        hardCodedRadio = new JRadioButton();
        hardCodedText = new JTextArea();
        JScrollPane jScrollPane1 = new JScrollPane();

        JLabel headerARLabel = new JLabel();
        addbox = new JCheckBox();
        replacebox = new JCheckBox();

        JLabel onOffRegexLabel = new JLabel();
        onOffRegexbox = new JCheckBox();
        onOffRegexText = new JTextField();

        JButton updatePreviewButton = new JButton();
        finalResultLabel = new JLabel();

        JPanel jPanel1 = new JPanel();
        FocusListener fc = new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                updateFinalResultLabel(api, tab);
            }
        };

        //setting up swing element values
        headerNameLabel.setText("Header name");

        headerNameText.setText(api.persistence().preferences().getString(SETTINGS_KEY+"HeaderName"+tab));
        headerNameText.addFocusListener(fc);

        headerValuePrefixLabel.setText("Header value (prefix)");

        headerValuePrefixText.setText(api.persistence().preferences().getString(SETTINGS_KEY+"HeaderPrefix"+tab));
        headerValuePrefixText.setToolTipText("Don't forget the space");
        headerValuePrefixText.addFocusListener(fc);

        headerValueLabel.setText("Header value");

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("Disable custom header");
        jRadioButton1.addActionListener(evt -> updateFinalResultLabel(api, tab));

        buttonGroup1.add(regExpRadio);
        regExpRadio.setText("Regular Expression");
        regExpRadio.addActionListener(evt -> updateFinalResultLabel(api, tab));

        regExpText.setText(api.persistence().preferences().getString(SETTINGS_KEY+"RegexText"+tab));
        regExpText.addFocusListener(fc);

        buttonGroup1.add(hardCodedRadio);
        hardCodedRadio.setText("Hard-Coded Value");
        hardCodedRadio.addActionListener(evt -> updateFinalResultLabel(api, tab));

        hardCodedText.setColumns(20);
        hardCodedText.setRows(5);
        hardCodedText.setText(api.persistence().preferences().getString(SETTINGS_KEY+"HardcodedText"+tab));
        jScrollPane1.setViewportView(hardCodedText);
        hardCodedText.addFocusListener(fc);

        headerARLabel.setText("Add and/or replace the current header value.");

        addbox.setText("Add header");
        addbox.setSelected( api.persistence().preferences().getBoolean(SETTINGS_KEY+"IsAdding"+tab));
        addbox.addActionListener(evt -> updateFinalResultLabel(api, tab));

        replacebox.setText("Replace header");
        replacebox.setSelected( api.persistence().preferences().getBoolean(SETTINGS_KEY+"IsReplacing"+tab));
        replacebox.addActionListener(evt -> updateFinalResultLabel(api, tab));

        onOffRegexLabel.setText("If checked, the header will only be added/replaced if the regex finds a match in the request");

        onOffRegexbox.setText("Regex");
        onOffRegexbox.setSelected( api.persistence().preferences().getBoolean(SETTINGS_KEY+"IsOnOffRegex"+tab));
        onOffRegexbox.addActionListener(evt -> updateFinalResultLabel(api, tab));

        onOffRegexText.setText(api.persistence().preferences().getString(SETTINGS_KEY+"OnOffRegexText"+tab));
        onOffRegexText.addFocusListener(fc);

        updatePreviewButton.setText("Update Header");
        updatePreviewButton.addActionListener(evt -> updateFinalResultLabel(api, tab));

        finalResultLabel.setText("The new header will look like this");
        finalResultLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);


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



        //setting up layout
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(headerValueLabel)
                                        .addComponent(headerARLabel)
                                        .addComponent(onOffRegexLabel)
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
                                                                        .addComponent(jRadioButton1)
                                                                        .addComponent(onOffRegexbox))
                                                                .addGap(18, 18, 18)))
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(finalResultLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 1432, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(replacebox)
                                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(regExpText, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(onOffRegexText, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                                .addGap(18, 18, 18)
                                .addComponent(headerARLabel)
                                .addGap(9, 9, 9)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(addbox)
                                        .addComponent(replacebox))
                                .addGap(18, 18, 18)
                                .addComponent(onOffRegexLabel)
                                .addGap(9, 9, 9)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(onOffRegexbox)
                                        .addComponent(onOffRegexText, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(21, 21, 21)

                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(updatePreviewButton)
                                        .addComponent(finalResultLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(45, Short.MAX_VALUE))
        );
    }


    // Updating settings and setting the text to show the header
    void updateFinalResultLabel(MontoyaApi api, String tab) {

        //saving the current settings to persistent values
        api.persistence().preferences().setBoolean(SETTINGS_KEY+"IsRegex"+tab, regExpRadio.isSelected());
        api.persistence().preferences().setBoolean(SETTINGS_KEY+"IsHardcoded"+tab, hardCodedRadio.isSelected());
        api.persistence().preferences().setString(SETTINGS_KEY+"RegexText"+tab, regExpText.getText());
        api.persistence().preferences().setString(SETTINGS_KEY+"HardcodedText"+tab,hardCodedText.getText());
        api.persistence().preferences().setString(HeaderTab.SETTINGS_KEY+"HeaderName"+tab, headerNameText.getText());
        api.persistence().preferences().setString(HeaderTab.SETTINGS_KEY+"HeaderPrefix"+tab, headerValuePrefixText.getText());
        api.persistence().preferences().setBoolean(SETTINGS_KEY+"IsAdding"+tab, addbox.isSelected());
        api.persistence().preferences().setBoolean(SETTINGS_KEY+"IsReplacing"+tab, replacebox.isSelected());
        api.persistence().preferences().setBoolean(SETTINGS_KEY+"IsOnOffRegex"+tab, onOffRegexbox.isSelected());
        api.persistence().preferences().setString(SETTINGS_KEY+"OnOffRegexText"+tab, onOffRegexText.getText());

        //logging settings
        //api.logging().logToOutput("init="+api.persistence().preferences().getBoolean(SETTINGS_KEY+"init"+tab).toString());
        //api.logging().logToOutput("IsRegex="+api.persistence().preferences().getBoolean(SETTINGS_KEY+"IsRegex"+tab).toString());
        //api.logging().logToOutput("IsHardcoded="+api.persistence().preferences().getBoolean(SETTINGS_KEY+"IsHardcoded"+tab).toString());
        //api.logging().logToOutput("Regextext="+api.persistence().preferences().getString(SETTINGS_KEY+"RegexText"+tab));
        //api.logging().logToOutput("Hardcodedtext="+api.persistence().preferences().getString(SETTINGS_KEY+"HardcodedText"+tab));
        //api.logging().logToOutput("headername="+api.persistence().preferences().getString(BurpTab.SETTINGS_KEY+"HeaderName"+tab));
        //api.logging().logToOutput("headerprefix="+api.persistence().preferences().getString(BurpTab.SETTINGS_KEY+"HeaderPrefix"+tab));
        //api.logging().logToOutput("IsAdding="+api.persistence().preferences().getBoolean(SETTINGS_KEY+"IsAdding"+tab).toString());
        //api.logging().logToOutput("IsReplacing="+api.persistence().preferences().getBoolean(SETTINGS_KEY+"IsReplacing"+tab).toString());

        //setting final text by checking if its regex or hardcoded
        String finalText = getFinalText();

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

    private String getFinalText() {
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
        return finalText;
    }

    private void SetDefaults (MontoyaApi api, String tab){
        api.persistence().preferences().setBoolean(SETTINGS_KEY+"init"+tab, true);
        api.persistence().preferences().setString(SETTINGS_KEY+"HeaderName"+tab, "Authorization");
        api.persistence().preferences().setString(SETTINGS_KEY+"HeaderPrefix"+tab, "Bearer ");
        api.persistence().preferences().setBoolean(SETTINGS_KEY+"IsHardcoded"+tab, false);
        api.persistence().preferences().setBoolean(SETTINGS_KEY+"IsRegex"+tab, false);
        api.persistence().preferences().setString(SETTINGS_KEY+"HardcodedText"+tab, "<insert static JWT token here>");
        api.persistence().preferences().setString(SETTINGS_KEY+"RegexText"+tab, "access_token\":\"(.*?)\"");
        api.persistence().preferences().setBoolean(SETTINGS_KEY+"IsAdding"+tab, true);
        api.persistence().preferences().setBoolean(SETTINGS_KEY+"IsReplacing"+tab, true);
        api.persistence().preferences().setBoolean(SETTINGS_KEY+"IsOnOffRegex"+tab, false);
        api.persistence().preferences().setString(SETTINGS_KEY+"OnOffRegexText"+tab, "Admin");
    }

}