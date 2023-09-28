package dk.logb.ex.module5.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by lb on 01/08/16.
 * This handler is used to create a mock of the StatFunctions interface.
 * It returns a default value for all methods in the StatFunctions interface.
 * It counts the number of invocation of the methods in the StatFunctions interface.
 *
 */
public class MyInvocationHandler implements InvocationHandler {

    int callCounter = 0;

    public int getCallCounter() {
        return callCounter;
    }

    //reset callCounter
    public void reset() {
        callCounter = 0;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method.getName() + " called!");

        callCounter++;

        Class<?> returnType = method.getReturnType();

        //return a default value based on the return type of the method
        if (returnType.equals(int.class)) {
            return 1;
        } else if (returnType.equals(String.class)) {
            return "1";
        } else if (returnType.equals(double.class)) {
            return 1.0;
        } else if (returnType.equals(float.class)) {
            return 1.0f;
        } else if (returnType.equals(long.class)) {
            return 1L;
        } else if (returnType.equals(short.class)) {
            return (short) 1;
        } else if (returnType.equals(byte.class)) {
            return (byte) 1;
        } else if (returnType.equals(char.class)) {
            return '1';
        } else if (returnType.equals(boolean.class)) {
            return true;
        } else {
            return null;
        }
    }
}
