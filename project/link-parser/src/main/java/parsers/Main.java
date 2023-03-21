package parsers;

import java.net.MalformedURLException;

public class Main {

	public static void main(String[] args) throws MalformedURLException {
		LinkParser parser = new LinkParser();
		System.out.println(parser.parse("https://github.com/sanyarnd/tinkoff-java-course-2022/")); // (sanyarnd, tinkoff-java-course-2022)
		System.out.println(parser.parse("https://stackoverflow.com/questions/1642028/what-is-the-operator-in-c/")); // 1642028
		System.out.println(parser.parse("https://stackoverflow.com/search?q=unsupported%20link")); // null
		System.out.println(parser.parse("https://google.com")); // null

	}

}
