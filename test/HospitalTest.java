import org.junit.Before;
import org.junit.Test;
import pojo.Logger;
import pojo.Query;
import pojo.Results;
import singleton.Configuration;
import singleton.DataStore;
import singleton.Initializer;
import singleton.QueryUtil;

/**
 * User: jeffreymeyerson
 * Date: 11/2/13
 * Time: 5:13 PM
 */
public class HospitalTest {

    static Logger logger;

    @Before
    public void setUp() throws Exception{
        Configuration.configName = "hospital";
        if(DataStore.columns == null){
            Initializer.init();
        }
        logger = new Logger();
    }

    @Test
    public void testCat1() throws Exception{
        Query query = QueryUtil.simpleQuery("Hospital Name = Southeast Alabama Medical Center");
        Results results = DataStore.getResults(query);
        logger.writeResults(results.toString());
    }

    @Test
    public void testCat2() throws Exception{
        Query query = QueryUtil.simpleQuery("Hospital Name = Southeast Alabama Medical Center");
        Results results = DataStore.getResults(query);
        logger.writeResults(results.toString());
    }

    @Test
    public void testCont1() throws Exception{
        Query query = QueryUtil.simpleQuery("Survey Response Rate Percent > 50");
        Results results = DataStore.getResults(query);
        logger.writeResults(results.toString());
    }

    @Test
    public void testCont2() throws Exception{
        Query query = QueryUtil.simpleQuery("pcnt who reported that their nurses Usually communicated well. > 0");
        Results results = DataStore.getResults(query);
        logger.writeResults(results.toString());
    }


}
