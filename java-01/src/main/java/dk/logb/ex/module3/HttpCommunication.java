package dk.logb.ex.module3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.HttpURLConnection;

public class HttpCommunication {

    public String readHttpResource(String urlString) throws IOException {
        StringBuilder content = new StringBuilder();

        URL url = null;
        url = new URL(urlString);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Set request method (GET by default)
        connection.setRequestMethod("POST");

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

    public static void main(String[] args) {
        String urlString = "https://www.atp.dk"; // Replace with the actual URL
        try {
            String content = new HttpCommunication().readHttpResource(urlString);
            System.out.println("Content of " + urlString + ":\n" + content);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Shutting Down", e);
        }
    }
}



