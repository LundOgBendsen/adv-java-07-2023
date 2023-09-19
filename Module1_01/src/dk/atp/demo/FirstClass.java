package dk.atp.demo;

public class FirstClass {

    public static void main(String[] args) {
        System.out.println("Hello World!");


        System.out.println(getSum(200));
    }

    private static int getSum(int n) {
        int resultat = 0;
        for (int i = 0; i <= n; i++) {
                resultat += i;
        }
        return resultat;

}
