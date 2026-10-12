package mx.edu.utez.cotizadores.exception.customExceptions;

public class CustomBadRequestException extends RuntimeException{
    public CustomBadRequestException(String message){
        super(message);
    }
}
