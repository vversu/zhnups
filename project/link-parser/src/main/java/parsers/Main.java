package parsers;

import java.net.MalformedURLException;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws MalformedURLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the link:");
        String url = scanner.nextLine();

        LinkParser parser = new LinkParser();
        Object result = parser.parse(url);

        if (result == null) {
            System.out.println("Null");
        } else if (result instanceof Integer) {
            System.out.println("StackOverflow ID: " + result);
        } else if (result instanceof Map.Entry) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) result;
            System.out.println("User: " + entry.getKey() + ", Repo: " + entry.getValue());
        }
    }
}