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

    public Results(List<Entity> resultSet){
        this.resultSet = resultSet;
    }

    public Results() {
        resultSet = new ArrayList<Entity>();
    }

    public void refine(Query query){

    }

    public String toString(){
        return Utilities.formatGridToString(Utilities.toStringGrid(resultSet));
    }

}
