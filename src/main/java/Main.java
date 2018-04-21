import parse.ParserInitialization;
import parse.model.Configuration;
import parse.split.SplitStepInOut;
import process.core.ProcessData;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws Exception {

        //parse (method realization in package parse (class ParserInitialization)
        ParserInitialization parser = new ParserInitialization();
        Configuration configuration = parser.fromXmlToObject(parser.filePath);

        //data from input.properties (namely a, const4, etc)
        //ProcessData has map (ProcessDataContainer)
        ProcessData processData = new ProcessData();
        String nameOfInputFile = configuration.getProcess_input();
        FileInputStream fis;
        fis = new FileInputStream("src/main/resources/"+nameOfInputFile);
        Properties properties = new Properties();
        properties.load(fis);
        fis.close();
        for (String name: properties.stringPropertyNames()) {
            processData.processDataContainer.put(name, properties.getProperty(name));}
        //System.out.println(processData.processDataContainer);

        SplitStepInOut splitStepInOut = new SplitStepInOut();
        System.out.print(splitStepInOut.xToMap(configuration.getStepList().get(0)));

        //In the final, we will write down the results using this approach
        String stringForWriteInTxt = "jfjfjfj";
        String stringForWriteInTxt2 = "121113";
        File file = new File("output/output.txt");

        PrintWriter pw = new PrintWriter(file);
        pw.print("");
        pw.println(stringForWriteInTxt);
        pw.println(stringForWriteInTxt2);
        pw.close();
    }
}
