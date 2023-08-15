package com.picpaysimplified.repositories;

import com.picpaysimplified.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    ///findUserByDocument -> como o jpa não tem a busca pelo documento, nós mesmo criamos...
    /// porém aqui ele cria a query na tabela em runtime find by coluna "document" (lembre que "document" é um atributdo da classe User, ou seja tem que ter o nome exato)
    /// find(JPA) User(classe) By(JPA) Document(atributo da classe)
    /// find(JPA) User(classe) By(JPA) id(atributo da classe)

    ///Optional -> Pode ou nao retornar um usuario (Pois não é certo que eu sempre irei retornar um usuario)
    Optional<User> findUserByDocument(String document);
    Optional<User> findUserById(Long id);
}
