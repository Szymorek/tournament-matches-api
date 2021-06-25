package com.example.tournamentmatches.user;

import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@EqualsAndHashCode
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class User implements UserDetails {
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    @Id
    private Long id;
    @NotNull
    @Column(unique = true)
    private String username;
    @NotNull
    @Column(unique = true)
    private String email;
    @NotNull
    private String name;
    @NotNull
    private String surname;

    @NotNull
    private String password;

    private Boolean locked = false;
    private Boolean enabled = true;


    public User(String username, String email, String name, String surname, String password) {
        this.username = username;
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }



    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
