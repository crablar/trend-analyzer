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
    public static Map<String,String> aliases = new HashMap<>();
    public static int numLinesToRead;
    public static String CSVFilePath;
    public static String configName;

    private static Map<String, String> setupMap = new HashMap<>();

    public static void configure(String configurationFilePath) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(configurationFilePath));
        while(scanner.hasNextLine()){
            String[] lineArr = scanner.nextLine().split(":");
            setupMap.put(lineArr[0], lineArr[1]);
        }
        setConfigName();
        setLinesToRead();
        setQueryOperands();
        setCSVFilePath();
        setAliases();
    }

    private static void setAliases() {
        if(!setupMap.containsKey("aliases"))
            return;
        for(String al : setupMap.get("aliases").split("%20")){
            String[] arr = al.split("=");
            aliases.put(arr[0], arr[1]);
        }
    }

    private static void setConfigName(){
        configName = setupMap.get("configName");
    }

    private static void setLinesToRead() {
        numLinesToRead = Integer.parseInt(setupMap.get("numLinesToRead"));
    }

    private static void setQueryOperands(){
        List<String> opArr = Arrays.asList(setupMap.get("queryOperands").split(","));
        queryOperands.addAll(opArr);
    }

    private static void setCSVFilePath() {
        CSVFilePath = setupMap.get("CSVFilePath");
    }

}
