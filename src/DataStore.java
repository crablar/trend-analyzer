import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: jeffreymeyerson
 * Date: 11/2/13
 * Time: 10:18 AM
 */
public class DataStore {

    static String[] columns = null;
    static HashMap<Field, HashMap<Object, Entity>> lookUps = new HashMap<Field, HashMap<Object, Entity>>();

    static Entity getEntity(Field field, Object key){
        return lookUps.get(field).get(key);
    }

    static void initialize(String[] columnTypes) {
        for(int i = 0; i < columns.length; i++){
            Field.FieldType fieldType = Field.FieldType.valueOf(columnTypes[i].replace("/", "_"));
            Field field = new Field(columns[i], fieldType);
            Field.fieldsToTypes.put(field, fieldType);
            Field.stringsToFields.put(columns[i], field);
            lookUps.put(field, new HashMap<Object, Entity>());
        }
    }

    static void addEntity(Entity entity) {
        for(Field field : Field.getAllFields()){
            lookUps.get(field).put(entity.getAttribute(field), entity);
        }
    }

    public static void print() throws Exception{
        int i = 0;
        for(HashMap map : lookUps.values()) {
            System.out.println(map);
            i++;
        }
        System.out.println("Maps in database " + i);
    }

    public static List<Entity> getResults(Query query) {
        ArrayList<Entity> result = new ArrayList<>();
        Map<Object, Entity> lookup = lookUps.get(query.selectedField);
        for(Map.Entry<Object, Entity> e : lookup.entrySet()){
            if(query.restriction.allows(e.getKey())){
                result.add(e.getValue());
            }
        }
        return result;
    }

}

