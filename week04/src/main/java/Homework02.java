import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

public class Homework02 {
    public static void main(String[] args) {
        try {
            System.out.println("提供了以下十二种种方式获取值:");
            action1();
            action2();
            action3();
            action4();
            action5();
            action6();
            action7();
            action8();
            action9();
            action10();
            action11();
            action12();
        } catch (InterruptedException e) {
            System.out.println("失败");
        }
    }

    public static void action1() throws InterruptedException {
        AtomicReference<String> result = new AtomicReference<>("");
        Thread thread = new Thread(() -> {
            try {
                //模拟业务操作等待2秒
                Thread.sleep(2000);
            } catch (Exception e) {
            }
            result.set(Hello());
        });
        thread.start();
        thread.join();

        System.out.println("action1:" + result.get());
    }

    public static void action2() throws InterruptedException {
        AtomicReference<String> result = new AtomicReference<>("");
        Thread thread = new Thread(() -> {
            result.set(Hello());
        });
        thread.start();
        Thread.sleep(2000);

        System.out.println("action2:" + result.get());
    }

    public static void action3() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        AtomicReference<String> result = new AtomicReference<>("");
        Thread thread = new Thread(() -> {
            try {
                //模拟业务操作等待2秒
                Thread.sleep(2000);
            } catch (Exception e) {
            }
            result.set(Hello());
            countDownLatch.countDown();
        });
        thread.start();
        countDownLatch.await();

        System.out.println("action3:" + result.get());
    }

    public static void action4() throws InterruptedException {
        AtomicReference<String> result = new AtomicReference<>("");
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        Thread thread = new Thread(() -> {
            try {
                //模拟业务操作等待2秒
                Thread.sleep(2000);
                result.set(Hello());
                cyclicBarrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
        try {
            cyclicBarrier.await();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("action4:" + result.get());
    }

    public static void action5() throws InterruptedException {
        AtomicReference<String> result = new AtomicReference<>("");
        Thread thread = new Thread(() -> {
            try {
                //模拟业务操作等待2秒
                Thread.sleep(2000);
                result.set(Hello());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
        try {
            CyclicBarrier cyclicBarrier = new CyclicBarrier(1, thread);
            cyclicBarrier.await();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("action5:" + result.get());
    }

    public static void action6() throws InterruptedException {
        AtomicReference<String> result = new AtomicReference<>("");
        AtomicBoolean complated = new AtomicBoolean(false);
        Thread thread = new Thread(() -> {
            try {
                //模拟业务操作等待2秒
                Thread.sleep(2000);
            } catch (Exception e) {
            }
            result.set(Hello());
            complated.set(true);
        });
        thread.start();

        while (!complated.get()) {
            Thread.sleep(100);
        }
        System.out.println("action6:" + result.get());
    }

    public static void action7() throws InterruptedException {
        AtomicReference<String> result = new AtomicReference<>("");
        Thread mainThread = Thread.currentThread();
        Thread thread = new Thread(() -> {
            try {
                //模拟业务操作等待2秒
                Thread.sleep(2000);
            } catch (Exception e) {
            }
            result.set(Hello());
            LockSupport.unpark(mainThread);
        });
        thread.start();
        LockSupport.park(Thread.currentThread());
        System.out.println("action7:" + result.get());
    }

    public static void action8() throws InterruptedException {
        AtomicReference<String> result = new AtomicReference<>("");
        Thread thread = new Thread(() -> {
            try {
                //模拟业务操作等待2秒
                Thread.sleep(2000);
                synchronized (result) {
                    result.set(Hello());
                    result.notify();
                }
            } catch (Exception e) {
            }
        });
        thread.start();

        synchronized (result) {
            result.wait();
        }
        System.out.println("action8:" + result.get());
    }

    public static void action9() throws InterruptedException {
        AtomicReference<String> result = new AtomicReference<>("");
        Thread thread1 = new Thread(() -> {
            try {
                //模拟业务操作等待2秒
                Thread.sleep(2000);
                result.set(Hello());
                Thread.currentThread().interrupt();
            } catch (Exception e) {
            }
        });
        thread1.start();

        while (true) {
            if (thread1.isInterrupted()) {
                break;
            }
        }

        System.out.println("action9:" + result.get());
    }

    public static void action10() throws InterruptedException {
        AtomicReference<String> result = new AtomicReference<>("");
        Thread threadMain = Thread.currentThread();
        Thread thread1 = new Thread(() -> {
            try {
                //模拟业务操作等待2秒
                Thread.sleep(2000);
                result.set(Hello());
                //通过中断方式
                threadMain.interrupt();
            } catch (Exception e) {
            }
        });
        thread1.start();
        LockSupport.park(threadMain);
        System.out.println("action10:" + result.get());
    }

    public static void action11() throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> result = executorService.submit(() -> {
            try {
                // 模拟业务操作等待2秒
                Thread.sleep(2000);
            } catch (Exception e) {
            }
            return Hello();
        });
        executorService.shutdown();

        try {
            System.out.println("action11:" + result.get());
        } catch (Exception e) {
        }
    }

    public static void action12() throws InterruptedException {
        FutureTask<String> ft = new FutureTask<>(() -> {
            try {
                // 模拟业务操作等待2秒
                Thread.sleep(2000);
            } catch (Exception e) {
            }
            return Hello();
        });
        new Thread(ft).start();
        try {
            System.out.println("action12:" + ft.get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static String Hello() {
        return "Hello world";
    }
}
