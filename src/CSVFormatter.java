import java.util.IllegalFormatException;

/**
 * User: jeffreymeyerson
 * Date: 11/3/13
 * Time: 11:55 AM
 */
public class CSVFormatter {

    public static Object cleanEntry(Field field, String att) {
        try{
            switch(field.fieldType){
                case ID:
                    return att;
                case TIME:
                    return formatTime(att);
                case CAT:
                    return att;
                case CAT_SENSITIVE:
                    return att;
                default:
                    return formatCont(att);
            }
        }
        catch(IllegalFormatException e){
            return "NIL";
        }
    }

    private static Object formatCont(String att) throws IllegalFormatException{
        return att;
    }

    private static Object formatTime(String att) throws IllegalFormatException {
        return att;
    }
}
