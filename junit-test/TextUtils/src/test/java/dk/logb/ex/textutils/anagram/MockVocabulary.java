package dk.logb.ex.textutils.anagram;


import dk.logb.ex.textutils.Vocabulary;

import java.util.Arrays;
import java.util.Set;

public class MockVocabulary implements Vocabulary {
    String words =  """
        anna adele lea lucille ellen elise sara jane 
        reed diana eva noel annette lily nora agnes
        ida flora irma otto eve lee naomi ann bob
    """;

    @Override
    public Set getWords() {
        return Arrays.stream(this.words.split(" "))
                .map(String::toLowerCase)
                .collect(java.util.stream.Collectors.toSet());
    }
}
