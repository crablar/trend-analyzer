import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * User: jeffreymeyerson
 * Date: 11/4/13
 * Time: 6:50 PM
 */
public class Configuration {

    public static Set<String> queryOperands = new HashSet<>();
    public static int numLinesToRead = 30;
    public static String CSVFilePath;

    private static Map<String, String> setupMap = new HashMap<String, String>();

    public static void configure(String configurationFilePath) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(configurationFilePath));
        while(scanner.hasNextLine()){
            String[] lineArr = scanner.next().split(":");
            setupMap.put(lineArr[0], lineArr[1]);
        }
        setQueryOperands();
        setCSVFilePath();

    }

    private static void setQueryOperands(){
        List<String> opArr = Arrays.asList(setupMap.get("queryOperands").split(","));
        queryOperands.addAll(opArr);
    }

    private static void setCSVFilePath() {
        CSVFilePath = (String) setupMap.get("CSVFilePath");
    }

}
