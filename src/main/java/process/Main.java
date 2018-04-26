package process;

import process.core.ProcessRunner;

public class Main {
	
	
	public static void main(String[] args) throws Exception {
		
		ProcessRunner processRunner = new ProcessRunner();
		processRunner.configurationReading();
		processRunner.stepProcessing();
		processRunner.writingResultToFile();
	}
}
