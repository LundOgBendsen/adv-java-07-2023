package dk.logb.ex.errorhandling;

public class Example {
    public static void main(String[] args) {
            a();
    }

    //an a() method that calls b() that calls c() that trows a runtime exception
    public static void a() {
        b();
    }

    public static void b() {
        c();
    }

    public static void c() {
        throw new RuntimeException("c() throws a runtime exception");
    }


}
