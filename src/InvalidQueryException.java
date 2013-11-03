/**
 * Created with IntelliJ IDEA.
 * User: jeffreymeyerson
 * Date: 11/2/13
 * Time: 2:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class InvalidQueryException extends Exception {

    static double similarityThreshold = .5;
    String reason;

    public InvalidQueryException(String reason) {
        this.reason = reason;
    }

    public static InvalidQueryException similarityException() {
        return new InvalidQueryException("Similarity threshold between this entity's nonsensitive information and corresponding nonsensitive information of an anonymized in-memory entity has been reached.");
    }

}
