import com.sun.javaws.exceptions.InvalidArgumentException;

import java.io.*;
import java.util.Scanner;

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
        File plotTemplate = copyTemplate(xAxis.name + "_" + yAxis.name, "scatter");
        String[][] xyTuples = results.getTuples(xAxis, yAxis);
        StringBuilder tupleString = new StringBuilder("");
        for(int i = 0; i < xyTuples.length; i++){
            tupleString.append("[" + xyTuples[i][0] + "," + xyTuples[i][1] + "],");
        }
        tupleString.deleteCharAt(tupleString.length() - 1);
        Scanner scan = new Scanner(plotTemplate);
        StringBuilder fileString = new StringBuilder("");
        String line;
        while(scan.hasNextLine()){
            line = scan.nextLine();
            if(line.contains("$F_1_PAIRS")){
                line = line.replace("$F_1_PAIRS", tupleString.toString());
                System.out.println(line);
            }
            fileString.append(line + "\n");
        }
        fileString.deleteCharAt(fileString.length() - 1);
        File jsPlot = new File(plotTemplate.toURI());
        jsPlot.createNewFile();
        FileWriter fileWriter = new FileWriter(jsPlot);
        fileWriter.write(fileString.toString());
        fileWriter.flush();
    }

	private static File copyTemplate(String plotName, String templateName) throws IOException{
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
        return scatterPlot;
    }

}
