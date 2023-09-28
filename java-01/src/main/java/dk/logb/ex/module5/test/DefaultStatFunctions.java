package dk.logb.ex.module5.test;
import java.util.Arrays;

public class DefaultStatFunctions implements StatFunctions {

    public int avg(int[] ints) {
        //calculate sum using streams
        int sum = Arrays.stream(ints).reduce(0, (a, b) -> a + b);
        return sum / ints.length;
    }

    @Override
    public int add(int a, int b) {
        return 0;
    }
}
