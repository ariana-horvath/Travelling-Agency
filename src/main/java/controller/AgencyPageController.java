package controller;

import exception.InputValidationFailedException;
import model.Destination;
import model.Status;
import model.VacationPackage;
import service.DestinationService;
import service.VacationPackageService;
import view.AgencyMainPage;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AgencyPageController {
    private AgencyMainPage agencyMainPage;
    private final DestinationService destinationService;
    private final VacationPackageService vacationPackageService;
    private JTable vacationsTable;

    public AgencyPageController() {
        destinationService = new DestinationService();
        vacationPackageService = new VacationPackageService();
        agencyMainPage = new AgencyMainPage();
        agencyMainPage.addComboBox(destinationService.getAll());
        initializeListeners();
    }

    public void initializeListeners() {
        agencyMainPage.getExitButton().addActionListener(e->{
            agencyMainPage.setVisible(false);
        });

        agencyMainPage.getAddDestinationButton().addActionListener(e->{
            try {
                addDestination(agencyMainPage.getNameDestTextField().getText(), agencyMainPage.getDescriptionTextArea().getText());
                agencyMainPage.addComboBox(destinationService.getAll());
            } catch (InputValidationFailedException ex) {
                agencyMainPage.displayErrorMessage(ex);
            }
        });

        agencyMainPage.getDeleteDestinationButton().addActionListener(e-> {
            try{
                deleteDestination(agencyMainPage.getNameDestTextField().getText());
                agencyMainPage.addComboBox(destinationService.getAll());
                vacationsTable = agencyMainPage.createVacationsTable(vacationPackageService.getAll());
                agencyMainPage.addTable(vacationsTable);
            } catch (InputValidationFailedException ex) {
                agencyMainPage.displayErrorMessage(ex);
            }
        });

        agencyMainPage.getAddPackageButton().addActionListener(e->{
            try{
                addVacationPackage(agencyMainPage.getNameTextField().getText(),
                                    agencyMainPage.getDestinationsComboBox().getSelectedItem().toString(),
                                    agencyMainPage.getPriceTextField().getText(),
                                    agencyMainPage.getExtraDetailsTextArea().getText(),
                                    agencyMainPage.getStartTextField().getText(),
                                    agencyMainPage.getEndTextField().getText(),
                                    agencyMainPage.getNbPlacesTextField().getText());
                vacationsTable = agencyMainPage.createVacationsTable(vacationPackageService.getAll());
                agencyMainPage.addTable(vacationsTable);
            } catch (InputValidationFailedException ex) {
                agencyMainPage.displayErrorMessage(ex);
            }
        });

        agencyMainPage.getViewPackagesButton().addActionListener(e->{
            vacationsTable = agencyMainPage.createVacationsTable(vacationPackageService.getAll());
            agencyMainPage.addTable(vacationsTable);
        });

        agencyMainPage.getDeletePackageButton().addActionListener(e->{
            deletePackage();
        });

        agencyMainPage.getEditPackageButton().addActionListener(e->{
            try {
                updatePackage();
                vacationsTable = agencyMainPage.createVacationsTable(vacationPackageService.getAll());
                agencyMainPage.addTable(vacationsTable);
            } catch (InputValidationFailedException ex) {
                agencyMainPage.displayErrorMessage(ex);
            }
        });
    }

    public void deletePackage() {
        int[] selectedRows = vacationsTable.getSelectedRows();
        //VALIDARE DACA E 0
        for(int i : selectedRows) {
            String packageName = vacationsTable.getValueAt(i, 0).toString();
            vacationPackageService.deletePackageByName(packageName);
        }
        vacationsTable = agencyMainPage.createVacationsTable(vacationPackageService.getAll());
        agencyMainPage.addTable(vacationsTable);
    }

    public void createDestination(String name, String description) {
        Destination dest = new Destination(name, description);
        destinationService.createDestination(dest);
    }

    public void addDestination(String name, String description) {
        if(name.compareTo("") == 0)
            throw new InputValidationFailedException("Name field cannot be empty!");

        Destination destination = destinationService.getDestinationByName(name);

        if(destination == null) {
            createDestination(name, description);
            agencyMainPage.addComboBox(destinationService.getAll());
            agencyMainPage.displayInformationMessage("Destination successfully added.");
        }
        else
            throw new InputValidationFailedException("Destination name already existent!");
    }

    public void deleteDestination(String name) {
        if(name.compareTo("") == 0)
            throw new InputValidationFailedException("Name field cannot be empty!");

        Destination destination = destinationService.getDestinationByName(name);

        if(destination != null) {
            destinationService.deleteDestinationByName(name);
            agencyMainPage.displayInformationMessage("Destination " + name + " successfully deleted.");
        }
        else
            throw new InputValidationFailedException("Destination not existent!");
    }

    public void createVacationPackage(String name, Float price, String details, int nbOfPlaces, Status status,
                                      LocalDate startDate, LocalDate endDate, Destination destination, int availablePlaces) {
        VacationPackage vacationPackage = new VacationPackage(name, price, details, nbOfPlaces, status, startDate, endDate, destination, availablePlaces);
        vacationPackageService.createVacationPackage(vacationPackage);
    }

    public void updateVacationPackage(String oldName, String name, Float price, String details, int nbOfPlaces, Status status,
                                      LocalDate startDate, LocalDate endDate, Destination destination, int availablePlaces) {
        VacationPackage vacationPackage = new VacationPackage(name, price, details, nbOfPlaces, status, startDate, endDate, destination, availablePlaces);
        vacationPackageService.updateVacationPackage(oldName, vacationPackage);
    }

    public void addVacationPackage(String name, String destination, String price, String details, String startDate, String endDate, String nbPlaces) {

        if(name.compareTo("") == 0)
            throw new InputValidationFailedException("Name field cannot be empty!");

        if(price.compareTo("") == 0)
            throw new InputValidationFailedException("Price field cannot be empty!");

        if(nbPlaces.compareTo("") == 0)
            throw new InputValidationFailedException("Nb. of places field cannot be empty!");

        if(destination.compareTo("") == 0)
            throw new InputValidationFailedException("Destination field cannot be empty!");

        VacationPackage vacationPackage = vacationPackageService.getPackageByName(agencyMainPage.getNameTextField().getText());

        if (vacationPackage == null) {
            Destination dest = destinationService.getDestinationByName(destination);
            if (dest != null) {
                try {
                    if (getDate(startDate).isBefore(LocalDate.now()) || getDate(endDate).isBefore(LocalDate.now()))
                        throw new InputValidationFailedException("Date cannot be in the past!");
                    else
                        if (getDate(endDate).isBefore(getDate(startDate)))
                            throw new InputValidationFailedException("End date cannot be before start date!");

                    if (Float.parseFloat(price) < 0)
                        throw new InputValidationFailedException("Price cannot be negative!");

                    if (Integer.parseInt(nbPlaces) < 0)
                        throw new InputValidationFailedException("Number of places cannot be negative!");

                    createVacationPackage(name, Float.parseFloat(price), details, Integer.parseInt(nbPlaces),
                            Status.NOT_BOOKED, getDate(startDate), getDate(endDate), dest, Integer.parseInt(nbPlaces));
                    agencyMainPage.displayInformationMessage("Vacation Package successfully added");
                } catch (NumberFormatException ex) {
                    throw new InputValidationFailedException("Input is not a number!");
                } catch (DateTimeParseException exception) {
                    throw new InputValidationFailedException("Date is not of valid format!");
                }
            }
            else
                throw new InputValidationFailedException("Destination selected not existent! Please add it.");
        }
        else
            throw new InputValidationFailedException("Vacation package already existent!");
    }

    public void updatePackage() {

        int selectedRow = vacationsTable.getSelectedRows()[0];
        String oldName = vacationPackageService.getAll().get(selectedRow).getName();
        int oldNbPlaces = vacationPackageService.getAll().get(selectedRow).getNbOfPlaces();

        String name = vacationsTable.getValueAt(selectedRow, 0).toString();
        String destination = vacationsTable.getValueAt(selectedRow, 1).toString();
        String price = vacationsTable.getValueAt(selectedRow, 2).toString();
        String startDate = vacationsTable.getValueAt(selectedRow, 3).toString();
        String endDate = vacationsTable.getValueAt(selectedRow, 4).toString();
        String nbPlaces = vacationsTable.getValueAt(selectedRow, 5).toString();
        String availablePlaces = vacationsTable.getValueAt(selectedRow, 6).toString();
        String status = vacationsTable.getValueAt(selectedRow, 7).toString();
        String details = vacationsTable.getValueAt(selectedRow, 8).toString();

        if (name.compareTo("") == 0)
            throw new InputValidationFailedException("Name field cannot be empty!");

        if (price.compareTo("") == 0)
            throw new InputValidationFailedException("Price field cannot be empty!");

        if (nbPlaces.compareTo("") == 0)
            throw new InputValidationFailedException("Nb. of places field cannot be empty!");

        if (destination.compareTo("") == 0)
            throw new InputValidationFailedException("Destination field cannot be empty!");

        if (oldName.compareTo(name) != 0) {
            VacationPackage vacationPackage = vacationPackageService.getPackageByName(name);
            if (vacationPackage != null) {
                throw new InputValidationFailedException("Vacation package name already existent!");
            }
        }

        Destination dest = destinationService.getDestinationByName(destination);
        if (dest != null) {
            Status statusEnums = Status.NOT_BOOKED;
            if (status.compareTo("BOOKED") == 0)
                statusEnums = Status.BOOKED;
            else if (status.compareTo("IN_PROGRESS") == 0)
                statusEnums = Status.IN_PROGRESS;

            try {
                if(getDate(startDate).isAfter(getDate(endDate)))
                    throw new InputValidationFailedException("End date cannot be before start date!");

                if(Integer.parseInt(nbPlaces) < 0)
                    throw new InputValidationFailedException("Nb of places cannot be negative!");

                if(Float.parseFloat(price) < 0)
                    throw new InputValidationFailedException("Price cannot be negative!");

                if (oldNbPlaces == Integer.parseInt(nbPlaces)) {
                    updateVacationPackage(oldName, name, Float.parseFloat(price), details, Integer.parseInt(nbPlaces),
                            statusEnums, getDate(startDate), getDate(endDate), dest, Integer.parseInt(availablePlaces));
                } else {
                    if (Integer.parseInt(nbPlaces) < (oldNbPlaces - Integer.parseInt(availablePlaces))) {
                        throw new InputValidationFailedException("People already booked this vacation, cannot put less places than the booked ones!");
                    }
                    int newAvailablePlaces = Integer.parseInt(availablePlaces) + Integer.parseInt(nbPlaces) - oldNbPlaces;
                    if (newAvailablePlaces == 0)
                        statusEnums = Status.BOOKED;
                    else if (newAvailablePlaces == Integer.parseInt(nbPlaces))
                        statusEnums = Status.NOT_BOOKED;
                    else
                        statusEnums = Status.IN_PROGRESS;

                    updateVacationPackage(oldName, name, Float.parseFloat(price), details, Integer.parseInt(nbPlaces),
                            statusEnums, getDate(startDate), getDate(endDate), dest, newAvailablePlaces);
                }
            } catch (NumberFormatException ex) {
                throw new InputValidationFailedException("Input is not a number!");
            } catch (DateTimeParseException e) {
                throw new InputValidationFailedException("Date is not of valid format!");
            }
        } else
            throw new InputValidationFailedException("Destination selected not existent! Please add it.");
    }

    public LocalDate getDate(String str) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(str, formatter);
        return date;
    }
}
