package controller;

import view.MainPage;
import view.UserLogIn;

public class MainController {

    private MainPage mainPage;

    public MainController() {
        mainPage = new MainPage();
        initializeListeners();
    }

    public void initializeListeners() {
        mainPage.getExitButton().addActionListener(e->{
            System.exit(0);
        });

        mainPage.getClientButton().addActionListener(e->{
            new UserLoginController();
        });

        mainPage.getTravelAgencyButton().addActionListener(e->{
            new AgencyPageController();
        });
    }
}
