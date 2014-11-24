public class XMLParseException extends RuntimeException {
	public XMLParseException() {
		this("Parsing Exception");
	}

	public XMLParseException(String s) {
		super(s);
	}
}
