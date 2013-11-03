import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: jeffreymeyerson
 * Date: 11/2/13
 * Time: 10:18 AM
 * To change this template use File | Settings | File Templates.
 */
public class Database {

    static String[] columns;
    static HashMap<Field, HashMap<Object, Entity>> lookUps = new HashMap<Field, HashMap<Object, Entity>>();

    static Entity getEntity(String fieldName, String key){
        return null;
    }

    static void initialize(String[] columnTypes) {
        for(int i = 0; i < columns.length; i++){
            Field.FieldType fieldType = Field.FieldType.valueOf(columnTypes[i].replace("/", "_"));
            Field field = new Field(columns[i], fieldType);
            Field.fieldsToTypes.put(columns[i], fieldType);
            Field.stringsToFields.put(columns[i], field);
            lookUps.put(field, new HashMap<Object, Entity>());
        }
    }

    static void addEntity(Entity entity) {
        for(Field field : Field.stringsToFields.values()){
            lookUps.get(field).put(entity.getAttribute(field), entity);
        }
    }
}

