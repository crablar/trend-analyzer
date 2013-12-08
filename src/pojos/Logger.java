package pojos;

import singleton.Configuration;

import java.io.*;

/**
 * User: jeffreymeyerson
 * Date: 11/5/13
 * Time: 7:02 PM
 */
public class Logger {

    private Writer writer;
    private static File file;

    public Logger() throws IOException {
        file = new File(Configuration.projectPath + "/src/output.csv");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        writer = new BufferedWriter(fw);
    }

    public void writeResults(String msg) throws IOException {
        writer.write(msg);
        writer.close();
    }

    public void clearLog() throws IOException {
        writer = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
    }
}