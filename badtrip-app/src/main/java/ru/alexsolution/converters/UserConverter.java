package ru.alexsolution.converters;

import lombok.RequiredArgsConstructor;
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
                .roles(roleRepository.findAllByName("ROLE_USER"))
                .avatar("https://wannago.hb.bizmrg.com/1_W35QUSvGpcLuxPo3SRTH4w.png")
                .build();
        return user;
    }

    public User fillUser (User user, RegistrationDto registrationDto) {
                user.setCity(registrationDto.getCity() != null ? registrationDto.getCity() : user.getCity());
                user.setFirstName(registrationDto.getFirstName() != null ? registrationDto.getFirstName() : user.getFirstName());
                user.setLastName(registrationDto.getLastName() != null ? registrationDto.getLastName() : user.getLastName());
                user.setDateOfBirth(registrationDto.getDateOfBirth() != null ? registrationDto.getDateOfBirth() : user.getDateOfBirth());
                user.setAvatar(registrationDto.getAvatar());
                user.setAbout(registrationDto.getAbout() != null ? registrationDto.getAbout() : user.getAbout());
        return user;
    }
}
