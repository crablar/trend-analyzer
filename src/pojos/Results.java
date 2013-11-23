package pojos;

import singleton.Utilities;

import java.util.ArrayList;
import java.util.List;

/**
 * User: jeffreymeyerson
 * Date: 11/19/13
 * Time: 9:52 AM
 */

public class Results {

    List<Entity> resultSet;

    public Results(List<Entity> resultSet){
        this.resultSet = resultSet;
    }

    public Results() {
        resultSet = new ArrayList<>();
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
        if(resultSet == null)
            return null;
        return Utilities.formatGridToString(Utilities.toStringGrid(resultSet));
    }

    public boolean isBlank() {
        return resultSet == null || resultSet.size() == 0;
    }

    public String[][] getTuples(Field field1, Field field2) {
        String[][] result = new String[resultSet.size()][2];
        for(int i = 0; i < resultSet.size(); i++){
            String x = "" + resultSet.get(i).getAttribute(field1);
            String y = "" + resultSet.get(i).getAttribute(field2);
            result[i][0] = x;
            result[i][1] = y;
        }
        return result;
    }
}
