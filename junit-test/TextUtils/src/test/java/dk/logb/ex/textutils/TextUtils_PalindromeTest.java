package dk.logb.ex.textutils;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TextUtils_PalindromeTest {
    static TextUtils tu;

    @BeforeAll
    public static void setup() {
        tu = new TextUtils();
    }

    //test isPalindrome()
    @Test
    public void testIsPalindrome() {
        //test with a palindrome
        String palindrome = "racecar";
        assertTrue(tu.isPalindrome(palindrome));

        //test with a non-palindrome
        String nonPalindrome = "hello";
        assertTrue(! tu.isPalindrome(nonPalindrome));
    }

    //parameterized test for isPalindrome
    @ParameterizedTest
    @ValueSource(strings = {"racecar", "radar", "level", "rotator"})
    public void testIsPalindrome(String palindrome) {
        assertTrue(tu.isPalindrome(palindrome));
    }

    @ParameterizedTest
    @CsvSource({"racecar, true", "radar, true", "level, true", "rotator, true", "hello, false", "world, false"})
    public void testIsPalindrome(String word, boolean expected) {
        assertEquals(expected, tu.isPalindrome(word));
    }
}
