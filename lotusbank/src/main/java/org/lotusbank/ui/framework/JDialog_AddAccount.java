package org.lotusbank.ui.framework;


import org.lotusbank.common.pattern.factory.AccountFactory;

import javax.swing.*;

public abstract class JDialog_AddAccount extends JDialog {
    protected BaseUI parentframe;
    protected AccountFactory accountFactory;

    protected JRadioButton JRadioButton_Chk;
    protected JRadioButton JRadioButton_Sav;
    protected JRadioButton JRadioButton_Extra; // Additional radio button specific to JDialog_AddCCAccount
    protected JLabel JLabel1;
    protected JLabel JLabel2;
    protected JLabel JLabel3;
    protected JLabel JLabel4;
    protected JLabel JLabel5;
    protected JLabel JLabel6;
    protected JLabel JLabel7;
    protected JTextField JTextField_NAME;
    protected JTextField JTextField_CT;
    protected JTextField JTextField_ST;
    protected JTextField JTextField_STR;
    protected JTextField JTextField_ZIP;
    protected JButton JButton_OK;
    protected JButton JButton_Cancel;
    protected JLabel JLabel8;
    protected JTextField JTextField_ACNR;

    public JDialog_AddAccount(BaseUI parent) {
        super(parent);
        parentframe = parent;

        // Initialize common components
        JRadioButton_Chk = new JRadioButton();
        JRadioButton_Sav = new JRadioButton();
        JLabel1 = new JLabel();
        JLabel2 = new JLabel();
        JLabel3 = new JLabel();
        JLabel4 = new JLabel();
        JLabel5 = new JLabel();
        JLabel6 = new JLabel();
        JLabel7 = new JLabel();
        JTextField_NAME = new JTextField();
        JTextField_CT = new JTextField();
        JTextField_ST = new JTextField();
        JTextField_STR = new JTextField();
        JTextField_ZIP = new JTextField();
        JButton_OK = new JButton();
        JButton_Cancel = new JButton();

        JLabel8 = new JLabel();
        JTextField_ACNR = new JTextField();

        // Add common components to content pane
        getContentPane().setLayout(null);
        getContentPane().add(JRadioButton_Chk);
        getContentPane().add(JRadioButton_Sav);
        getContentPane().add(JButton_OK);
        getContentPane().add(JButton_Cancel);
        getContentPane().add(JTextField_ACNR);

        // Register common listeners
        SymMouse aSymMouse = new SymMouse();
        JRadioButton_Chk.addMouseListener(aSymMouse);
        JRadioButton_Sav.addMouseListener(aSymMouse);
        SymAction lSymAction = new SymAction();
        JButton_OK.addActionListener(lSymAction);
        JButton_Cancel.addActionListener(lSymAction);

        // Initialize common components
        initCommonComponents();
    }

    protected abstract void initCommonComponents();

    // Inner classes for listeners
    class SymMouse extends java.awt.event.MouseAdapter {
        public void mouseClicked(java.awt.event.MouseEvent event) {
            Object object = event.getSource();
            if (object == JRadioButton_Chk) {
                JRadioButtonChk_mouseClicked(event);
            } else if (object == JRadioButton_Sav) {
                JRadioButtonSav_mouseClicked(event);
            } else if (object == JRadioButton_Extra) { // Specific to JDialog_AddCCAccount
                JRadioButtonExtra_mouseClicked(event);
            }
        }
    }

    class SymAction implements java.awt.event.ActionListener {
        public void actionPerformed(java.awt.event.ActionEvent event) {
            Object object = event.getSource();
            if (object == JButton_OK)
                JButtonOK_actionPerformed(event);
            else if (object == JButton_Cancel)
                JButtonCalcel_actionPerformed(event);
        }
    }

    // Common methods
    protected void JRadioButtonChk_mouseClicked(java.awt.event.MouseEvent event) {
        // Implement in subclass if needed
    }

    protected void JRadioButtonSav_mouseClicked(java.awt.event.MouseEvent event) {
        // Implement in subclass if needed
    }

    protected void JRadioButtonExtra_mouseClicked(java.awt.event.MouseEvent event) {
        // Implement in subclass if needed
    }

    protected abstract void JButtonOK_actionPerformed(java.awt.event.ActionEvent event);

    protected void JButtonCalcel_actionPerformed(java.awt.event.ActionEvent event) {
        // Make this frame invisible if Cancel button is clicked
        dispose();
    }
}

