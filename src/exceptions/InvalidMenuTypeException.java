package exceptions;

public class InvalidMenuTypeException extends Exception {

    private String message;
    public InvalidMenuTypeException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
