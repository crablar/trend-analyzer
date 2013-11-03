import java.io.File;
import java.util.Scanner;

/**
 * User: jeffreymeyerson
 * Date: 11/2/13
 * Time: 10:05 AM
 */
public class Loader {

    static void load(String path, int numLines) throws Exception{
        Scanner s = new Scanner(new File(path));
        String line = s.nextLine();
        Database.columns = line.split(",");
        line = s.nextLine();
        String[] columnTypes = line.split(",");
        Database.initialize(columnTypes);
        System.out.println("DATABASE COLUMNS: " + Database.columns.length);
        for(int i = 0; i < numLines; i++){
            line = s.nextLine();
            if(line.endsWith(","))
                line += ",";
            Database.addEntity(new Entity(line.replace(",,", ",NO ENTRY,").split(",")));
        }
    }

}
