package view;

import javax.swing.*;
import java.awt.*;

public abstract class AppFrame extends JFrame{

    public void displayErrorMessage(Exception exception) {
        if (exception != null) {
            String message = exception.getMessage();
            UIManager UI = new UIManager();
            UI.put("OptionPane.background", new Color(250, 206, 97));
            UI.put("Panel.background", new Color(250, 206, 97));
            JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void displayInformationMessage(String message) {
        if (!message.isEmpty()) {
            UIManager UI = new UIManager();
            UI.put("OptionPane.background", new Color(250, 206, 97));
            UI.put("Panel.background", new Color(250, 206, 97));
            JOptionPane.showMessageDialog(this, message, "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
