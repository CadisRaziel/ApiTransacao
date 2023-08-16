package com.picpaysimplified.service;

import com.picpaysimplified.domain.user.User;
import com.picpaysimplified.domain.user.UserType;
import com.picpaysimplified.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception{
        ///Se o tipo do usuario for igual logista ele não esta autroizado a realizar transação
        if (sender.getUser() == UserType.MERCHANT) {
            throw new Exception("Usuário do tipo Logista não está autorizado a realizar esse tipo de transação.");
        }

        ///verifica se ele tem 'balance' do usuario com o amout e verifica se é menor que 0 ele não tem saldo
        if(sender.getBalance().compareTo(amount) < 0) {
            throw new Exception("Saldo insulficiente.");
        }
    }


    public User findUserById(Long id) throws Exception {
        ///função criada pois eu nao quero que meu transactionService tenha acesso ao meu repository
       return this.repository.findUserById(id).orElseThrow(() -> new Exception("Usuário não encontrado."));
    }

    public void saveUser(User user) {
        ///Depois que eu atualizar os dados com as funções acima, eu quero atualizar isso no cadastro do usuario
        this.repository.save(user);
    }
}
