import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

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
        Initializer.init("/Users/jeffreymeyerson/Documents/workspace/IdeaProjects/TrendAnalyzer/src/config/hospital");
        startSession();
    }

    public static void startSession() throws Exception {
        logger = new Logger();
        System.out.println("Welcome to TrendAnalyzer. Type 'commands' for help.\nBrowsing database: " + Configuration.configName);
        while(!finished){
            System.out.print("Enter query: ");
            String input = in.nextLine().toUpperCase();
            Query query;
            try {
                if(!doCommand(input)) {
                    query = QueryUtil.simpleQuery(input);
                    results = DataStore.getResults(query);
                }
            } catch (InvalidQueryException e) {
                System.out.println("Invalid query. Try again.");
                continue;
            }
            logger.clearLog();
            logger.out(results.toString());
        }
    }

    private static boolean doCommand(String input){
        switch(input){
            case("CLEAR"):
                results = new Results();
                break;
            case("LIST"):
                Utilities.printAllFields();
                break;
            case("COMMANDS"):
                Utilities.printCommandDescriptions();
                break;
            default:
                return false;
        }
        return true;
    }

}
