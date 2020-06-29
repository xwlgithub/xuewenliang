package tt.demo1;

import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Auther: 薛
 * @Date: 2020/6/29 09:48
 * @Description:
 */
public class MyThread  extends Thread{
    @Override
    public synchronized void start() {
        for (int i = 0; i < 10; i++) {
            System.out.println(this.getName());
        }
    }
}
 class MyThread2  implements Runnable{
     @Override
     public void run() {
         for (int i = 0; i < 10; i++) {
             System.out.println(Thread.currentThread().getName());
         }
     }
 }
 class MyThread3  implements Callable<String> {

     @Override
     public String call() throws Exception {
         return String.valueOf((int)(Math.random()*10+1));
     }
 }
class TestMyThread{
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
        Thread thread = new Thread(new MyThread2());
        thread.run();
        }
}
