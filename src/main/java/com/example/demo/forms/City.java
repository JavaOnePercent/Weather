package com.example.demo.forms;

import javax.swing.*;
import java.awt.event.*;

public class City extends JFrame {
    private JPanel cityPanel;
    private JTextField textFieldCity;
    private JButton okButton;
    private JLabel labelTitle;
    private JButton backButton;
    private JButton buttonOK;
    private JButton buttonCancel;

    public City() {
        super("Настройки");
        this.setSize(540, 320);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        setContentPane(cityPanel);

        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cityPanel.setVisible(false);
                MainForm mainForm = new MainForm();
                mainForm.setCity(textFieldCity.getText());
                mainForm.setVisible(true);
                dispose();
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cityPanel.setVisible(false);
                MainForm mainForm = new MainForm();
                mainForm.setVisible(true);
                dispose();
            }
        });
    }
}
