package singleton;

import pojos.Entity;

import java.io.File;
import java.util.Map;
import java.util.Scanner;

/**
 * User: jeffreymeyerson
 * Date: 11/2/13
 * Time: 10:05 AM
 */
public class Initializer {

    public static void init() throws Exception{
        Configuration.configure();
        Scanner s = new Scanner(new File(Configuration.CSVFilePath));
        String line = s.nextLine().toUpperCase();
        String[] columnNames = line.split(",");
        applyAliases(columnNames);
        DataStore.columns = columnNames;
        line = s.nextLine();
        String[] columnTypes = line.split(",");
        DataStore.initialize(columnTypes);
        for(int i = 0; i < Configuration.numLinesToRead; i++){
            line = s.nextLine().toUpperCase();
            if(line.endsWith(","))
                line += ",";
            DataStore.addEntity(new Entity(line.replace(",,", ",NO ENTRY,").split(",")));
        }
    }

    private static void applyAliases(String[] columnNames) {
        for(int i = 0; i < columnNames.length; i++){
            for(Map.Entry<String,String> alias : Configuration.aliases.entrySet()){
                if(columnNames[i].startsWith(alias.getKey())){
                    String replacement = columnNames[i].replace(alias.getKey(), alias.getValue());
                    columnNames[i] = replacement;
                }
            }
        }
    }

}
