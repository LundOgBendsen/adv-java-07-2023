package dk.logb.ex.module5;

public class FunWithLmbda {

    public static void main(String[] args) {
        long l = measureTime(() -> {
            System.out.println("42");
            return null;
        });
        System.out.println(l);

        long o =measureTime2( ost -> {
            System.out.println("hello: " + ost.toUpperCase());
            return "hej";
        });

        System.out.println(o);


        Executable e = doTwice(new PrintJob());
        Executable f = doTwice(e);



        f.run();


    }

    static Executable doTwice(Executable e) {
        return new Executable() {
            @Override
            public Object run() {
                e.run();
                e.run();
                return null;
            }
        };
    }

    static long measureTime(Executable executable) {
        long start = System.currentTimeMillis();
        executable.run();
        long end = System.currentTimeMillis();
        return end-start;
    }

    static long measureTime2(Task task) {
        long start = System.currentTimeMillis();
        String string = task.run("string");
        System.out.println("fra measuretime2: " + string);
        long end = System.currentTimeMillis();
        return end-start;
    }

    static long twice(Executable executable) {
        executable.run();
        executable.run();
        return 0;
    }



}


interface Executable {
    Object run();
}

interface Task {
    String run(String s);
}

class PrintJob implements Executable {
    @Override
    public Object run() {
        System.out.println("The job prints this!");
        return null;
    }
}

class LoopJob implements Executable {
    @Override
    public Object run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("The loopjob prints this!");
        }
        return null;
    }
}