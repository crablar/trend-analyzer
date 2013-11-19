import java.io.File;
import java.util.Scanner;

/**
 * User: jeffreymeyerson
 * Date: 11/2/13
 * Time: 10:05 AM
 */
public class Initializer {

    static void init(String configFilePath) throws Exception{
        Configuration.configure(configFilePath);
        Scanner s = new Scanner(new File(Configuration.CSVFilePath));
        String line = s.nextLine().toUpperCase();
        DataStore.columns = line.split(",");
        line = s.nextLine();
        String[] columnTypes = line.split(",");
        DataStore.initialize(columnTypes);
        for(int i = 0; i < Configuration.numLinesToRead; i++){
            line = s.nextLine();
            if(line.endsWith(","))
                line += ",";
            DataStore.addEntity(new Entity(line.replace(",,", ",NO ENTRY,").split(",")));
        }
    }

}
