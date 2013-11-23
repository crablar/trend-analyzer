package singleton;

import pojos.*;

import java.util.*;

/**
 * User: jeffreymeyerson
 * Date: 11/2/13
 * Time: 10:18 AM
 */
public class DataStore {

    public static String[] columns = null;
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

    public static Results getResults(Query query) throws InvalidQueryException {
        List<Entity> resultSet = new ArrayList<>();
        Map<Object, List<Entity>> lookup = allEntities.getFieldMappings(query.selectedField);
        for(Map.Entry<Object, List<Entity>> e : lookup.entrySet()){
            if(query.restriction.allows(e.getKey())){
                resultSet.addAll(e.getValue());
            }
        }
        Results results = new Results(resultSet);
        AnonymityVerifier.assertAnonymity(results);
        return results;
    }

    static void addEntity(Entity entity) {
        allEntities.addEntity(entity);
    }

    public static List<String> getAllFieldEntries(Field field) {
        Map<Object, List<Entity>> fieldMappings = allEntities.getFieldMappings(field);
        List<String> result = new ArrayList<>();
        for(Object key : fieldMappings.keySet()){
            result.add(key.toString());
        }
        return result;
    }
}