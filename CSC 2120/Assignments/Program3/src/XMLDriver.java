public class XMLDriver {
	private static Keyboard keyboard = Keyboard.getKeyboard();

	public static void main(String[] args) {
		XMLController controller;
		String result = keyboard
				.readString("Please enter in the xml file you wish to use: ");
		while (!result.equalsIgnoreCase("q")) {
			controller = new XMLController(result);
			controller.readFile();
			result = keyboard.readString("Enter in the output filename: ");
			controller.writeFile(result);
		}
	}
}
