package process.definitions;

public class Difference extends ADefinitions implements x1, x2 {
	public double doOperation() {
		return (map.get("x1") - map.get("x2"));
	}
}
