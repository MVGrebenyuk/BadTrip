package ru.alexsolution.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.alexsolution.dto.RegistrationDto;
import ru.alexsolution.entity.User;
import ru.alexsolution.repositories.RoleRepository;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserConverter {

    private final RoleRepository roleRepository;

    public User DtoToEntity (RegistrationDto registrationDto) {
        User user = User.builder()
                .id(UUID.randomUUID())
                .login(registrationDto.getLogin())
                .city(registrationDto.getCity())
                .firstName(registrationDto.getFirstName())
                .lastName(registrationDto.getLastName())
                .roles(roleRepository.findAllByName("ROLE_USER"))
                .dateOfBirth(registrationDto.getDateOfBirth())
                .build();

        return user;
    }
}
