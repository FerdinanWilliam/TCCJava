package com.itb.inf2bm.pizzaria.exceptions;

public class NotFound extends RuntimeException {

    // extends : HERANÇA, significa que BadResquest herda características de RuntimeException

    // Construtor: Ele possui o mesmo nome da classe, utilizamos quando precisamos
    //             criar um objeto JÁ PASSANDO PARÂMETRO(S)
    // Toda classe possui o construtor padrão, a partir do momento que você crua outros
    // Construtores a classe perde o construtor padrão, devendo ser recriado a critério.

    // O construtor padrão não possui parâmetros
    // Veja:
    public NotFound(){}

    // super: Acessando o construtor da super 'classe mãe'
    public NotFound(String message){
        super(message);
    }


}
