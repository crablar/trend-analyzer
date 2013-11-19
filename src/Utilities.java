import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * User: jeffreymeyerson
 * Date: 11/9/13
 * Time: 2:10 PM
 */
public class Utilities {

    public static String[][] toStringGrid(List<Entity> entities){
        String[][] grid = new String[entities.size() + 1][Field.getAllFields().size() + 1];
        grid[0][0] = "EntityID";
        List<Field> fields = Field.getAllFields();
        for(int i = 0; i < fields.size(); i++){
            grid[0][i + 1] = fields.get(i).toString();
        }
         for(int i = 0; i < entities.size(); i++){
            Entity entity = entities.get(i);
            grid[i + 1][0] = entity.toString();
            for(int j = 0; j < fields.size(); j++){
                grid[i + 1][j + 1] = entity.getAttribute(fields.get(j)).toString();
            }
        }
        return grid;
    }

    public static String formatGridToString(String[][] grid){
        StringBuilder result = new StringBuilder("");
        for(int i = 0; i < grid.length;i++){
            for(int j = 0; j < grid[0].length; j++){
                result.append(grid[i][j] + ",");
            }
            result.append("\n");
        }
        return result.toString();
    }

    public static void printAllFields() {
        for(Field f : Field.getAllFields())
            System.out.println(f.toString());
    }

    public static void printCommandDescriptions() {
        System.out.println(
            "'CLEAR' removes the results of the current session.\n" +
            "'LIST' displays all available fields you can query on.");
    }
}
