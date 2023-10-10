import dk.logb.ex.textutils.TextUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LevenshteinDistanceTest {

    static TextUtils textUtils = null;

    @BeforeAll
    static void beforeAll() {
        textUtils = new TextUtils();
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("før hver");
    }

    @Test
    void testLevenshteinDistance() {
        int i = textUtils.levenshteinDistance("aaa", "aba");
        assertEquals(1, i);

        i = textUtils.levenshteinDistance("aaa", "abab");
        assertEquals(2, i);
    }

    @ParameterizedTest
    @CsvSource(value = {"hello|helo|1",
            "aloha|paloma|2",
            "circle|close|4",
            "edit|reddit|2",
            "tricks|rickshaw|4",
            "arabic|atrab|3",
            "arabic|arab|2",
            "arabic|arabica|1",
            "arabic|arabicaa|2",
            "arabic|arabicaaa|3",
            "arabic|arabicaaaa|4",
            "arabic|arabicaaaaa|5",
    }, delimiter = '|')
    void testLevenshteinDistanceParameterized(String from, String to, int distance) {
        int actualDistance = textUtils.levenshteinDistance(from, to);
        assertEquals(distance, actualDistance);
    }

}
