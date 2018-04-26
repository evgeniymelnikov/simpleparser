package parse.model;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import java.util.Comparator;

/**
 * Entity for xml parsing
 */
public class Step implements Serializable {
	
	private int number;
	private String operationClassName;
	private String input;
	private String output;
	
	/*
	 * Comparator to sorting ArrayList<Step> by step number
	 */
	public static final Comparator<Step> STEP_BY_ID = new Comparator<Step>() {
		@Override
		public int compare(Step o1, Step o2) {
			return o1.getNumber() - o2.getNumber();
		}
	};
	
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
	
	@XmlElement(name = "class") //связываем переменную operationClassName с элементом class в xml
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
