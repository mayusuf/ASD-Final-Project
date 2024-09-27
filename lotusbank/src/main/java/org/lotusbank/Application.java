package org.lotusbank;

import org.lotusbank.ui.framework.Launcher;

import javax.swing.*;

public class Application {
    public static void main(String[] args) {

        System.out.println("Welcome to Lotus Bank!");
        try {
            initializeUI();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void initializeUI() throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        (new Launcher()).setVisible(true);
    }
}