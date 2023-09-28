package dk.logb.ex.module5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Lambdas {
    public static void main(String[] args) {
        Random random = new Random();
        IntStream ints = random.ints(100);

        ints.filter(i -> i % 2==0)
                .filter(i -> i > 0)
                .mapToObj(Lambdas::getDigits).flatMap(list -> list.stream())
                .forEach(System.out::println);

        List<List<Integer>> listOfLists = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5),
                Arrays.asList(6, 7, 8)
        );

        List<Integer> flattenedList = listOfLists.stream()
                .flatMap(list -> list.stream())  // Flattening step
                .toList();

//Prints [1, 2, 3, 4, 5, 6, 7, 8]
        System.out.println("Flattened list: " + flattenedList);
    }

    static List<Integer> getDigits(Integer i) {
        String s = ""+i;
        List<Integer> is = new ArrayList<>();

        for (int j =0;j<s.length();j++) {
            is.add(Integer.parseInt(""+s.charAt(j)));
        }
        return is;
    }
}
