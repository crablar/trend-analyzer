/**
 * Created with IntelliJ IDEA.
 * User: jeffreymeyerson
 * Date: 11/2/13
 * Time: 2:22 PM
 */
public class InvalidQueryException extends Exception {

    static double similarityThreshold = .5;
    String reason;

    public InvalidQueryException(String reason) {
        this.reason = reason;
    }

    public InvalidQueryException(Exception e) {
         reason = "Invalid query: " + e.toString();
    }

    public InvalidQueryException() {
        reason = "Bad input.";
    }

    public static InvalidQueryException tooSimilar() {
        return new InvalidQueryException("Similarity threshold between this entity's nonsensitive information and corresponding nonsensitive information of an anonymized in-memory entity has been reached.");
    }

    public static InvalidQueryException nonexistentField(String nef){
        return new InvalidQueryException("Nonexistent field: " + nef);
    }

}
