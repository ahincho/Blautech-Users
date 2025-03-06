package com.blautech.ecommerce.users.application.services;

import com.blautech.ecommerce.users.application.ports.in.CreateOneUserUseCase;
import com.blautech.ecommerce.users.application.ports.out.UserPersistencePort;
import com.blautech.ecommerce.users.domain.exceptions.UserDuplicateException;
import com.blautech.ecommerce.users.domain.exceptions.UserUnderAgeException;
import com.blautech.ecommerce.users.domain.models.User;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CreateOneUserDefaultService implements CreateOneUserUseCase {
    private final UserPersistencePort userPersistencePort;
    public CreateOneUserDefaultService(UserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }
    @Override
    public User execute(User user) throws UserUnderAgeException, UserDuplicateException {
        validateUserAge(user.getBirthday());
        validateEmailUniqueness(user.getEmail());
        return this.userPersistencePort.createOneUser(user);
    }
    private void validateUserAge(LocalDate birthday) throws UserUnderAgeException {
        LocalDate legalAgeDate = birthday.plusYears(18);
        if (legalAgeDate.isAfter(LocalDate.now())) {
            throw new UserUnderAgeException("User must be at least 18 years old.");
        }
    }
    private void validateEmailUniqueness(String email) throws UserDuplicateException {
        if (this.userPersistencePort.existsOneUserByEmail(email)) {
            throw new UserDuplicateException(String.format("User with email '%s' already exists", email));
        }
    }
}
