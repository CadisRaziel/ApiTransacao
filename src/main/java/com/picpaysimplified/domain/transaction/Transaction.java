package com.picpaysimplified.domain.transaction;

import com.picpaysimplified.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

///transactions -> no plural pois tabelas NOSQL utiliza a palavra reservada 'transaction' no singular !!
@Entity(name = "transactions")
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;

    ///referencia pra quem foi o pagador(sender) e o que recebeu(receiver)
    @ManyToOne //-> um usuario pode ter varias transações(Many), mais uma transação só pode ter um usuario(One)
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne //-> um usuario pode ter varias transações(Many), mais uma transação só pode ter um usuario(One)
    @JoinColumn(name = "receiver_id")
    private User receiver;

    private LocalDateTime timestamp;
}
