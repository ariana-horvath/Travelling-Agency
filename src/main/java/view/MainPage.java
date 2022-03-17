package view;

import javax.swing.*;
import java.awt.*;

public class MainPage extends AppFrame {
    private JButton travelAgencyButton;
    private JButton clientButton;
    private JButton exitButton;

    public MainPage() {
        this.setTitle("Your Perfect Vacation");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        this.setLocationRelativeTo(null);
        initializeForm(panel);
        this.setContentPane(panel);
        this.setVisible(true);
        this.getContentPane().setBackground(new Color(250, 206, 97));
    }

    private void initializeForm(JPanel panel) {
        JLabel titleLabel = new JLabel("YOUR PERFECT VACATION");
        titleLabel.setFont(new Font("Calibri", Font.PLAIN, 28));
        titleLabel.setForeground(new Color(151, 85, 13));
        titleLabel.setBounds(95, 80, 400, 30);
        panel.add(titleLabel);

        JLabel titleLabel2 = new JLabel("TRAVELLING AGENCY");
        titleLabel2.setFont(new Font("Calibri", Font.PLAIN, 18));
        titleLabel2.setForeground(new Color(151, 85, 13));
        titleLabel2.setBounds(160, 110, 400, 30);
        panel.add(titleLabel2);

        travelAgencyButton = new JButton("I'M THE AGENCY");
        travelAgencyButton.setBounds(140, 200, 210, 30);
        travelAgencyButton.setFont(new Font("Calibri", Font.PLAIN, 14));
        travelAgencyButton.setForeground(new Color(151, 85, 13));
        panel.add(travelAgencyButton);

        clientButton = new JButton("I'M A VACAY SEEKER");
        clientButton.setBounds(140, 260, 210, 30);
        clientButton.setFont(new Font("Calibri", Font.PLAIN, 14));
        clientButton.setForeground(new Color(151, 85, 13));
        //clientButton.setBackground(new Color(175, 126, 6));
        panel.add(clientButton);

        exitButton = new JButton("EXIT");
        exitButton.setBounds(140, 400, 210, 30);
        exitButton.setFont(new Font("Calibri", Font.PLAIN, 14));
        exitButton.setForeground(new Color(151, 85, 13));
        panel.add(exitButton);
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public JButton getTravelAgencyButton() {
        return travelAgencyButton;
    }

    public JButton getClientButton() {
        return clientButton;
    }
}
