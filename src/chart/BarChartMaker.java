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
 * Time: 8:41 AM
 */
public class BarChartMaker extends ChartMaker{

    public static void makeChart(Results results, Field contField, Field catField) throws Exception{
        if(!contField.isCont() || catField.isCont()){
            throw new IllegalArgumentException("Cannot make bar chart with fields: " +
                contField + "," + catField);
        }
        File plotTemplate = copyTemplate(contField.name + "_" + catField.name, "bar");
        String[][] strTuples = results.getTuples(contField, catField);
        StringBuilder contString = new StringBuilder("");
        StringBuilder catString = new StringBuilder("");
        for(int i = 0; i < strTuples.length; i++){
            contString.append(strTuples[i][0] + ",");
            catString.append("'" + strTuples[i][1] + "',");
        }
        contString.deleteCharAt(contString.length() - 1);
        catString.deleteCharAt(catString.length() - 1);
        Scanner scan = new Scanner(plotTemplate);
        StringBuilder fileString = new StringBuilder("");
        String line;
        while(scan.hasNextLine()){
            line = scan.nextLine();
            line = line.replace("$FIELD_CONT", contField.name);
            line = line.replace("$FIELD_CAT", catField.name);
            line = line.replace("$SOURCE", Configuration.configName);
            line = line.replace("$CATS", catString);
            line = line.replace("$CONTS", contString);
            fileString.append(line + "\n");
        }
        fileString.deleteCharAt(fileString.length() - 1);
        File jsPlot = new File(plotTemplate.toURI());
        jsPlot.createNewFile();
        FileWriter fileWriter = new FileWriter(jsPlot);
        fileWriter.write(fileString.toString());
        fileWriter.flush();
    }

}
