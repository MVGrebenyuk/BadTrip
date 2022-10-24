package ru.alexsolution.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alexsolution.converters.UserConverter;
import ru.alexsolution.dto.RegistrationDto;
import ru.alexsolution.entity.Password;
import ru.alexsolution.entity.Role;
import ru.alexsolution.entity.User;
import ru.alexsolution.repositories.UserRepository;

import java.security.Principal;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserConverter userConverter;

    public Optional<User> findByLogin(String username) {
        Optional<User> user = userRepository.findByLogin(username);
        return user;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = findByLogin(login).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", login)));
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword().getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Transactional
    public User saveNewUser(RegistrationDto registrationDto) {
        User user = userConverter.DtoToEntity(registrationDto);
        userRepository.save(user);
        user.setPassword(new Password(UUID.randomUUID(), bCryptPasswordEncoder.encode(registrationDto.getPassword()), user));
        userRepository.save(user);
        return user;
    }

    @Transactional
    public void updateUser(Principal principal, RegistrationDto updateProfileDto) {
        User user = userRepository.findByLogin(principal.getName()).orElseThrow();
        userConverter.fillUser(user, updateProfileDto);
        userRepository.save(user);
    }

    public User findByID(UUID id){
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Пользователь не найден"));
    }

}
