package parse.model;

import javax.xml.bind.annotation.XmlElement;



public class Step {

    private int number;
    private String operationClassName;
    private String input;
    private String output;

    public Step() {
    }


    public Step(int number, String operationClassName, String input, String output) {
        this.number = number;
        this.operationClassName = operationClassName;
        this.input = input;
        this.output = output;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getOperationClassName() {
        return operationClassName;
    }

    @XmlElement(name="class") //связываем переменную operationClassName с элементом class в xml
    public void setOperationClassName(String operationClassName) {
        this.operationClassName = operationClassName;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    @Override
    public String toString() {
        return "Step{" +
                "number=" + number +
                ", operationClassName='" + operationClassName + '\'' +
                ", input='" + input + '\'' +
                ", output='" + output + '\'' +
                '}';
    }
}
