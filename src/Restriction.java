/**
 * User: jeffreymeyerson
 * Date: 11/2/13
 * Time: 4:54 PM
 */
public class Restriction {

    Field field;
    String operand;
    Object rhs;

    public Restriction(Field field, String operand, String rhs) {
        this.field = field;
        this.operand = operand;
        if(field.isCont()){
            try{
                this.rhs = Double.parseDouble(rhs);
            }
            catch (NumberFormatException e){
                e.printStackTrace();
            }
        }
        if(field.isID()){
            this.rhs = rhs.toUpperCase();
        }
    }

    public boolean allows(Object lhs) {
        if(field.isID()){
            return rhs.equals(lhs);
        }
        try{
            if(field.isCont()){
                switch(operand){
                    case("<"):
                        return (Double)lhs < (Double)rhs;
                    case(">"):
                        return (Double)lhs > (Double)rhs;
                    case("="):
                        return lhs.equals(rhs);
                    default:
                        return false;
                }
            }
            else{
                return lhs.equals(rhs);
            }
        }
        catch(ClassCastException e){
            return false;
        }
    }
}
