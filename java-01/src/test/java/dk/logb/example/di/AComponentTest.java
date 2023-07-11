package dk.logb.example.di;


//junit jupiter test class
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;

//junit jupiter assertions
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

//junit jupiter extensions
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class AComponentTest {

    private ABean aComponent;

    @BeforeEach
    void setUp() {
        aComponent = new ABean();
        aComponent.setBComponent(new BBean());
    }

    @AfterEach
    void tearDown() {
        aComponent = null;
    }

    @Test
    @DisplayName("Test if all strings are palindromes - positive")
    void testIsAllPalindromeTrue() {
        assertTrue(aComponent.isAllPalindrome("otto", "anna", "hannah", "kayak", "level", "racecar", "radar", "rotator", "rotor", "tenet"));
    }

    @Test
    @DisplayName("Test if all strings are palindromes - negative")
    void testIsAllPalindromeFalse() {
        assertFalse(aComponent.isAllPalindrome("otto", "anna", "hannah", "kayak", "level", "racecar", "radar", "rotator", "rotor", "tenet", "not a palindrome"));
    }

    //parameterized testcase for isPalindrome
    @ParameterizedTest
    @DisplayName("Test if many single words are palindromes - positive")
    @ValueSource(strings={"otto", "anna", "hannah", "kayak", "level", "racecar", "radar", "rotator", "rotor", "tenet"})
    void testIsPalindrome(String s) {
        assertTrue(aComponent.isAllPalindrome(s));
    }

}