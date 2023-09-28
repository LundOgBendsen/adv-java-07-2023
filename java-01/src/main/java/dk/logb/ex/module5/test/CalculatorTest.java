package dk.logb.ex.module5.test;

import java.lang.reflect.Proxy;

public class CalculatorTest {

    /* create a mock of the StatFunctions interface.
    Any call to a method in the StatFunctions interface will be handled by the MyInvocationHandler.
    The MyInvocationHandler will just print the name of the method called and return 1.
     */

    StatFunctions createMock() {
        return (StatFunctions) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class[]{StatFunctions.class}, new MyInvocationHandler() );

    }

    StatFunctions createMock(MyInvocationHandler handler) {
        return (StatFunctions) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class[]{StatFunctions.class}, handler );

    }

    public static void main(String[] args) {
        new CalculatorTest().testAdd();
        new CalculatorTest().testAddDoesNotCallStatFunctions();
    }

    void testAdd() {
        Calculator calculator = new Calculator();
        int result = calculator.add(2, 2);
        if (result != 4) {
            throw new RuntimeException("burde være 4, var " + result);
        }
    }

    void testAvg() {
        Calculator calculator = new Calculator();
        int result = calculator.avg(1,2,3,4,5,6,7,8,9,10);
        if (result != 5) {
            throw new RuntimeException("avg burde være 5, var " + result);
        }
    }

    void testAddDoesNotCallStatFunctions() {
        StatFunctionMock statFunctionMock = new StatFunctionMock();
        Calculator calculator = new Calculator(statFunctionMock);
        calculator.add(2, 2);

        int numberOfCalls = statFunctionMock.getNumberOfCalls();
        if (numberOfCalls != 1) {
            throw new RuntimeException("expected: 0, but found: " + numberOfCalls);
        }
    }

    void testAddDoesNotCallStatFunctions2() {
        MyInvocationHandler handler = new MyInvocationHandler();
        StatFunctions statFunctionMock = createMock(handler);

        Calculator calculator = new Calculator(statFunctionMock);
        calculator.add(2,2);
        if (handler.getCallCounter() != 0) {
            throw new RuntimeException("expected: 0, but found: " + handler.getCallCounter());
        }
        handler.reset();

        int avg = calculator.avg(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        if (handler.getCallCounter() != 1) {
            throw new RuntimeException("expected: 1, but found: " + handler.getCallCounter());
        }


    }

}

class StatFunctionMock implements StatFunctions {

    int numberOfCalls=0;

    @Override
    public int avg(int[] ints) {
        numberOfCalls++;
        return 0;
    }

    @Override
    public int add(int a, int b) {
        numberOfCalls++;
        return a + b;
    }

    public int getNumberOfCalls() {
        return numberOfCalls;
    }
}
