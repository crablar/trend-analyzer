package pojos;

import singleton.CSVFormatter;
import singleton.DataStore;

import java.util.HashMap;
import java.util.Map;

/**
 * User: jeffreymeyerson
 * Date: 11/2/13
 * Time: 10:18 AM
 */
public class Entity {

    static int entityCount = 0;

    HashMap<Field, Object> attributes;
    int entityID;

    public Entity(String[] atts) {
        this.entityID = entityCount++;
        attributes = new HashMap<Field, Object>();
        for(int i = 0; i < DataStore.columns.length; i++){
            Field field = Field.getFieldForString(DataStore.columns[i]);
            Object o = CSVFormatter.cleanEntry(field, atts[i]);
            attributes.put(field, o);
        }
    }

    public Entity(int entityID, HashMap<Field, Object> attCopy) {
        this.entityID = entityID;
        this.attributes = attCopy;
    }

    public Object getAttribute(Field field) {
        return attributes.get(field);
    }

    public String toString(){
        return "entity_" + entityID;
    }

    /**
     * @return a version of the pojos.Entity with sensitive fields removed.
     */
    public Entity censoredCopy(){
        HashMap<Field, Object> attCopy = new HashMap<Field, Object>();
        for(Map.Entry<Field, Object> e : attributes.entrySet())
            if(!e.getKey().isSensitive())
                attCopy.put(e.getKey(), e.getValue());
        return new Entity(entityID, attCopy);
    }

}
