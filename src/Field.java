import java.util.*;

/**
 * User: jeffreymeyerson
 * Date: 11/2/13
 * Time: 10:20 AM
 */
public class Field {

    public enum FieldType{
        ID,TIME,CAT,CAT_SENSITIVE,CONT,CONT_SENSITIVE;
    }

    static HashMap<Field, FieldType> fieldsToTypes = new HashMap<Field, FieldType>();
    static HashMap<String, Field> stringsToFields = new HashMap<String, Field>();

    String name;
    FieldType fieldType;

    public Field(String fieldName, FieldType fieldType) {
        this.name = fieldName;
        this.fieldType = fieldType;
    }

    public static boolean isID(Field field) {
        return fieldsToTypes.get(field) == FieldType.ID;
    }

    public static boolean isSensitive(Field field){
       return fieldsToTypes.get(field).equals(FieldType.CAT_SENSITIVE) || fieldsToTypes.get(field).equals(FieldType.CONT_SENSITIVE);
    }

    public static Field getFieldForString(String s){
        return stringsToFields.get(s);
    }

    public String toString(){
        return name + " : " + fieldType;
    }

    public static ArrayList<Field> getAllFields(){
        return new ArrayList<Field>(fieldsToTypes.keySet());
    }

}
