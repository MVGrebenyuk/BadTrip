package ru.alexsolution.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private User user;
}
