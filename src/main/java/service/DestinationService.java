package service;

import exception.InputValidationFailedException;
import model.Destination;
import repository.DestinationRepository;

import java.util.List;

public class DestinationService {

    private final DestinationRepository destinationRepository;

    public DestinationService() {
        destinationRepository = new DestinationRepository();
    }

    public void createDestination(Destination destination) {
        if (destination.getName() != null && !destination.getName().isEmpty()) {
            destinationRepository.insertDestination(destination);
        } else throw new InputValidationFailedException("Destination name cannot be empty!");
    }

    public Destination getDestinationByName(String name) {
        return destinationRepository.findDestinationByName(name);
    }

    public void deleteDestinationByName(String name) {
        destinationRepository.deleteDestinationByName(name);
    }

    public List<Destination> getAll () {return destinationRepository.findAll();}
}
