package com.llenque.llenquepizzeria.service.exeption;

public class EmailApiExeption extends RuntimeException{

    public EmailApiExeption() {
        super("Error sending email");
    }
}
