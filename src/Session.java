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

    public static void main(String[] args) throws Exception {
        Initializer.init("/Users/jeffreymeyerson/Documents/workspace/IdeaProjects/TrendAnalyzer/src/config/hospital");
        startSession();
    }

    public static void startSession() throws IOException {
        logger = new Logger();
        System.out.println("Welcome to TrendAnalyzer. Browsing database: " + Configuration.configName);
        while(!finished){
            logger.clearLog();
            System.out.print("Enter query: ");
            String input = in.nextLine().toUpperCase();
            Query query;
            try {
                query = QueryUtil.simpleQuery(input);
            } catch (InvalidQueryException e) {
                System.out.println("Invalid query. Try again.");
                continue;
            }
            Set<Entity> results = DataStore.getResults(query);
            List lResults = Arrays.asList(results.toArray());
            logger.out(Utilities.formatGridToString(Utilities.toStringGrid(lResults)));
        }
    }

}
