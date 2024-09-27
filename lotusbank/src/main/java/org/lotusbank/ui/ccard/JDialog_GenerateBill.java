package org.lotusbank.ui.ccard;
/*
		A basic implementation of the JDialog class.
*/

import org.lotusbank.creditcard.service.CreditReportServiceImpl;
import org.lotusbank.framework.service.ReportService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JDialog_GenerateBill extends javax.swing.JDialog {
    String billstring;

    JScrollPane JScrollPane1 = new JScrollPane();
    JTextArea JTextField1 = new JTextArea();
    JButton JButton_OK = new JButton();
    ReportService reportService;

    public JDialog_GenerateBill(Frame parent) {
        super(parent);
        reportService = new CreditReportServiceImpl();

        getContentPane().setLayout(null);
        setLocationRelativeTo(null);
        setVisible(false);

        getContentPane().add(JScrollPane1);
        JScrollPane1.setBounds(24, 24, 358, 240);
        JScrollPane1.getViewport().add(JTextField1);
        JTextField1.setBounds(0, 0, 355, 237);
        JButton_OK.setText("OK");
        JButton_OK.setActionCommand("OK");
        getContentPane().add(JButton_OK);
        JButton_OK.setBounds(156, 276, 96, 24);

        // generate the string for the monthly bill
        billstring = reportService.generateReport();
        JTextField1.setText(billstring);

        SymAction lSymAction = new SymAction();
        JButton_OK.addActionListener(lSymAction);
    }


    public JDialog_GenerateBill() {
        this(null);
    }

    void JButtonOK_actionPerformed(ActionEvent event) {
        dispose();

    }

    class SymAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            Object object = event.getSource();
            if (object == JButton_OK)
                JButtonOK_actionPerformed(event);
        }
    }
}
