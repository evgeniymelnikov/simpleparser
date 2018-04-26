package parse.split;

import process.core.InputData;
import process.core.OutputData;

import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SplitStepInOut {
	
	/**Splits input, output strings of particular step. Will be used in for loop (in which steps will be processing step by step).
	 * @param input   - for ex., ac:x1,const4:x2
	 * @param yValues - for ex., y:4ac
	 */
	public static void splitToMap(String input, String yValues) {
		
		Map<String, String> mapIn = Pattern.compile("\\s*,\\s*")
				.splitAsStream(input.trim())
				.map(s -> s.split("\\s*:\\s*", 2))
				.collect(Collectors.toMap(
						a -> {
							for (String s : a) {
								if (s.matches("^(x|X)\\d*$"))
									return s;
							}
							return null;
						},
						a -> {
							for (String s : a) {
								if (!s.matches("^(x|X)\\d*$"))
									return s;
							}
							return null;
						}));
		
		InputData.inputDataContainer.add(new InputData(mapIn));
		
		Map<String, String> mapYValues = Pattern.compile("\\s*,\\s*")
				.splitAsStream(yValues.trim())
				.map(s -> s.split("\\s*:\\s*", 2))
				.collect(Collectors.toMap(
						a -> {
							for (String s : a) {
								if (s.matches("^(y|Y)\\d*$"))
									return s;
							}
							return null;
						},
						a -> {
							for (String s : a) {
								if (!s.matches("^(y|Y)\\d*$"))
									return s;
							}
							return null;
						}));
		
		
		OutputData.outputDataContainer.add(new OutputData(mapYValues));
		
	}
	
}
