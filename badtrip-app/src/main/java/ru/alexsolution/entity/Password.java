package ru.alexsolution.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.alexsolution.entity.user.User;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "passwords")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Password {

    @Id
    private UUID id;

    private String password;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
}
