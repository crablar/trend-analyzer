package singleton;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * User: jeffreymeyerson
 * Date: 11/4/13
 * Time: 6:50 PM
 */
public class Configuration {

    public static String configName = "hospital";
    public static final String projectPath = System.getProperty("user.dir");
    private final static String configPath = "/src/config/";

    public static Set<String> queryOperands = new HashSet<>();
    public static Map<String,String> aliases = new HashMap<>();
    public static int numLinesToRead;
    public static String CSVFilePath;
    public static int k_value;

    private static Map<String, String> setupMap = new HashMap<>();

    public static void configure() throws FileNotFoundException {
        String configurationFilePath = projectPath + configPath + configName;
        Scanner scanner = new Scanner(new File(configurationFilePath));
        while(scanner.hasNextLine()){
            String[] lineArr = scanner.nextLine().split(":");
            setupMap.put(lineArr[0], lineArr[1]);
        }
        setLinesToRead();
        setQueryOperands();
        setCSVFilePath();
        setAliases();
        setKValue();
    }

    private static void setAliases() {
        if(!setupMap.containsKey("aliases")) {
            return;
        }
        for(String al : setupMap.get("aliases").split("%20")){
            String[] arr = al.split("=");
            aliases.put(arr[0], arr[1]);
        }
    }

    private static void setLinesToRead() {
        numLinesToRead = Integer.parseInt(setupMap.get("numLinesToRead"));
    }

    private static void setQueryOperands(){
        List<String> opArr = Arrays.asList(setupMap.get("queryOperands").split(","));
        queryOperands.addAll(opArr);
    }

    private static void setCSVFilePath() {
        CSVFilePath = projectPath + setupMap.get("CSVFilePath");
    }

    private static void setKValue() {
        k_value = Integer.parseInt(setupMap.get("k-anonymity"));
    }

}