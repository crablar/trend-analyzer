import java.io.*;

/**
 * User: jeffreymeyerson
 * Date: 11/5/13
 * Time: 7:02 PM
 */
public class Logger {

    Writer writer;
    final static File file = new File ("/Users/jeffreymeyerson/Documents/workspace/IdeaProjects/TrendAnalyzer/src/output.csv");

    public Logger() throws IOException {
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        writer = new BufferedWriter(fw);
    }

    public void out(String msg) throws IOException {
        writer.write(msg);
        System.out.println(msg);
        writer.close();
    }

    public void clearLog() throws IOException {
        writer = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
    }
}