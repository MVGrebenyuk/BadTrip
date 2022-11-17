package ru.alexsolution.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.UpdateTimestamp;
import ru.alexsolution.entity.Password;
import ru.alexsolution.entity.Role;
import ru.alexsolution.entity.Trip;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Builder
public class User {

    @Id
    private UUID id;

    private String login;

    private String firstName;

    private String lastName;

    private String avatar;

    private LocalDate dateOfBirth;

    private String city;

    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Password password;

    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @JsonIgnore
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UserDetails userDetails;

    private String about;
}
