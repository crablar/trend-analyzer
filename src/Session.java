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
    static List lResults;
    static Results results;

    public static void main(String[] args) throws Exception {
        Initializer.init("/Users/jeffreymeyerson/Documents/workspace/IdeaProjects/TrendAnalyzer/src/config/hospital");
        startSession();
    }

    public static void startSession() throws IOException {
        logger = new Logger();
        System.out.println("Welcome to TrendAnalyzer. Browsing database: " + Configuration.configName);
        while(!finished){
            System.out.print("Enter query: ");
            String input = in.nextLine().toUpperCase();
            Query query;
            try {
                query = QueryUtil.simpleQuery(input);
            } catch (InvalidQueryException e) {
                System.out.println("Invalid query. Try again.");
                continue;
            }

            //lResults = Arrays.asList(results.toArray());
            logger.clearLog();
            logger.out(Utilities.formatGridToString(Utilities.toStringGrid(lResults)));
        }
    }

    private void doCommands(String input){
        switch(input){
            case("CLEAR"):

        }

    }

}
