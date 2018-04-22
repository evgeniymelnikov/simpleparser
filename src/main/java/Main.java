import parse.ParserInitialization;
import parse.model.Configuration;
import parse.model.Step;
import parse.split.SplitStepInOut;
import process.core.InputData;
import process.core.OutputData;
import process.core.ProcessData;
import process.core.ProcessStep;

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
        for (String name : properties.stringPropertyNames()) {
            processData.processDataContainer.put(name, properties.getProperty(name));}
        System.out.println(processData.processDataContainer);

        SplitStepInOut splitStepInOut = new SplitStepInOut();



        OutputData outputData = new OutputData();
        for(Step i : configuration.getStepList()){
            outputData.getOutputDataContainer().add(splitStepInOut.outToMap(i));
        }
        System.out.println(outputData.getOutputDataContainer());

        InputData inputData = new InputData();

        ProcessStep processStep = new ProcessStep(configuration.getStepList(),inputData, processData, inputData.getInputDataContainer(), outputData.getOutputDataContainer(), processData.processDataContainer);
        //configuration.getStepList().size()
        System.out.println("\n");
        for(int i = 0; i < configuration.getStepList().size(); i++) {
            processStep.setStepNumber(i);
            inputData.getInputDataContainer().putAll(splitStepInOut.xToMap(configuration.getStepList().get(i)));
            processStep.perform();

        }

        System.out.println(processData.processDataContainer);
        System.out.println(processData.processDataContainer.get("constant4"));
        System.out.println(inputData.getInputDataContainer());
        //System.out.println(processData.processDataContainer);

        //In the final, we will write down the results using this approach
        String result1 = processData.processDataContainer.get("result1");
        String result2 = processData.processDataContainer.get("result2");
        File file = new File("output/output.txt");

        //String tes = "-15";
        //System.out.println(Integer.parseInt(tes));

        PrintWriter pw = new PrintWriter(file);
        pw.print("");
        pw.println(result1);
        pw.println(result2);
        pw.close();
    }
}
