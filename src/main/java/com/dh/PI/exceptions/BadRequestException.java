package com.dh.PI.exceptions;

public class BadRequestException extends Exception{
    public BadRequestException(String message){super(message);} //la agrego en el metodo agregar del controller
}
