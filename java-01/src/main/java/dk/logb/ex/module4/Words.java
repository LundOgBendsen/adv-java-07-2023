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
        System.out.println(allWords.size());

        //1. tæl antal ord
        HashSet<String> strings = new HashSet<>(allWords);

        //2. tæl antal unikke ord
        System.out.println("unikke ord: " + strings.size());

        //3. find det længste ord
        int longest=0;
        String s=null;
        for (String string : strings) {
            if (string.length() > longest) {
                longest=string.length();
                s=string;
            }
        }
        System.out.println("længste ord: " + longest +", " + s);


        //5. sorter (unikke) ordene efter længde

        TreeSet<String> set = new TreeSet<>(strings);
        System.out.println(set);


        //4. sorter (alle) ordene alfabetisk
        Collections.sort(allWords, new MyComparator());
        System.out.println(allWords);

        //6. tæl hvor mange gange hvert ord forekommer (ord -> antal)
        HashMap<String, Integer> occ = new HashMap<>();

        for (String word: allWords) {
            if (occ.containsKey(word)) {
                Integer i = occ.get(word);
                occ.put(word, ++i);
            } else {
                occ.put(word, 1);
            }
        }
        System.out.println(occ);

        Set<Map.Entry<String, Integer>> entries = occ.entrySet();
        ArrayList<Map.Entry<String, Integer>> entriesAsList = new ArrayList<>(entries);

        //Collections.sort(entriesAsList, new EntryComparator());
        //o1.getValue()-o2.getValue()

        Collections.sort(entriesAsList, (o1, o2) -> o1.getValue()-o2.getValue());


        System.out.println("----------------------------xxxxxxx--------------------");
        System.out.println(entriesAsList);


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

class MyComparator implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        return o1.length()-o2.length();
    }
}

class EntryComparator implements Comparator<Map.Entry<String, Integer>> {

    @Override
    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
        return o1.getValue()-o2.getValue();
    }
}


