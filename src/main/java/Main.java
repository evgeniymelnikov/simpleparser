import parse.ParserInitialization;
import parse.model.Configuration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        ParserInitialization parser = new ParserInitialization();
        Configuration configuration = parser.fromXmlToObject(parser.filePath);
        System.out.println(configuration.toString());

        String stringForWriteInTxt = "jfjfjfj";
        String stringForWriteInTxt2 = "121113";
        File file = new File("output/output.txt");

        /* try(FileWriter writer = new FileWriter("D:/test/output.txt", true))
        {
            writer.write(StringForWriteInTxt2);
            writer.write(System.getProperty("line.separator "));
            // запись по символам
            writer.write(stringForWriteInTxt);

            writer.flush();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        } */

        PrintWriter pw = new PrintWriter(file);
        pw.print("");
        pw.println(stringForWriteInTxt);
        pw.println(stringForWriteInTxt2);
        pw.close();
    }
}
