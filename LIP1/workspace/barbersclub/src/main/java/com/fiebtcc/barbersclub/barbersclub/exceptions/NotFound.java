package com.fiebtcc.barbersclub.barbersclub.exceptions;

public class NotFound extends RuntimeException {

    // extends : HERANÇA, significa que BadRequest herda características de RuntimeException

    // Construtor: Ele possui o mesmo nome da classe, utilizamos quando precisamos
    //             criar um objeto JÁ PASSANDO PARÂMETRO(S)
    // Toda classe possui o construtor padrão, a partir do momento que vc cria outros
    // construtores a classe perde o construtor padrão, devendo ser recriado a critério.

    // O construtor padrão não possui parâmetros
    // Veja:
    public NotFound() {

    }
    // super: Acessando o construtor da super classe 'classe mãe'

    public NotFound(String message) {
        super(message);
    }
}
