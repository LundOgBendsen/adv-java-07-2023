import dk.logb.ex.textutils.TextUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TextUtilsTest {

    @Test
    void testIsPalindrome() {
        String testWord = "anna";
        boolean palindrome = new TextUtils().isPalindrome(testWord);
        assertTrue(palindrome);

        testWord = "anne";
        palindrome = new TextUtils().isPalindrome(testWord);
        assertFalse(palindrome);

        palindrome = new TextUtils().isPalindrome("");
        assertTrue(palindrome);

        palindrome = new TextUtils().isPalindrome("AnnA");
        assertTrue(palindrome);

        palindrome = new TextUtils().isPalindrome("Anna");
        assertFalse(palindrome);

        palindrome = new TextUtils().isPalindrome("123321");
        assertTrue(palindrome);

        palindrome = new TextUtils().isPalindrome("-123321-");
        assertTrue(palindrome);
    }

    @Test
    void testIsPalindromeThrowsNullpointer() {
        assertThrows(NullPointerException.class, () -> {
                    System.out.println("hej");
                    new TextUtils().isPalindrome(null);
                }
        );
    }



}
