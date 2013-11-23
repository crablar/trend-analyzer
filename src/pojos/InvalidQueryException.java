package pojos;

/**
 * Created with IntelliJ IDEA.
 * User: jeffreymeyerson
 * Date: 11/2/13
 * Time: 2:22 PM
 */
public class InvalidQueryException extends Exception {

    Exception rootCause;
    public String reason;

    public InvalidQueryException(String reason) {
        rootCause = this;
        this.reason = reason;
    }

    public InvalidQueryException(Exception e) {
        rootCause = e;
        reason = "Invalid query.";
    }

    public InvalidQueryException() {
        rootCause = this;
        reason = "Bad input.";
    }

}
