package interpreter;

public class IncompatibleTypeException extends Exception {
    public IncompatibleTypeException()
    {
        
    }
    public IncompatibleTypeException(String message){
    super(message);
    }
}
