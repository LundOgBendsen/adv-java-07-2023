package dk.logb.ex.module4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class Words {
    public static void main(String[] args) throws IOException {
        List<String> allWords = getAllWords("https://www.ietf.org/rfc/rfc2616.txt");

        //1. tæl antal ord
        //2. tæl antal unikke ord
        //3. find det længste ord
        //4. sorter (alle) ordene alfabetisk
        //5. sorter (unikke) ordene efter længde
        //6. tæl hvor mange gange hvert ord forekommer (ord -> antal)
        //7. udskriv forekomsterne, så de er sorteret efter antal, faldende
    }


    static List<String> getAllWords(String url) throws IOException {
        String content = readHttpResource(url);
        String[] words = content.split("\\s+|\\n|\\.");
        return Arrays.asList(words);
    }


    public static String readHttpResource(String urlString) throws IOException {
        StringBuilder content = new StringBuilder();

        URL url = null;
        url = new URL(urlString);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Set request method (GET by default)
        //connection.setRequestMethod("POST");

        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line);
                    content.append("\n");
                }
            }
        } else {
            throw new IOException("HTTP request failed with response code: " + responseCode);
        }

        connection.disconnect();
        return content.toString();
    }
}
