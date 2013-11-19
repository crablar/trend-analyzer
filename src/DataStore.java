import java.util.*;

/**
 * User: jeffreymeyerson
 * Date: 11/2/13
 * Time: 10:18 AM
 */
public class DataStore {

    static String[] columns = null;
    static HashMap<Field, HashMap<Object, List<Entity>>> lookUps = new HashMap<>();

    /**
     * Get all entities whose field matches the key.
     * @param field
     * @param key
     * @return
     */
    static List<Entity> getEntities(Field field, Object key){
        return lookUps.get(field).get(key);
    }

    static void initialize(String[] columnTypes) {
        for(int i = 0; i < columns.length; i++){
            Field.FieldType fieldType = Field.FieldType.valueOf(columnTypes[i].replace("/", "_"));
            Field field = new Field(columns[i].toUpperCase(), fieldType);
            Field.fieldsToTypes.put(field, fieldType);
            Field.stringsToFields.put(columns[i].toUpperCase(), field);
            lookUps.put(field, new HashMap<Object, List<Entity>>());
        }
    }

    static void addEntity(Entity entity) {
        for(Field field : Field.getAllFields()){
            Map<Object,List<Entity>> lookup = lookUps.get(field);
            List matches = lookup.get(entity.getAttribute(field));
            if(matches == null){
                matches = new ArrayList(1);
            }
            matches.add(entity);
            lookup.put(entity.getAttribute(field), matches);
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

    public static Set<Entity> getResults(Query query) {
        Set<Entity> result = new HashSet<>();
        Map<Object, List<Entity>> lookup = lookUps.get(query.selectedField);
        for(Map.Entry<Object, List<Entity>> e : lookup.entrySet()){
            if(query.restriction.allows(e.getKey())){
                result.addAll(e.getValue());
            }
        }
        return result;
    }

}