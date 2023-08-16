package com.picpaysimplified.service;


import com.picpaysimplified.domain.transaction.Transaction;
import com.picpaysimplified.domain.user.User;
import com.picpaysimplified.dto.TransactionDTO;
import com.picpaysimplified.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;


@Service
public class TransactionService {
    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository transectionRepository;

    @Autowired
    ///RestTemplate é uma classe do spring que permite fazer chamadas http(ou seja se eu preciso pegar uma api externa eu uso o restTemplate)
    private RestTemplate restTemplate;

    public void createTransaction(TransactionDTO transactionDTO) throws Exception {
        User sender =  this.userService.findUserById(transactionDTO.senderId());
        User receiver = this.userService.findUserById(transactionDTO.receiverId());

        userService.validateTransaction(sender, transactionDTO.value());

        boolean isAuthorized = this.authorizeTransaction(sender, transactionDTO.value());
        if(!isAuthorized) {
            throw new Exception("Transação não autorizada.");
        }

        Transaction transaction = new Transaction();
        transaction.setAmount(transactionDTO.value());
        transaction.setSender(sender);
        transaction.setReceiver(receiver);
        transaction.setTimestamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transactionDTO.value()));
        receiver.setBalance(receiver.getBalance().add(transactionDTO.value()));

        this.transectionRepository.save(transaction);
        this.userService.saveUser(sender);
        this.userService.saveUser(receiver);
    }

    public boolean authorizeTransaction(User sender, BigDecimal value) {
        ///Chamando um serviço terceiro (autorizador externo)
        ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity("https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6", Map.class);

        ///message -> é o retorno que o site me da
        if(authorizationResponse.getStatusCode() == HttpStatus.OK && authorizationResponse.getBody().get("message").equals("Autorizado")) {
            return true;
        } else {
            return false;
        }

    }
}
