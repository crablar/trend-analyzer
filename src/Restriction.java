/**
 * Created with IntelliJ IDEA.
 * User: jeffreymeyerson
 * Date: 11/2/13
 * Time: 4:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class Restriction {

    Field restrictedField;

    public Restriction(Field restrictedField, String s) {
        this.restrictedField = restrictedField;
        String[] arr = s.split(" ");
    }

    public boolean allows(Object key) {
        return true;
    }
}
