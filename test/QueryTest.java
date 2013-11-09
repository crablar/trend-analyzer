import org.junit.Test;

import java.util.List;

/**
 * User: jeffreymeyerson
 * Date: 11/2/13
 * Time: 5:13 PM
 */
public class QueryTest {

    Logger logger;

    @org.junit.Before
    public void setUp() throws Exception {
        Initializer.init("src/config/hospital");
        logger = new Logger();
    }

    @Test
    public void testQuery() throws Exception{
        Query query = Queries.simpleQuery("Hospital Name = Southeast Alabama Medical Center");
        List<Entity> results = DataStore.getResults(query);
        logger.out(results.toString());
    }

}
