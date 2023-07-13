package dk.logb.ex.module2;

public class Palindromes {

    public static void main(String[] args) {
        boolean racecar = isPalindrome("racecar");
        System.out.println("racecar is palindrome: " + racecar);
        boolean fane = isPalindromeIterative("enafdemderredmedfane");
        System.out.println("enafdemderredmedfane is palindrome: " + fane);

    }

    //reverse string palindrome method
    public static boolean isPalindromeReverse(String s) {
        return s.equals(new StringBuilder(s).reverse().toString());
    }


    //iterative palindrome method
    public static boolean isPalindromeIterative(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i <= j) {
            if (s.charAt(i++) != s.charAt(j--)) {
                return false;
            }
        }
        return true;
    }

    //recursive palindrome method
    public static boolean isPalindrome(String s) {
        if (s.length() <= 1) {
            return true;
        } else if (s.charAt(0) == s.charAt(s.length() - 1)) {
            return isPalindrome(s.substring(1, s.length() - 1));
        } else {
            return false;
        }
    }

}
