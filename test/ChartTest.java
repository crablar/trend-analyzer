import chart.BarChartMaker;
import chart.ScatterPlotMaker;
import org.junit.Before;
import org.junit.Test;
import pojos.Field;
import pojos.Query;
import pojos.Results;
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

    @Before
    public void setUp() throws Exception{
        Configuration.configName = "airsampling";
        Initializer.init();
    }

    @Test
    public void testScatter() throws Exception{
        Query query = QueryUtil.simpleQuery("Latitude > 20");
        Results results = DataStore.getResults(query);
        Field x = Field.getFieldForString("LATITUDE");
        Field y = Field.getFieldForString("LONGITUDE");
        ScatterPlotMaker.makeChart(results, x, y);
    }

    @Test
    public void testBar() throws Exception{
        Query query = QueryUtil.simpleQuery("Latitude > 20");
        Results results = DataStore.getResults(query);
        Field cont = Field.getFieldForString("LATITUDE");
        Field cat = Field.getFieldForString("LOCATION");
        BarChartMaker.makeChart(results, cont, cat);
    }

}
