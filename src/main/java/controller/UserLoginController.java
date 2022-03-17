package controller;

import exception.InputValidationFailedException;
import model.User;
import service.UserService;
import view.UserLogIn;

import java.util.Arrays;

public class UserLoginController {
    private UserLogIn userLogInPage;
    private final UserService userService;

    public UserLoginController() {
        userService = new UserService();
        userLogInPage = new UserLogIn();
        initializeListeners();
    }

    public void initializeListeners() {
        userLogInPage.getExitButton().addActionListener(e->{
            userLogInPage.setVisible(false);
        });

        userLogInPage.getRegisterClient().addActionListener(e->{
            try {
                registerUser();
            } catch (InputValidationFailedException ex) {
                userLogInPage.displayErrorMessage(ex);
            }
        });

        userLogInPage.getLogInClient().addActionListener(e->{
            try {
                loginUser();
            } catch (InputValidationFailedException ex) {
                userLogInPage.displayErrorMessage(ex);
            }
        });
    }

    public void createUser(String username, String password) {
        User user = new User(username, password);
        userService.createUser(user);
    }

    public User getByUsername(String username) {
        return userService.getUserByUsername(username);
    }

    public void registerUser() {
        if(userLogInPage.getUsernameTextField().getText().compareTo("") == 0)
            throw new InputValidationFailedException("Username field cannot be empty!");

        if(String.valueOf(userLogInPage.getPasswordTextField().getPassword()).compareTo("") == 0)
            throw new InputValidationFailedException("Password field cannot be empty!");

        User user = getByUsername(userLogInPage.getUsernameTextField().getText());

        if(user == null) {
            createUser(userLogInPage.getUsernameTextField().getText(), new String(userLogInPage.getPasswordTextField().getPassword()));
            userLogInPage.displayInformationMessage("User successfully added.");
        }
        else
            throw new InputValidationFailedException("Username already taken! Please choose another one.");
    }

    public void loginUser() {
        if(userLogInPage.getUsernameTextField().getText().compareTo("") == 0)
            throw new InputValidationFailedException("Username field cannot be empty!");

        if (String.valueOf(userLogInPage.getPasswordTextField().getPassword()).compareTo("") == 0)
            throw new InputValidationFailedException("Password field cannot be empty");

        User user = getByUsername(userLogInPage.getUsernameTextField().getText());
        if(user != null) {
            if (Arrays.equals(user.getPassword().toCharArray(), userLogInPage.getPasswordTextField().getPassword())) {
                new UserController(userLogInPage.getUsernameTextField().getText());
            }
            else
                throw new InputValidationFailedException("Incorrect password");
        }
        else
            throw new InputValidationFailedException("User " + userLogInPage.getUsernameTextField().getText() + " not existent!");
    }
}
