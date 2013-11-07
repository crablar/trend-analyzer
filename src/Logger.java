import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * User: jeffreymeyerson
 * Date: 11/5/13
 * Time: 7:02 PM
 */
public class Logger {

    PrintWriter printWriter;

    public Logger() throws FileNotFoundException {
        printWriter = new PrintWriter("output.log");
    }

    public void out(String msg){
        printWriter.write(msg);
    }

}
