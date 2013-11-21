import org.junit.Before;
import org.junit.Test;

/**
 * User: jeffreymeyerson
 * Date: 11/17/13
 * Time: 9:20 AM
 */
public class ChartTest {

    static Logger logger;

    @Before
    public void setUp() throws Exception{
        if(DataStore.columns == null){
            Initializer.init();
        }
        logger = new Logger();
    }

    @Test
    public void testChart() throws Exception{
        Query query = QueryUtil.simpleQuery("Latitude > 0");
        Results results = DataStore.getResults(query);
        Field x = Field.getFieldForString("LATITUDE");
        Field y = Field.getFieldForString("LONGITUDE");
        ChartMaker.scatterPlot(results, x, y);
    }

}
