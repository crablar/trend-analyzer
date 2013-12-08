package chart;

import singleton.Configuration;

import java.io.*;

/**
 * User: jeffreymeyerson
 * Date: 11/21/13
 * Time: 9:14 AM
 */
public abstract class ChartMaker {

	protected static File copyTemplate(String plotName, String templateName) throws IOException{

		File template = new File(Configuration.projectPath + "/visualization/templates/" + templateName + ".html");
        File scatterPlot = new File(Configuration.projectPath + "/visualization/" + templateName + "/" + plotName.toLowerCase() + ".html");

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
        return scatterPlot;
    }

}
