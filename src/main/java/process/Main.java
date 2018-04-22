package process;

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
import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws Exception {

        /*
         * @see ParserInitialization;
         */
        ParserInitialization parser = new ParserInitialization();
        Configuration configuration = parser.fromXmlToObject(parser.filePath);


        /*
         * data from input.properties (namely a, const4, etc). Will be used as process data container.
         */
        ProcessData processData = new ProcessData();
        String nameOfInputFile = configuration.getProcess_input();
        FileInputStream fis;
        fis = new FileInputStream("src/main/resources/"+nameOfInputFile);
        Properties properties = new Properties();
        properties.load(fis);
        fis.close();
        for (String name : properties.stringPropertyNames()) {
            processData.getProcessDataContainer().put(name, properties.getProperty(name));}
        //System.out.println(processData.getProcessDataContainer());

        /*
         * Sort by step number. Just in case.
         */
        Collections.sort(configuration.getStepList(), Step.STEP_BY_ID);
        //System.out.println(configuration.getStepList()+"\n"+"\n");

        SplitStepInOut splitStepInOut = new SplitStepInOut();

        OutputData outputData = new OutputData();
        for(Step i : configuration.getStepList()){
            outputData.getOutputDataContainer().add(splitStepInOut.outToMap(i));
        }
        //System.out.println(outputData.getOutputDataContainer());

        InputData inputData = new InputData();

        /*
         * An instance of the class that will be used to process the data at each step.
         */
        ProcessStep processStep = new ProcessStep(configuration.getStepList(),inputData, processData, inputData.getInputDataContainer(), outputData.getOutputDataContainer(), processData.getProcessDataContainer());


        /*
         * A loop where processing of data is carried out step by step
         */
        for(int i = 0; i < configuration.getStepList().size(); i++) {
            processStep.setStepNumber(i);
            inputData.getInputDataContainer().putAll(splitStepInOut.xToMap(configuration.getStepList().get(i)));
            processStep.perform();

        }

        /*System.out.println(processData.getProcessDataContainer());
        System.out.println(processData.getProcessDataContainer().get("squarerootdiscriminant"));
        System.out.println(inputData.getInputDataContainer());*/


        /*
         * writing results to a file
         */
        String result1 = processData.getProcessDataContainer().get("result1");
        String result2 = processData.getProcessDataContainer().get("result2");
        File folder = new File("output/");
        if (!folder.exists()) {
            folder.mkdir();
        }
        File file = new File("output/output.txt");

        PrintWriter pw = new PrintWriter(file);
        pw.print("");
        pw.println("result1=" + result1);
        pw.println("result2=" + result2);
        pw.close();
    }
}
