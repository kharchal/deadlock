package bobowork.threadlock;

public class App {

    static Object lock1 = new Object();
    static Object lock2 = new Object();

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(App::foo);
        thread1.start();
        Thread thread2 = new Thread(App::boo);
        thread2.start();

        while (true) {
            System.out.println("t1 = " + thread1.getState());
            System.out.println("t2 = " + thread2.getState());
            sleep(200);
        }

    }

    private static void boo() {
        synchronized (lock2) {
            sleep(500);
            synchronized (lock1) {
                sleep(500);
                System.out.println("boo");
            }
        }
    }

    private static void foo() {
        synchronized (lock1) {
            sleep(500);
            synchronized (lock2) {
                sleep(500);
                System.out.println("foo");
            }
        }
    }

    private static void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
