package controller;

import exception.InputValidationFailedException;
import model.Destination;
import model.Status;
import model.User;
import model.VacationPackage;
import service.DestinationService;
import service.UserService;
import service.VacationPackageService;
import view.UserMainPage;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserController {
    private final UserService userService;
    private UserMainPage userMainPage;
    private final VacationPackageService vacationPackageService;
    private final DestinationService destinationService;
    private JTable vacationsTable;
    private JTable myVacationsTable;
    private String actualUsername;
    List<VacationPackage> availableVacations = new ArrayList<>();

    public UserController(String actualUser) {
        userMainPage = new UserMainPage();
        userService = new UserService();
        vacationPackageService = new VacationPackageService();
        destinationService = new DestinationService();
        userMainPage.addComboBox(destinationService.getAll());
        actualUsername = actualUser;
        initializeListeners();
    }

    public void initializeListeners() {
        userMainPage.getExitButton().addActionListener(e->{
            userMainPage.setVisible(false);
        });

        userMainPage.getAvailableVacationsButton().addActionListener(e->{
            List<VacationPackage> availableVacations = vacationPackageService.getAll()
                    .stream()
                    .filter(v->v.getStatus() == Status.NOT_BOOKED || v.getStatus() == Status.IN_PROGRESS)
                    .collect(Collectors.toList());
            vacationsTable = userMainPage.createVacationsTable(availableVacations);
            userMainPage.addTable(vacationsTable);
        });

        userMainPage.getBookedVacationsButton().addActionListener(e->{
            myVacationsTable = userMainPage.createVacationsTable(
                    new ArrayList<>(userService.getUserByUsername(actualUsername).getBookedVacations())
            );
            userMainPage.addTable2(myVacationsTable);
        });

        userMainPage.getBookButton().addActionListener(e->{
            try {
                 bookVacation();
                 availableVacations = vacationPackageService.getAll()
                        .stream()
                        .filter(v -> v.getStatus() == Status.NOT_BOOKED || v.getStatus() == Status.IN_PROGRESS)
                        .collect(Collectors.toList());
                vacationsTable = userMainPage.createVacationsTable(availableVacations);
                userMainPage.addTable(vacationsTable);
                myVacationsTable = userMainPage.createVacationsTable( new ArrayList<>(userService.getUserByUsername(actualUsername).getBookedVacations()));
                userMainPage.addTable2(myVacationsTable);
            } catch (InputValidationFailedException ex) {
                userMainPage.displayErrorMessage(ex);
            }
        });

        userMainPage.getFilterButton().addActionListener(e->{
            try {
                availableVacations = filterVacations();
                vacationsTable = userMainPage.createVacationsTable(availableVacations);
                userMainPage.addTable(vacationsTable);
            } catch (InputValidationFailedException ex) {
                userMainPage.displayErrorMessage(ex);
            }
        });
    }

    public List<VacationPackage> filterVacations() {
        List<VacationPackage> filteredVacations = new ArrayList<>();
        filteredVacations = vacationPackageService.getAll();

        filteredVacations = filteredVacations
                .stream()
                .filter(v -> v.getStatus() == Status.NOT_BOOKED || v.getStatus() == Status.IN_PROGRESS)
                .collect(Collectors.toList());

        if (userMainPage.getMinPriceTF().getText().compareTo("") != 0 && userMainPage.getMaxPriceTF().getText().compareTo("") != 0) {
            try {
                float priceMin = Float.parseFloat(userMainPage.getMinPriceTF().getText());
                float priceMax = Float.parseFloat(userMainPage.getMaxPriceTF().getText());
                if(priceMin < 0 || priceMax < 0)
                    throw new InputValidationFailedException("Price cannot be negative!");
                filteredVacations = filteredVacations
                        .stream()
                        .filter(v -> v.getPrice() >= priceMin && v.getPrice() <= priceMax)
                        .collect(Collectors.toList());
            } catch (NumberFormatException ex) {
                throw new InputValidationFailedException("Price input is not a number!");
            }
        }

        filteredVacations = filteredVacations
                .stream()
                .filter(v -> v.getDestination().getName().compareTo(userMainPage.getDestinationsComboBox().getSelectedItem().toString())==0)
                .collect(Collectors.toList());

        if (userMainPage.getStartDateTF().getText().compareTo("") != 0 && userMainPage.getEndDateTF().getText().compareTo("") != 0) {
            try {
                LocalDate start = getDate(userMainPage.getStartDateTF().getText());
                LocalDate end = getDate(userMainPage.getEndDateTF().getText());
                filteredVacations = filteredVacations
                        .stream()
                        .filter(v -> v.getStartDate().isAfter(start.minusDays(1)) && v.getEndDate().isBefore(end.plusDays(1)))
                        .collect(Collectors.toList());
            } catch (DateTimeParseException e) {
                throw  new InputValidationFailedException("Date is not of correct format!");
            }
        }

        return filteredVacations;
    }

    public void bookVacation() {
        if(vacationsTable.getSelectedRows().length != 0) {
            int selectedRow = vacationsTable.getSelectedRows()[0];
            String vacationName = vacationsTable.getValueAt(selectedRow, 0).toString();
            VacationPackage vacationPackage = vacationPackageService.getPackageByName(vacationName);
            User user = userService.getUserByUsername(actualUsername);

            for(VacationPackage vp : user.getBookedVacations()) {
                if (vp.getName().compareTo(vacationPackage.getName()) == 0)
                    throw new InputValidationFailedException("This vacation is already booked by you!");
            }

            user.getBookedVacations().add(vacationPackage);
            vacationPackage.getUsers().add(user);

            vacationPackage.setAvailablePlaces(vacationPackage.getAvailablePlaces() - 1);

            if (vacationPackage.getAvailablePlaces() == 0) {
                vacationPackage.setStatus(Status.BOOKED);
            } else if (vacationPackage.getAvailablePlaces() < vacationPackage.getNbOfPlaces()) {
                vacationPackage.setStatus(Status.IN_PROGRESS);
            }
            vacationPackageService.updateVacationPackage(vacationPackage.getName(), vacationPackage);
            userService.addBookedVacation(user, vacationPackage);
        }
        else
            throw new InputValidationFailedException("No vacation selected to be booked. Please choose one!");
    }

    public LocalDate getDate(String str) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(str, formatter);
        return date;
    }
}
