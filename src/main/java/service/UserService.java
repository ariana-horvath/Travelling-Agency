package service;

import exception.InputValidationFailedException;
import model.User;
import model.VacationPackage;
import repository.UserRepository;

public class UserService {

    private final UserRepository userRepository;

    public UserService() {
        userRepository = new UserRepository();
    }

    public void createUser(User user) {
        if (user.getUsername() != null && !user.getUsername().isEmpty()) {
            userRepository.insertUser(user);
        } else throw new InputValidationFailedException("Username cannot be empty!");
    }

    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public void addBookedVacation(User user, VacationPackage vacationPackage) {
        userRepository.addBookedVacation(user, vacationPackage);
    }
}
