import chart.ChartMaker;
import chart.ScatterPlotMaker;
import org.junit.Before;
import org.junit.Test;
import pojo.Field;
import pojo.Logger;
import pojo.Query;
import pojo.Results;
import singleton.Configuration;
import singleton.DataStore;
import singleton.Initializer;
import singleton.QueryUtil;

/**
 * User: jeffreymeyerson
 * Date: 11/17/13
 * Time: 9:20 AM
 */
public class ChartTest {

    static Logger logger;

    @Before
    public void setUp() throws Exception{
        Configuration.configName = "airsampling";
        Initializer.init();
        logger = new Logger();
    }

    @Test
    public void testChart() throws Exception{
        Query query = QueryUtil.simpleQuery("Latitude > 20");
        Results results = DataStore.getResults(query);
        Field x = Field.getFieldForString("LATITUDE");
        Field y = Field.getFieldForString("LONGITUDE");
        ScatterPlotMaker.makeChart(results, x, y);
    }

}
