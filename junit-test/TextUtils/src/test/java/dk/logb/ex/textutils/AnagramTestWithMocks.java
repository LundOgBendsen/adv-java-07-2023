package dk.logb.ex.textutils;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import static org.mockito.Mockito.when;

public class AnagramTestWithMocks {

    @Test
    void testSmokeTest() {
        boolean actual = new TextUtils().isAnagram("shal");
        assertTrue(actual);
        actual = new TextUtils().isAnagram("foog");
        assertTrue(actual);
    }

    @Test
    void testIsAnagramWith5WordsVocabulary() {
        TextUtils textUtils = new TextUtils(new MockVocWith5Words());
        boolean actual = textUtils.isAnagram("od");
        assertTrue(actual);
        actual = textUtils.isAnagram("pool");
        assertFalse(actual);
    }

    @Test
    void testIsAnagramWith5WordsVocabularyWithMockito() {
        Vocabulary mock = mock(Vocabulary.class);

        when(mock.getWords()).thenReturn(Set.of("do", "go", "of"));

        TextUtils textUtils = new TextUtils(mock);
        boolean act = textUtils.isAnagram("od");
        assertTrue(act);

        Comparator comparator = mock(Comparator.class);
        when(comparator.compare("hej", "ole")).thenReturn(1).thenReturn(-1).thenThrow(new NullPointerException());


        System.out.println(comparator.compare("hej", "ole"));  //first call: 1
        System.out.println(comparator.compare("hej", "ole"));  //second: -1
        assertThrows(NullPointerException.class, () ->
        System.out.println(comparator.compare("hej", "ole")));  //third: NPE thrown
    }
}

//handcrafted mock
class MockVocWith5Words implements Vocabulary {

    @Override
    public Set getWords() {
        return Set.of("is", "has", "do", "counterrevolutionaries", "shall");
    }
}
