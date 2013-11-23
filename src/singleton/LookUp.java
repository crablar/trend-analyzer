package singleton;

import pojo.Entity;
import pojo.Field;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: jeffreymeyerson
 * Date: 11/19/13
 * Time: 10:31 AM
 */
public class LookUp {

    private Map<Field, Map<Object, List<Entity>>> lookUps;

    public LookUp(){
        this.lookUps = new HashMap<>();
    }

    public List<Entity> getEntities(Field field, Object key){
        return lookUps.get(field).get(key);
    }

    public void putEntities(Field field, HashMap<Object, List<Entity>> entityMappings) {
        lookUps.put(field, entityMappings);
    }

    public void addEntity(Entity entity) {
        for(Field field : Field.getAllFields()){
            List<Entity> matches = getEntities(field, entity.getAttribute(field));
            if(matches == null){
                matches = new ArrayList<>(1);
            }
            matches.add(entity);
            lookUps.get(field).put(entity.getAttribute(field), matches);
        }
    }

    public Map<Object, List<Entity>> getFieldMappings(Field selectedField) {
        return lookUps.get(selectedField);
    }
}
