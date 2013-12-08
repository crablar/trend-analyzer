import org.junit.Before;
import org.junit.Test;
import pojos.Logger;
import pojos.Query;
import pojos.Results;
import singleton.*;

import static singleton.Configuration.*;

/**
 * User: jeffreymeyerson
 * Date: 11/2/13
 * Time: 5:13 PM
 */
public class HospitalTest {

    static Logger logger;

    @Before
    public void setUp() throws Exception{
        configName = "hospital";
        if(DataStore.columns == null){
            Initializer.init();
        }
        logger = new Logger();
    }

    @Test
    public void testQueryCat1() throws Exception{
        Query query = QueryUtil.simpleQuery("Hospital Name = Southeast Alabama Medical Center");
        Results results = DataStore.getResults(query);
        logger.writeResults(results.toString());
    }

    @Test
    public void testQueryCat2() throws Exception{
        Query query = QueryUtil.simpleQuery("Hospital Name != Southeast Alabama Medical Center");
        Results results = DataStore.getResults(query);
        logger.writeResults(results.toString());
    }

    @Test
    public void testQueryCont() throws Exception{
        Query query = QueryUtil.simpleQuery("Survey Response Rate Percent > 50");
        Results results = DataStore.getResults(query);
        logger.writeResults(results.toString());
    }

    @Test
    public void testAliasQuery() throws Exception{
        Query query = QueryUtil.simpleQuery("PCNT THE AREA AROUND THEIR ROOM WAS SOMETIMES OR NEVER QUIET AT NIGHT. > 0");
        Results results = DataStore.getResults(query);
        logger.writeResults(results.toString());
    }


}
