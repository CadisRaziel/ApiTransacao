package com.picpaysimplified.domain.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity(name = "users")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @Column(unique = true)//-> Não pode haver mais de um documento igual
    private String document;

    @Column(unique = true)//-> Não pode haver mais de um documento igual
    private String email;

    private String password;

    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type")
    private UserType user;
}
