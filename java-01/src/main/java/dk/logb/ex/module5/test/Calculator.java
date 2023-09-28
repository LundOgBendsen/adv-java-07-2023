package dk.logb.ex.module5.test;

public class Calculator {
    StatFunctions stats = new DefaultStatFunctions();

    // Dependency injection
    public Calculator(StatFunctions stats) {
        this.stats = stats;
    }

    public Calculator() {
    }



    public int add(int a, int b) {
        //simulate a misplaced call to a stat function
        stats.avg(a,b);
        return a+b;
    }

    public int avg(int... ints) {
        return stats.avg(ints);
    }

}
