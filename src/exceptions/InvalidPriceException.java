package exceptions;

public class InvalidPriceException extends Exception {
    private String message;
    public InvalidPriceException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
