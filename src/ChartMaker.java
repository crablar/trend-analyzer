import com.sun.javaws.exceptions.InvalidArgumentException;

import java.io.*;

/**
 * User: jeffreymeyerson
 * Date: 11/21/13
 * Time: 9:14 AM
 */
public class ChartMaker {

    public static void scatterPlot(Results results, Field xAxis, Field yAxis) throws Exception{
        if(xAxis.isCat() || xAxis.isID() || yAxis.isCat() || yAxis.isID()){
            throw new IllegalArgumentException("Cannot make scatter plot " +
                "with fields: " + xAxis + ", " + yAxis);
        }
        copyTemplate(xAxis.name + "_" + yAxis.name, "scatter");
        double[][] xyTuples = results.getTuples(xAxis, yAxis);
    }

	private static void copyTemplate(String plotName, String templateName) throws IOException{
		File template = new File(Configuration.projectPath + "visualization/templates/" + templateName + ".html");
        File scatterPlot = new File(Configuration.projectPath + "visualization/" + templateName + "/" + plotName.toLowerCase() + ".html");

        if(!scatterPlot.exists()){
            scatterPlot.createNewFile();
        }
        InputStream inStream = new FileInputStream(template);
        OutputStream outStream = new FileOutputStream(scatterPlot);

		byte[] buffer = new byte[1024];

		int length;

		while((length = inStream.read(buffer)) > 0){
			outStream.write(buffer, 0, length);
		}

		inStream.close();
		outStream.close();
    }

}
