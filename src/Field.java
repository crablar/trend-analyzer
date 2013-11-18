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

    public static Field getFieldForString(String s){
        return stringsToFields.get(s);
    }

    public static ArrayList<Field> getAllFields(){
        return new ArrayList<Field>(fieldsToTypes.keySet());
    }

    public Field(String fieldName, FieldType fieldType) {
        this.name = fieldName.toUpperCase();
        this.fieldType = fieldType;
    }

    public boolean isID() {
        return this.fieldType == FieldType.ID;
    }

    public boolean isCont(){
        return this.fieldType == FieldType.CONT || this.fieldType == FieldType.CONT_SENSITIVE;
    }

    public boolean isSensitive(){
        return this.fieldType == FieldType.CAT_SENSITIVE || this.fieldType == FieldType.CONT_SENSITIVE;
    }

    public String toString(){
        return name + " (" + fieldType + ")";
    }

}
