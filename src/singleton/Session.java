package singleton;

import chart.ScatterPlotMaker;
import pojo.*;

import java.util.List;
import java.util.Scanner;

/**
 * User: jeffreymeyerson
 * Date: 11/4/13
 * Time: 8:10 PM
 */

public class Session {

    static boolean hasIDInformation = false;
    static boolean hasSensitiveInformation = false;
    static Scanner in = new Scanner(System.in);
    static boolean finished = false;
    static Logger logger;
    static Results results = new Results();

    public static void main(String[] args) throws Exception {
        Configuration.configName = "hospital";
        Initializer.init();
        runSession();
    }

    public static void runSession() throws Exception {
        logger = new Logger();
        System.out.println("Welcome to TrendAnalyzer. Type 'commands' for help.\nBrowsing database: " + Configuration.configName);
        while(!finished){
            System.out.print("Enter query: ");
            String input = in.nextLine().toUpperCase();
            Query query;
            try {
                if(!doCommand(input)) {
                    query = QueryUtil.simpleQuery(input);
                    if(results.isBlank()){
                        results = DataStore.getResults(query);
                    }
                    else{
                        results.refine(query);
                    }
                }
            }
            catch (InvalidQueryException e) {
                System.out.println(e.reason);
                continue;
            }
            logger.clearLog();
            logger.writeResults(results.toString());
        }
    }

    private static boolean doCommand(String input) throws InvalidQueryException {
        if(input.contains(" ")){
            return complexCommand(input);
        }
        switch(input){
            case("PRINT"):
                if(results.isBlank()){
                    System.out.println("No results loaded");
                }
                else{
                    System.out.println(results.toString());
                }
                return true;
            case("CLEAR"):
                results = new Results();
                return true;
            case("LIST"):
                Utilities.printAllFields();
                return true;
            case("COMMANDS"):
                Utilities.printCommandDescriptions();
                return true;
            default:
                return false;
        }
    }

    private static boolean complexCommand(String input) throws InvalidQueryException {
        try{
            if(input.startsWith("LIST FIELDS")){
                String[] inputArr = input.split(" ", 3);
                if(inputArr.length > 2)  {
                    printFields(inputArr[2]);
                    return true;
                }
            }
            else if(input.startsWith("GRAPH")){
                if(results.isBlank()){
                    throw new InvalidQueryException("Can't graph empty result set.");
                }
                String fieldStr = input.split(" ", 3)[2];
                String[] fieldNames = fieldStr.split("&");
                Field field1 = Field.getFieldForString(fieldNames[0]);
                Field field2 = Field.getFieldForString(fieldNames[1]) ;
                ScatterPlotMaker.makeChart(results, field1, field2);
                return true;
            }
            return false;
        }
        catch(Exception e){
            if(e instanceof InvalidQueryException){
                throw (InvalidQueryException)e;
            }
            throw new InvalidQueryException("Problem with command: " + input);
        }
    }

    private static void printFields(String fieldName) throws InvalidQueryException {
        Field field = Field.getFieldForString(fieldName);
        if(field != null){
            List<String> fieldEntries = DataStore.getAllFieldEntries(field);
            for(String ent : fieldEntries){
                System.out.println(ent);
            }
        }
        else{
            throw new InvalidQueryException();
        }
    }

}
