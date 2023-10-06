package dk.logb.ex.textutils;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TextUtils_DistanceTest {
    static TextUtils tu;

    @BeforeAll
    public static void setup() {
        tu = new TextUtils();
    }

    //test levenshteinDistance
    @Test
    public void testDistance0() {
        int distance = tu.levenshteinDistance("object", "object");
        assertEquals(0, distance);
    }

    @Test
    public void testDistanceFromEmpty() {
        int distance = tu.levenshteinDistance("", "object");
        assertEquals(6, distance);
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
    public void testDistances(String from, String to, int expected) {
        assertEquals(expected, tu.levenshteinDistance(from, to));
    }



}
