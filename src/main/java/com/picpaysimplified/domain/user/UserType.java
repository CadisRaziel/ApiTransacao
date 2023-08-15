package com.picpaysimplified.domain.user;

public enum UserType {
    ///Ao invés de criar dois modelos, podemos criar apenas 1(com todos atributos de ambos common e merchant) e separa-los por um enum
    COMMON, MERCHANT
}
