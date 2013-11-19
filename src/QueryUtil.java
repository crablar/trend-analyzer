import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: jeffreymeyerson
 * Date: 11/2/13
 * Time: 2:14 PM
 * To change this template use File | Settings | File Templates.
 */

/*
fields?
    "Hospital name", "Provider Number"...
list "Hospital name"
    SOUTHEAST ALABAMA MEDICAL CENTER, MARSHALL MEDICAL CENTER SOUTH...
list "Hospital Name" where "Percent of patients who..." > 70
    SOUTHEAST ALABAMA MEDICAL CENTER
loadEntities where "Percent of patients who..." > 70
    <loads everything except ID information>
loadEntity "Provider Number=01001"
    <loads all nonsensitive info about that entity, unless similarity threshold is exceeded>

 */
public class QueryUtil {

    public static Query simpleQuery(String queryString) throws InvalidQueryException {
        queryString = queryString.toUpperCase();
        Query q = new Query();
        q.selectedField = getSelectedField(queryString);
        q.restriction = getRestriction(q.selectedField, queryString);
        return q;
    }

    private static Field getSelectedField(String queryString) throws InvalidQueryException {
        try{
            String[] qArr = queryString.split(" ");
            String fieldString = "";
            for (int i = 0; i < qArr.length; i++) {
                if (!Configuration.queryOperands.contains(qArr[i])) {
                    fieldString += qArr[i] + " ";
                }
                else {
                    fieldString = fieldString.substring(0, fieldString.length() - 1);
                    break;
                }
            }
            return Field.getFieldForString(fieldString);
        }
        catch(Exception e){
            throw new InvalidQueryException(e.getMessage());
        }
    }

    private static Restriction getRestriction(Field select, String queryString) throws InvalidQueryException {
        try{
            String operand = null;
            String rhs = "";
            String[] qArr = queryString.split(" ");
            for (int i = 0; i < qArr.length; i++) {
                if (Configuration.queryOperands.contains(qArr[i])) {
                    if (operand != null) {
                        throw new InvalidQueryException("Multiple operands in a single query");
                    }
                    operand = qArr[i];
                } else if (operand != null) {
                    rhs += qArr[i] + " ";
                }
            }
            return new Restriction(select, operand, rhs.substring(0, rhs.length() - 1));
        }
        catch(Exception e){
            throw new InvalidQueryException(e);
        }
    }


    boolean isAcceptablyPrivate(Query q) {
        if (Session.hasIDInformation)
            return q.selectedField.isSensitive();
        if (Session.hasSensitiveInformation)
            return q.selectedField.isID();
        return true;
    }
}
