import java.util.Scanner;
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
public class Queries {

    public static Query simpleQuery(String queryString) throws InvalidQueryException{
        Query q = new Query();
        q.selectedField = getSelectedField(queryString);
        q.restriction = getRestriction(q.selectedField, queryString);
        return q;
    }

    private static Field getSelectedField(String queryString){
        Matcher m = Pattern.compile("(.*?)").matcher(queryString);
        return m.find() ? Field.getFieldForString(m.group(1)) : null;
    }

    private static Restriction getRestriction(Field select, String query) throws InvalidQueryException{
        Scanner scan = new Scanner(query);
        StringBuilder str = new StringBuilder("");
        scan.next("where ");
        while(scan.hasNext())
            str.append(scan.next());
        return new Restriction(select,str.toString());
    }


    boolean isAcceptablyPrivate(Query q) {
        if(Session.hasIDInformation)
            return Field.isSensitive(q.selectedField);
        if(Session.hasSensitiveInformation)
            return Field.isID(q.selectedField);
        return true;
    }
}
