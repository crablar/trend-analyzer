import java.io.File;
import java.util.Scanner;

/**
 * User: jeffreymeyerson
 * Date: 11/2/13
 * Time: 10:05 AM
 */
public class Initializer {

    static void init(String path, int numLines) throws Exception{
        Scanner s = new Scanner(new File(path));
        String line = s.nextLine();
        DataStore.columns = line.split(",");
        line = s.nextLine();
        String[] columnTypes = line.split(",");
        DataStore.initialize(columnTypes);
        for(int i = 0; i < numLines; i++){
            line = s.nextLine();
            if(line.endsWith(","))
                line += ",";
            DataStore.addEntity(new Entity(line.replace(",,", ",NO ENTRY,").split(",")));
        }
    }

}
