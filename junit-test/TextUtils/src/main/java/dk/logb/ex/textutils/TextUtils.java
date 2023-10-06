package dk.logb.ex.textutils;

import java.io.InputStream;
import java.util.Dictionary;
import java.util.Set;
import java.util.SortedSet;

public class TextUtils {

    private Set<String> sortedWords;

    private Vocabulary vocabulary = new EnglishVocabulary();

    public TextUtils() {
    }

    public TextUtils(Vocabulary vocabulary) {
        this.vocabulary = vocabulary;
    }


    public boolean isPalindrome(String s) {
        return s.equals(new StringBuilder(s).reverse().toString());
    }

    private Set getSortedWords() {
        if (sortedWords != null) {
            return sortedWords;
        }
        Set<String> words = vocabulary.getWords();
        sortedWords = words.stream().map((word) -> getSortedLetters(word)).collect(java.util.stream.Collectors.toSet());
        return sortedWords;
    }

    String getSortedLetters(String in) {
        char[] chars = in.toCharArray();
        java.util.Arrays.sort(chars);
        return new String(chars);
    }


    public boolean isAnagram(String s) {
        String sorted = getSortedLetters(s.toLowerCase());
        return getSortedWords().contains(sorted);
    }

    public int levenshteinDistance(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];
        for (int i = 0; i <= a.length(); i++)
            dp[i][0] = i;
        for (int j = 0; j <= b.length(); j++)
            dp[0][j] = j;

        for (int i = 1; i <= a.length(); i++)
            for (int j = 1; j <= b.length(); j++)
                dp[i][j] = Math.min(
                        Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1),
                        dp[i - 1][j - 1]
                                + ((a.charAt(i - 1) == b.charAt(j - 1)) ? 0
                                        : 1));

        return dp[a.length()][b.length()];
    }


}
