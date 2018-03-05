package adriana.rincon.urlfinder;

import java.io.IOException;
import java.net.URL;;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by Adriana Rincon on 02/26/2018.
 */
public class UrlFinder {
    public static void main(String[] args) {
        if (args == null || args.length != 1) {
            System.out.println("Invalid number of arguments.\n" + usage());
            return;
        }

        parsePage(args[0]);
    }

    static String usage() {
        return "Usage: urlfinder <url>";
    }

    public static List<String> parsePage(String urlString) {
        // Validate URL
        URL url = null;
        try
        {
            url = new URL(urlString);
            url.toURI();
        } catch (Exception exception)
        {
            System.out.println(exception.toString());
            return null;
        }

        // Read url
        BufferedReader in = null;
        try {
            in = new BufferedReader(
                    new InputStreamReader(url.openStream()));
        } catch (IOException e) {
            System.out.println(e.toString());
            return null;
        }

        String inputLine;
        boolean done = false;
        List<String> urlsFound = new LinkedList<String>();
        while (!done) {
            try {
                inputLine = in.readLine();
            } catch (IOException e) {
                System.out.println(e.toString());
                return null;
            }
            if (inputLine == null){
                return urlsFound;
            } else {
                urlsFound.addAll(parseUrls(inputLine));
            }

        }
        try {
            in.close();
        } catch (IOException e) {
            System.out.println(e.toString());
            return urlsFound;
        }
        return urlsFound;
    }

    public static List<String> parseUrls(String inputLine)
    {
        String[] split = inputLine.split("<a href=\"");
        List<String> returnList = new LinkedList<String>();

        if (split.length > 1) {
            boolean isFirstToken = true;
            for (String temp : split) {
                // Do not print the first token.  It is either "" or it was not preceded by href
                if (isFirstToken ) {
                    isFirstToken = false;
                    continue;
                }
                int lastIndex = temp.indexOf("\"");
                if (lastIndex >= 0) {
                    String toPrint = temp.substring(0, lastIndex);
                    returnList.add(toPrint);
                    System.out.println(toPrint);
                }
            }
        }
        return returnList;
    }
}
