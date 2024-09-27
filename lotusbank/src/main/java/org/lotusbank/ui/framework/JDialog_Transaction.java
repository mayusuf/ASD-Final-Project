package org.lotusbank.ui.framework;

import javax.swing.*;
import java.awt.*;

public abstract class JDialog_Transaction extends JDialog {
    protected BaseUI parentframe;
    protected String name;
    protected JTextField JTextField_AMT = new JTextField();
    JLabel JLabel1 = new JLabel();
    JLabel JLabel2 = new JLabel();
    JTextField JTextField_NAME = new JTextField();
    JButton JButton_OK = new JButton();
    JButton JButton_Cancel = new JButton();


    public JDialog_Transaction(BaseUI parent, String aname, String title) {
        super(parent);
        parentframe = parent;
        name = aname;

        setTitle(title);
        setModal(true);
        getContentPane().setLayout(null);
        setSize(290, 230);
        setVisible(false);
        JLabel1.setText("Name");
        getContentPane().add(JLabel1);
        JLabel1.setForeground(Color.black);
        JLabel1.setBounds(12, 12, 58, 24);
        JLabel2.setText("Amount");
        getContentPane().add(JLabel2);
        JLabel2.setForeground(Color.black);
        JLabel2.setBounds(12, 36, 58, 24);
        JTextField_NAME.setEditable(false);
        getContentPane().add(JTextField_NAME);
        JTextField_NAME.setBounds(84, 12, 156, 20);
        getContentPane().add(JTextField_AMT);
        JTextField_AMT.setBounds(84, 36, 156, 20);
        JButton_OK.setText("OK");
        JButton_OK.setActionCommand("OK");
        getContentPane().add(JButton_OK);
        JButton_OK.setBounds(48, 74, 84, 24);
        JButton_Cancel.setText("Cancel");
        JButton_Cancel.setActionCommand("Cancel");
        getContentPane().add(JButton_Cancel);
        JButton_Cancel.setBounds(156, 74, 84, 24);

        JTextField_NAME.setText(name);

        SymAction lSymAction = new SymAction();
        JButton_OK.addActionListener(lSymAction);
        JButton_Cancel.addActionListener(lSymAction);
    }

    protected abstract void JButtonOK_actionPerformed(java.awt.event.ActionEvent event);

    void JButtonCancel_actionPerformed(java.awt.event.ActionEvent event) {
        dispose();
    }

    class SymAction implements java.awt.event.ActionListener {
        public void actionPerformed(java.awt.event.ActionEvent event) {
            Object object = event.getSource();
            if (object == JButton_OK)
                JButtonOK_actionPerformed(event);
            else if (object == JButton_Cancel)
                JButtonCancel_actionPerformed(event);
        }
    }
}
