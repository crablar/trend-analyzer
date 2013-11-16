import java.io.*;

/**
 * User: jeffreymeyerson
 * Date: 11/5/13
 * Time: 7:02 PM
 */
public class Logger {

    Writer writer;

    public Logger() throws IOException {
        File f = new File ("/Users/jeffreymeyerson/Documents/workspace/IdeaProjects/TrendAnalyzer/src/output.log");
        FileWriter fw = new FileWriter(f.getAbsoluteFile());
        writer = new BufferedWriter(fw);
    }

    public void out(String msg) throws IOException {
        System.out.println(msg);
        writer.write(msg);
        writer.close();
    }

}