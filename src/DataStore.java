import java.util.*;

/**
 * User: jeffreymeyerson
 * Date: 11/2/13
 * Time: 10:18 AM
 */
public class DataStore {

    static String[] columns = null;
    static LookUp allEntities = new LookUp();

    static void initialize(String[] columnTypes) {
        for(int i = 0; i < columns.length; i++){
            Field.FieldType fieldType = Field.FieldType.valueOf(columnTypes[i].replace("/", "_"));
            Field field = new Field(columns[i].toUpperCase(), fieldType);
            Field.fieldsToTypes.put(field, fieldType);
            Field.stringsToFields.put(columns[i].toUpperCase(), field);
            allEntities.putEntities(field, new HashMap<Object, List<Entity>>());
        }
    }

    static void addEntity(Entity entity) {
        allEntities.addEntity(entity);
    }

    public static Results getResults(Query query) {
        Set<Entity> resultSet = new HashSet<>();
        Map<Object, List<Entity>> lookup = allEntities.getFieldMappings(query.selectedField);
        for(Map.Entry<Object, List<Entity>> e : lookup.entrySet()){
            if(query.restriction.allows(e.getKey())){
                resultSet.addAll(e.getValue());
            }
        }
        return new Results(resultSet);
    }

}