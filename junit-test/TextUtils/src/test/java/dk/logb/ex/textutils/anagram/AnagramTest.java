package dk.logb.ex.textutils.anagram;

import dk.logb.ex.textutils.TextUtils;
import dk.logb.ex.textutils.Vocabulary;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.TreeSet;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnagramTest {
    private static Vocabulary mock;
    private TextUtils textUtils;


    @BeforeAll
    public static void setup() {
        mock = new MockVocabulary();
    }

    @BeforeEach
    public void setupEach() {
        textUtils = new TextUtils(mock);
    }

    //test isAnagram()
    @Test
    public void testIsAnagramWithCustomMock() {
        //test with an anagram
        assertTrue(textUtils.isAnagram("nana"));
        assertTrue(textUtils.isAnagram("nelle"));
        assertTrue(textUtils.isAnagram("ale"));

        assertFalse(textUtils.isAnagram("jack"));
        assertFalse(textUtils.isAnagram("ennio"));
        assertFalse(textUtils.isAnagram("umberto"));
    }

    @Test
    public void testIsAnagramWithEmptyMock() {
        Vocabulary mock = createEmptyVocabularyMock();
        TextUtils textUtils = new TextUtils(mock);

        //all false
        assertFalse(textUtils.isAnagram("nana"));
        assertFalse(textUtils.isAnagram("nelle"));
        assertFalse(textUtils.isAnagram("ale"));
        assertFalse(textUtils.isAnagram("jack"));
        assertFalse(textUtils.isAnagram("ennio"));
        assertFalse(textUtils.isAnagram("umberto"));
    }

    @Test
    public void testIsAnagramWithMockitoMock() {
        Vocabulary mock = createVocabularyMock();
        TextUtils textUtils = new TextUtils(mock);

        //test with an anagram
        assertTrue(textUtils.isAnagram("alice"));
        assertTrue(textUtils.isAnagram("abe"));
        assertTrue(textUtils.isAnagram("eba"));

        assertFalse(textUtils.isAnagram("jack"));
        assertFalse(textUtils.isAnagram("cathe"));
        assertFalse(textUtils.isAnagram("dor"));
    }


    private Vocabulary createEmptyVocabularyMock() {
        Vocabulary mock = mock(Vocabulary.class);
        when(mock.getWords()).thenReturn(new TreeSet<>()); //empty set
        return mock;
    }

    private Vocabulary createVocabularyMock() {
        Vocabulary mock = mock(Vocabulary.class);
        when(mock.getWords()).thenReturn(Set.of("alice", "bea", "cath", "dora"));
        return mock;
    }
}
