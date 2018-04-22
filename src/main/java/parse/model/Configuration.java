package parse.model;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity for xml parsing
 */

@XmlRootElement(name="configuration")
@XmlAccessorType(XmlAccessType.FIELD)
public class Configuration {

    private String process_input;

    private String process_output;

    private String output_keys;

    @XmlElement(name="step")
    private ArrayList<Step> stepList;

    public Configuration() {
    }


    public Configuration(String process_input, String process_output, String output_keys, ArrayList<Step> stepList) {
        this.process_input = process_input;
        this.process_output = process_output;
        this.output_keys = output_keys;
        this.stepList = stepList;
    }

    public String getProcess_input() {
        return process_input;
    }

    public void setProcess_input(String process_input) {
        this.process_input = process_input;
    }

    public String getProcess_output() {
        return process_output;
    }

    public void setProcess_output(String process_output) {
        this.process_output = process_output;
    }

    public String getOutput_keys() {
        return output_keys;
    }

    public void setOutput_keys(String output_keys) {
        this.output_keys = output_keys;
    }

    public ArrayList<Step> getStepList() {
        return stepList;
    }

    public void setStepList(ArrayList<Step> stepList) {
        this.stepList = stepList;
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "process_input='" + process_input + '\'' +
                ", process_output='" + process_output + '\'' +
                ", output_keys='" + output_keys + '\''+
                ", stepList=" + stepList +
                '}';
    }
}
