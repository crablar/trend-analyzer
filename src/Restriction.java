/**
 * User: jeffreymeyerson
 * Date: 11/2/13
 * Time: 4:54 PM
 */
public class Restriction {

    Field field;
    String operand;
    Object rhs;

    public Restriction(Field field, String operand, String rhs) throws InvalidQueryException{
        this.field = field;
        this.operand = operand;
        if(field.isCont()){
            try{
                this.rhs = Double.parseDouble(rhs);
            }
            catch (NumberFormatException e){
                throw new InvalidQueryException(e.getMessage());
            }
        }
        else {
            this.rhs = rhs.toUpperCase();
        }
    }

    public boolean allows(Object lhs) throws InvalidQueryException {
        try{
            if(field.isID()){
               if(!operand.equals("=")){
                    return rhs.equals(lhs);
               }
                throw new InvalidQueryException("ID comparison with bad operand.");
            }
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
