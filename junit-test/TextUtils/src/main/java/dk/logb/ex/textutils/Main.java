package dk.logb.ex.textutils;



public class Main {
    public static void main(String[] args) {
        String cabale = new TextUtils().getSortedLetters("cabale");
        System.out.println(cabale);

        System.out.println("Starting...");
        System.out.println(new TextUtils().isAnagram("playground"));

        System.out.println("distance: arab to arab: " + new TextUtils().levenshteinDistance("arabic", "atrab"));



    }
}