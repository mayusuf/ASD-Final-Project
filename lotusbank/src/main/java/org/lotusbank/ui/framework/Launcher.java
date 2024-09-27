package org.lotusbank.ui.framework;

import org.lotusbank.ui.bank.BankForm;
import org.lotusbank.ui.ccard.CreditCardForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Launcher extends JFrame {
    //private BankFrm bankFrm;
    //private CardFrm cardFrm;
    private BaseUI uiForm;

    public Launcher() {
        setTitle("<<< Lotus Bank >>>");
        setLayout(new GridLayout(2,1,0,20));

        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel headingPanel = new JPanel();
        headingPanel.setSize(400,200);
        JLabel welcomeLabel = new JLabel("Welcome to Lotus Bank.");
        JLabel serviceOptionLabel = new JLabel("Please select the service you want.");

        headingPanel.add(welcomeLabel);
        headingPanel.add(serviceOptionLabel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,2,0,5));


        JButton bankButton = new JButton("Go to Bank Application");
        JButton cardButton = new JButton("Go to Credit Card Application");

        //buttonPanel.setBounds(150, 150, 400, 300);
        buttonPanel.add(bankButton);
        //bankButton.setBounds(200, 200, 200, 50);

        buttonPanel.add(cardButton);
        //cardButton.setBounds(200, 280, 200, 50);

        bankButton.addActionListener(e -> {
            if (uiForm == null || !uiForm.isVisible()) {
                uiForm = new BankForm(this);
                uiForm.setVisible(true);
//                cardButton.setEnabled(false); // Disable the other button
                this.setVisible(false); // Hide the main window

                uiForm.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        cardButton.setEnabled(true); // Re-enable the other button when the window is closed
                    }
                });
            }
        });

        cardButton.addActionListener(e -> {
            if (uiForm == null || !uiForm.isVisible()) {
                uiForm = new CreditCardForm(this);
                uiForm.setVisible(true);
//                bankButton.setEnabled(false); // Disable the other button
                this.setVisible(false); // Hide the main window

                uiForm.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        bankButton.setEnabled(true); // Re-enable the other button when the window is closed
                    }
                });
            }
        });

        buttonPanel.add(bankButton);
        buttonPanel.add(cardButton);

        //this.add(bankButton);
        // Terminate the application when the MainWindow is closed

        this.add(headingPanel, BorderLayout.NORTH);
        this.add(buttonPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }
}
