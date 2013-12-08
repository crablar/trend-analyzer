package chart;

import pojos.Field;
import pojos.Results;
import singleton.Configuration;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
/**
 * User: jeffreymeyerson
 * Date: 11/23/13
 * Time: 8:43 AM
 */
public class ScatterPlotMaker extends ChartMaker{

    public static void makeChart(Results results, Field xAxis, Field yAxis) throws Exception{

        if(!(xAxis.isCont() && yAxis.isCont())){
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
            line = line.replace("$PAIRS", tupleString.toString());
            line = line.replace("$SOURCE", Configuration.configName);
            line = line.replace("$FIELD_1", xAxis.name);
            line = line.replace("$FIELD_2", yAxis.name);
            fileString.append(line + "\n");
        }

        fileString.deleteCharAt(fileString.length() - 1);
        File jsPlot = new File(plotTemplate.toURI());
        jsPlot.createNewFile();
        FileWriter fileWriter = new FileWriter(jsPlot);
        fileWriter.write(fileString.toString());
        fileWriter.flush();
        System.out.println("Chart created in " + jsPlot.getAbsolutePath());
    }

}
