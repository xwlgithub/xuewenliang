package tt.runable;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: 薛
 * @Date: 2020/6/29 15:41
 * @Description:
 */
public class MyRunable implements Runnable {
    ReentrantLock lk = new ReentrantLock();
    int counts = 15;

    @Override
    public void run() {
        while (true) {
            lk.lock();
            try {
                if (counts <= 0) {
                    break;
                }
                System.out.println(Thread.currentThread().getName() + "\t" + counts);
                counts--;
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lk.unlock();
            }
        }
    }
    public static void main(String[] args) {
//        MyRunable myRunable = new MyRunable();
//        new Thread(myRunable, "张三").start();
//        new Thread(myRunable, "李四").start();
        String ss ="zz";
        if (ss!=null) System.out.println("哈哈");
        else System.out.println("zhangsan");
    }
}

