package com.picpaysimplified.domain.user;

import com.picpaysimplified.dto.UserDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "users")
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
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


    ///Como eu implementei um record UserDTO eu vo precisar criar um construtor para ele
    public User(UserDTO userDTO) {
        this.firstName = userDTO.firstName();
        this.lastName = userDTO.lastName();
        this.document = userDTO.document();
        this.email = userDTO.email();
        this.password = userDTO.password();
        this.balance = userDTO.balance();
        this.user = userDTO.userType();
    }
}
