package service;

import exception.InputValidationFailedException;
import model.Destination;
import model.User;
import model.VacationPackage;
import repository.DestinationRepository;
import repository.VacationPackageRepository;

import java.util.List;

public class VacationPackageService {

    private final VacationPackageRepository vacationPackageRepository;

    public VacationPackageService() {
        vacationPackageRepository = new VacationPackageRepository();
    }

    public void createVacationPackage(VacationPackage vacationPackage) {
        if (vacationPackage.getName() != null && !vacationPackage.getName().isEmpty()) {
            vacationPackageRepository.insertVacationPackage(vacationPackage);
        } else throw new InputValidationFailedException("Name cannot be empty!");
    }

    public VacationPackage getPackageByName(String name) {
        return vacationPackageRepository.findPackageByName(name);
    }

    public List<VacationPackage> getAll () {return vacationPackageRepository.findAll();}

    public void deletePackageByName (String name) { vacationPackageRepository.deletePackageByName(name); }

    public void updateVacationPackage(String name, VacationPackage vacationPackage) {
        vacationPackageRepository.updatePackage(name, vacationPackage);
    }
}
