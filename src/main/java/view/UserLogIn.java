package view;

import javax.swing.*;
import java.awt.*;

public class UserLogIn extends AppFrame {

    private JTextField usernameTextField;
    private JPasswordField passwordTextField;
    private JButton registerClient;
    private JButton logInClient;
    private JButton exitButton;

    public UserLogIn() {
        this.setTitle("Client Login/Register");
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

        JLabel usernameLabel = new JLabel("Username: ");
        usernameLabel.setBounds(20,170,70,30);
        usernameLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
        usernameLabel.setForeground(new Color(151, 85, 13));
        panel.add(usernameLabel);

        usernameTextField = new JTextField();
        usernameTextField.setBounds(100, 170, 280, 30);
        usernameTextField.setBackground(new Color(245, 230, 156));
        usernameTextField.setFont(new Font("Calibri", Font.PLAIN, 14));
        usernameTextField.setForeground(new Color(151, 85, 13));
        panel.add(usernameTextField);

        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setBounds(20,220,70,30);
        passwordLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
        passwordLabel.setForeground(new Color(151, 85, 13));
        panel.add(passwordLabel);

        passwordTextField = new JPasswordField();
        passwordTextField.setBounds(100, 220, 280, 30);
        passwordTextField.setBackground(new Color(245, 230, 156));
        panel.add(passwordTextField);

        registerClient = new JButton("REGISTER");
        registerClient.setBounds(100, 310, 100, 30);
        registerClient.setFont(new Font("Calibri", Font.PLAIN, 14));
        registerClient.setForeground(new Color(151, 85, 13));
        panel.add(registerClient);

        logInClient = new JButton("LOGIN");
        logInClient.setBounds(280, 310, 100, 30);
        logInClient.setFont(new Font("Calibri", Font.PLAIN, 14));
        logInClient.setForeground(new Color(151, 85, 13));
        panel.add(logInClient);

        exitButton = new JButton("EXIT");
        exitButton.setBounds(140, 400, 210, 30);
        exitButton.setFont(new Font("Calibri", Font.PLAIN, 14));
        exitButton.setForeground(new Color(151, 85, 13));
        panel.add(exitButton);
    }

    public JTextField getUsernameTextField() {
        return usernameTextField;
    }

    public void setUsernameTextField(JTextField usernameTextField) {
        this.usernameTextField = usernameTextField;
    }

    public JPasswordField getPasswordTextField() {
        return passwordTextField;
    }

    public void setPasswordTextField(JPasswordField passwordTextField) {
        this.passwordTextField = passwordTextField;
    }

    public JButton getRegisterClient() {
        return registerClient;
    }

    public void setRegisterClient(JButton registerClient) {
        this.registerClient = registerClient;
    }

    public JButton getLogInClient() {
        return logInClient;
    }

    public void setLogInClient(JButton logInClient) {
        this.logInClient = logInClient;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public void setExitButton(JButton exitButton) {
        this.exitButton = exitButton;
    }
}
