import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * User: jeffreymeyerson
 * Date: 11/19/13
 * Time: 9:52 AM
 */

public class Results {

    List<Entity> resultSet;
    private boolean blank;

    public Results(List<Entity> resultSet){
        this.resultSet = resultSet;
        blank = true;
    }

    public Results() {
        resultSet = new ArrayList<Entity>();
        blank = true;
    }

    public void refine(Query query) throws InvalidQueryException {
        for(Entity entity : resultSet){
            Object entityAttribute = entity.getAttribute(query.selectedField);
            if(!query.restriction.allows(entityAttribute)){
                resultSet.remove(entity);
            }
        }
    }

    public String toString(){
        return Utilities.formatGridToString(Utilities.toStringGrid(resultSet));
    }

    public boolean isBlank() {
        return blank;
    }

    public double[][] getTuples(Field xAxis, Field yAxis) {
        double[][] result = new double[resultSet.size()][2];
        for(int i = 0; i < resultSet.size(); i++){
            double x = (Double)resultSet.get(i).getAttribute(xAxis);
            double y = (Double)resultSet.get(i).getAttribute(yAxis);
            result[i][0] = x;
            result[i][1] = y;
        }
        return result;
    }
}
