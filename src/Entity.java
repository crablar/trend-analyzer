import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: jeffreymeyerson
 * Date: 11/2/13
 * Time: 10:18 AM
 * To change this template use File | Settings | File Templates.
 */
public class Entity {

    static int entityCount = 0;

    HashMap<Field, Object> attributes;
    int entityID;

    public Entity(String[] atts) {
        this.entityID = entityCount++;
        attributes = new HashMap<Field, Object>();
        for(int i = 0; i < Database.columns.length; i++){
            attributes.put(Field.getFieldForString(Database.columns[i]), atts[i]);
        }
    }

    public Object getAttribute(Field field) {
        return attributes.get(field);
    }

    public String toString(){
        return "Entity " + entityID;
    }

}
