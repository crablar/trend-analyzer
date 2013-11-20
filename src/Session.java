import java.util.List;
import java.util.Scanner;

/**
 * User: jeffreymeyerson
 * Date: 11/4/13
 * Time: 8:10 PM
 */

public class Session {

    static boolean hasIDInformation = false;
    static boolean hasSensitiveInformation = false;
    static Scanner in = new Scanner(System.in);
    static boolean finished = false;
    static Logger logger;
    static Results results = new Results();

    public static void main(String[] args) throws Exception {
        Initializer.init();
        runSession();
    }

    public static void runSession() throws Exception {
        logger = new Logger();
        System.out.println("Welcome to TrendAnalyzer. Type 'commands' for help.\nBrowsing database: " + Configuration.configName);
        while(!finished){
            System.out.print("Enter query: ");
            String input = in.nextLine().toUpperCase();
            Query query;
            try {
                if(!doCommand(input)) {
                    query = QueryUtil.simpleQuery(input);
                    if(results.isBlank()){
                        results = DataStore.getResults(query);
                    }
                    else{
                        results.refine(query);
                    }
                }
            }
            catch (InvalidQueryException e) {
                System.out.println("Invalid query. Try again.");
                continue;
            }
            logger.clearLog();
            logger.writeResults(results.toString());
        }
    }

    private static boolean doCommand(String input) throws InvalidQueryException{
        String[] inputArr = input.split(" ");
        if(inputArr.length > 1){
            return complexCommand(input);
        }
        switch(input){
            case("CLEAR"):
                results = new Results();
                return true;
            case("LIST"):
                Utilities.printAllFields();
                return true;
            case("COMMANDS"):
                Utilities.printCommandDescriptions();
                return true;
            default:
                return false;
        }
    }

    private static boolean complexCommand(String input) throws InvalidQueryException{
        String[] lfArr = input.split(" ", 3);
        if(lfArr[0].equals("LIST") && lfArr[1].equals("FIELDS")){
            if(lfArr.length > 2)  {
                printFields(lfArr[2]);
                return true;
            }
        }
        return false;
    }

    private static void printFields(String fieldName) throws InvalidQueryException {
        Field field = Field.getFieldForString(fieldName);
        if(field != null){
            List<String> fieldEntries = DataStore.getAllFieldEntries(field);
            for(String ent : fieldEntries){
                System.out.println(ent);
            }
        }
        else{
            throw new InvalidQueryException();
        }
    }

}
