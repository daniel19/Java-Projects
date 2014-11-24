public class XMLDriver {
	private static Keyboard keyboard = Keyboard.getKeyboard();

	public static void main(String[] args) {
		XMLController controller;
		String result = keyboard
				.readString("Please enter in the xml file you wish to use: ");
		while (!result.equalsIgnoreCase("q")) {
			controller = new XMLController(result);
			try{
                controller.readFile();
                result = keyboard.readString("Enter in the output filename: ");
                controller.writeFile(result);
                result = keyboard.readString("Please enter in the xml file you wish to use: ");
            }catch(XMLParseException|FileIOException e){
                System.out.println(e.getMessage());
                result = keyboard.readString("Please enter in the xml file you wish to use: ");
            }
        }
	}
}
