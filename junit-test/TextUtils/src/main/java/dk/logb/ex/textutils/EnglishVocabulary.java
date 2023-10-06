package dk.logb.ex.textutils;

import java.io.InputStream;
import java.util.Set;

public class EnglishVocabulary implements Vocabulary{
    private Set words;

    @Override
    public Set getWords() {
        if (words != null) {
            return words;
        }
        //load file from resources folder
        InputStream is = new TextUtils().getClass().getClassLoader().getResourceAsStream("corncob_lowercase.txt");
        //create a set of words
        words = new java.util.TreeSet<>();
        //read words from file
        try (java.util.Scanner s = new java.util.Scanner(is)) {
            while (s.hasNext()) {
                words.add(s.next().toLowerCase());
            }
        }
        return words;
    }

}
