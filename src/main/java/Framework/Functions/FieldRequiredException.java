package Framework.Functions;

public class FieldRequiredException extends Throwable {
    public FieldRequiredException(String message) {
        try {
            throw new FieldRequiredException(message);
        } catch (FieldRequiredException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }
    }
}
