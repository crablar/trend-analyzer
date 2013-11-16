import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * User: jeffreymeyerson
 * Date: 11/2/13
 * Time: 5:13 PM
 */
public class QueryTest {

    Logger logger;

    @Before
    public void setUp() throws Exception {
        if(DataStore.columns == null){
            Initializer.init("src/config/hospital");
        }
        logger = new Logger();
    }

    @Test
    public void testCat1() throws Exception{
        Query query = QueryUtil.simpleQuery("Hospital Name = Southeast Alabama Medical Center");
        List<Entity> results = DataStore.getResults(query);
        logger.out(Utilities.formatGridToString(Utilities.toStringGrid(results)));
    }

    @Test
    public void testCont1() throws Exception{
        Query query = QueryUtil.simpleQuery("Survey Response Rate Percent > 50");
        List<Entity> results = DataStore.getResults(query);
        logger.out(Utilities.formatGridToString(Utilities.toStringGrid(results)));
    }

    @Test
    public void testCont2() throws Exception{
        Query query = QueryUtil.simpleQuery("Percent of patients who reported that their nurses Usually communicated well. > 0");
        List<Entity> results = DataStore.getResults(query);
        logger.out(Utilities.formatGridToString(Utilities.toStringGrid(results)));
    }

}
