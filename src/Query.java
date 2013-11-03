import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

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
loadEntities "Percent of patients who..." > 70
    <loads everything except ID information>
loadEntity "Provider Number=01001"
    <loads all nonsensitive info about that entity, unless similarity threshold is exceeded>

 */
public class Query {

    Query parseQuery(String queryString){
        String[] params = getQuotedParams(queryString);
        String[] getRestrictions = getRestrictions(params, queryString);
        String[] fields = getFields(queryString);
        if(queryString.startsWith("list"))
            return list(fields);
    }


    /*
     Return all
     */
    Query loadEntities(Field[] fields, Restriction[] restrictions) throws InvalidQueryException{
        boolean sensitiveDataRead = false;
        ArrayList result = new ArrayList(fields.length);
        for(int i = 0; i < fields.length; i++) {
            sensitiveDataRead = sensitiveDataRead || Field.isSensitive(fields[0]);
            if(sensitiveDataRead && Field.isID(fields[i]))
                throw new InvalidQueryException("");
        }
    }


    private String[] getQuotedParams(String string) {
        ArrayList<String> result = new ArrayList<String>();
        Scanner scan = new Scanner(string);
        while(scan.hasNext())
            result.add(scan.next("(.*?)"));
        return (String[]) result.toArray();
    }

    private Restriction[] getRestrictions(String[] queryParams, String query){
        Restriction[] result = new Restriction[queryParams.length];
        for(int i = 0; i < queryParams.length; i++){
            Scanner scanner = new Scanner(query);
            scanner.next(queryParams[i] + "\"");
            StringBuilder restrictionString = new StringBuilder("");

        }

    }

    private Field[] getFields(String[] params) {
        Field[] fields = new Field[params.length];
        for(int i = 0; i < params.length; i++)
            fields[i] = Field.getFieldForString(params[i]);
        return fields;
    }


}
